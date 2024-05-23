package ru.glamcheck.compoanalyzer.client;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.client.parser.ComponentClientHtmlParser;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;

import java.io.IOException;

@Component
@Data
@RequiredArgsConstructor
@PropertySource("classpath:componentclient.properties")
public class WebComponentCosmoBaseClient implements ComponentClient {

    @Value("${component.client.baseurl}")
    private String baseUrl;

    private final ComponentClientHtmlParser parser;

    private final WebClient webClient;

    @Override
    public Mono<ComponentResponse> getComponentByInciName(String inciName) {
        return webClient
                .get()
                .uri("/handbook/show/{inciName}", inciName)
                .retrieve()
                .bodyToMono(String.class)
                .map(Jsoup::parse)
                .map(parser);
    }

}

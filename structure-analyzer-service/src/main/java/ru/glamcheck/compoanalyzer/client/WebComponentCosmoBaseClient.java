package ru.glamcheck.compoanalyzer.client;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.client.exception.ComponentNotFoundException;
import ru.glamcheck.compoanalyzer.client.parser.ComponentClientHtmlParser;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;

@Component
@Data
@RequiredArgsConstructor
@PropertySource("classpath:componentclient.properties")
public class WebComponentCosmoBaseClient implements ComponentClient {

    @Value("${component.client.baseurl}")
    private String baseUrl;

    private final ComponentClientHtmlParser htmlParser;

    private final WebClient webClient;

    @Override
    public Mono<ComponentResponse> getComponentByLatinName(String latinName) {
        String latinNameRequestParam = latinName.replace(' ', '_');
        return webClient
                .get()
                .uri(baseUrl + latinNameRequestParam)
                .retrieve()
                .bodyToMono(String.class)
                .map(Jsoup::parse)
                .map(htmlParser)
                .onErrorMap(throwable -> new ComponentNotFoundException(latinName));
    }

}

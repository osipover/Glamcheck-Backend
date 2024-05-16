package ru.glamcheck.compoanalyzer.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.glamcheck.compoanalyzer.client.parser.ComponentClientHtmlParser;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;

import java.io.IOException;

@Component
@Data
@PropertySource("classpath:componentclient.properties")
public class ComponentCosmoBaseClient implements ComponentClient {

    @Value("${component.client.baseurl}")
    private String baseUrl;

    @Autowired
    private ComponentClientHtmlParser parser;

    public ComponentResponse getComponentByInciName(String inciName) {
        ComponentResponse component = null;
        try {
            Document document = Jsoup.connect(baseUrl.formatted(inciName.toUpperCase())).get();
            component = parser.apply(document);

        } catch (HttpStatusException e) {
            System.out.println(e.getStatusCode());
        } catch (IOException e) {
            //todo выбросить кастомное исключение
        }
        return component;
    }

}

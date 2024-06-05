package ru.glamcheck.compoanalyzer.client.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.glamcheck.compoanalyzer.client.parser.exception.ImpossibleParseComponentException;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComponentCosmoBaseHtmlParserTest {

    @Value("${component.client.baseurl}")
    String baseUrl;

    WebClient webClient = WebClient.builder()
            .exchangeStrategies(ExchangeStrategies.builder()
                    .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                    .build())
            .build();

    ComponentClientHtmlParser componentClientHtmlParser = new ComponentCosmoBaseHtmlParser();

    @Test
    void apply_invalidHtml_returnsImpossibleParseComponentException() {
        String html = "Invalid html";
        Document document = Jsoup.parse(html);

        assertThrows(ImpossibleParseComponentException.class, () -> componentClientHtmlParser.apply(document));
    }

    @Test
    void apply_NormalHtml_returnsComponentResponse() {
        String latinName = "AQUA";
        ComponentResponse expected = new ComponentResponse(
                "AQUA",
                0,
                "Натуральный",
                new ArrayList<>(),
                List.of(
                        "Детская кожа", "Для губ", "Для кожи тела", "Для любого типа кожи", "Для ногтей",
                        "Жирная кожа", "Жирные волосы", "Кожа век", "Комбинированная кожа", "Нормальная кожа",
                        "Нормальные волосы", "Обезвоженная кожа", "Проблемная кожа", "Смешанные волосы",
                        "Сухая кожа", "Сухие волосы", "Увядающая кожа", "Чувствительная кожа"
                ));

        Mono<ComponentResponse> response = webClient
                .get()
                .uri("https://cosmobase.ru/handbook/show/" + latinName)
                .retrieve()
                .bodyToMono(String.class)
                .map(Jsoup::parse)
                .map(componentClientHtmlParser);

        StepVerifier.create(response)
                .expectNext(expected)
                .verifyComplete();
    }
}
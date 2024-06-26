package ru.glamcheck.compoanalyzer.client.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.glamcheck.compoanalyzer.client.parser.exception.ImpossibleParseComponentException;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;
import ru.glamcheck.compoanalyzer.model.dto.CosmeticFeatureDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComponentCosmoBaseHtmlParser implements ComponentClientHtmlParser {

    @Override
    public ComponentResponse apply(Document document) {
        try {
            String latinName = parseLatinName(document);
            Integer dangerFactor = parseDangerFactor(document);
            String naturalness = parseNaturalness(document);
            List<CosmeticFeatureDto> features = parseCosmeticFeatures(document);
            List<String> skinTypes = parseSkinTypes(document);

            return new ComponentResponse(
                    latinName,
                    dangerFactor,
                    naturalness,
                    features,
                    skinTypes
            );
        } catch (Exception e) {
            throw new ImpossibleParseComponentException("Невозможно распарсить страницу: " + e.getMessage());
        }
    }

    private String parseLatinName(Document document) {
        return document.select(".componentName").get(1).text();
    }

    private Integer parseDangerFactor(Document document) {
        return Integer.parseInt(document.select("[class~=(low|medium|high)Factor]")
                .first()
                .child(0)
                .text());
    }

    private String parseNaturalness(Document document) {
        Elements elements = document.select("[class~=natural(No|Yes)\\b]");
        return elements
                .first()
                .nextElementSibling()
                .text();
    }

    private List<CosmeticFeatureDto> parseCosmeticFeatures(Document document) {
        List<CosmeticFeatureDto> features = new ArrayList<>();
        Elements elements = document.select("label:contains(Косметические свойства)");

        if (!elements.isEmpty()) {
            features = elements.first().nextElementSibling().children()
                    .stream()
                    .map(e -> new CosmeticFeatureDto(
                            e.child(0).text(),
                            Double.parseDouble(e.child(1).text())
                    )).toList();
        }
        return features;
    }

    private List<String> parseSkinTypes(Document document) {
        List<String> skinTypes = new ArrayList<>();
        Elements elements = document.select("label:contains(Эффективен для типов кожи:)");
        if (!elements.isEmpty()) {
            skinTypes = elements.first().nextElementSibling().children()
                    .stream()
                    .map(Element::text)
                    .filter(str -> !str.isEmpty())
                    .toList();
        }
        return skinTypes;
    }
}

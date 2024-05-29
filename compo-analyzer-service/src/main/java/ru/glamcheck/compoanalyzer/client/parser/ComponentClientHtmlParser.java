package ru.glamcheck.compoanalyzer.client.parser;

import org.jsoup.nodes.Document;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;

import java.util.function.Function;

public interface ComponentClientHtmlParser extends Function<Document, ComponentResponse> {

}

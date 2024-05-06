package ru.glamcheck.compoanalyzer.client;

import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;

public interface ComponentClient {

    ComponentResponse getComponentByInciName(String inciName);
}

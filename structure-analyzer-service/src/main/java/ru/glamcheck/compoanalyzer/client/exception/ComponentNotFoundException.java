package ru.glamcheck.compoanalyzer.client.exception;

import lombok.Getter;

@Getter
public class ComponentNotFoundException extends RuntimeException {
    private final String componentName;

    public ComponentNotFoundException(String componentName) {
        super("Component not found: " + componentName);
        this.componentName = componentName;
    }
}

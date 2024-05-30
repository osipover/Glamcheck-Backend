package ru.glamcheck.compoanalyzer.client.parser.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ImpossibleParseComponentException extends RuntimeException {

    public ImpossibleParseComponentException(String message) {
        super(message);
    }

}

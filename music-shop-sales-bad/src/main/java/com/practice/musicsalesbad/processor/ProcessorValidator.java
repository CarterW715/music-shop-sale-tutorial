package com.practice.musicsalesbad.processor;

import com.practice.musicsalesgood.exception.ProcessorValidationException;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class ProcessorValidator {
    abstract void validationLogic(MusicShopEvent event, List<String> errorList);

    public void validateEvent(MusicShopEvent event) throws ProcessorValidationException {
        var errorList = new ArrayList<String>();

        validationLogic(event, errorList);

        if (!errorList.isEmpty()) {
            throw new ProcessorValidationException(String.join(", ", errorList));
        }
    }
}

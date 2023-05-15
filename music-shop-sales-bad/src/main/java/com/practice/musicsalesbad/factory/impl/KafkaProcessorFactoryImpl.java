package com.practice.musicsalesbad.factory.impl;

import com.practice.musicsalesgood.factory.KafkaProcessorFactory;
import com.practice.musicsalesgood.kafka.processor.KafkaProcessor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class KafkaProcessorFactoryImpl implements KafkaProcessorFactory {

    @Inject
    Instance<KafkaProcessor> kafkaProcessors; // Reflection to grab all service classes that implement the proper blueprint

    public List<KafkaProcessor> getKafkaProcessors() {
        return kafkaProcessors.stream().collect(Collectors.toList());
    }
}

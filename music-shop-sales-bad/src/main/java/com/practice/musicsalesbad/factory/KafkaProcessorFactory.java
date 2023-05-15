package com.practice.musicsalesbad.factory;

import com.practice.musicsalesgood.kafka.processor.KafkaProcessor;

import java.util.List;

public interface KafkaProcessorFactory {
    List<KafkaProcessor> getKafkaProcessors();
}

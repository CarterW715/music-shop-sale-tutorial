package com.practice.musicsalespractice.kafka.serializer;

import com.practice.musicsalespractice.kafka.model.MusicShopEvent;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MusicShopEventSerializer extends ObjectMapperDeserializer<MusicShopEvent> {
    public MusicShopEventSerializer() {
        super(MusicShopEvent.class);
    }
}

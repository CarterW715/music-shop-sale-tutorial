package com.practice.kafka.serializer;

import com.practice.kafka.model.MusicShopEvent;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MusicShopEventSerializer extends ObjectMapperDeserializer<MusicShopEvent> {
    public MusicShopEventSerializer() {
        super(MusicShopEvent.class);
    }
}

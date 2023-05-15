package com.practice.musicsalesbad.kafka.serializer;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MusicShopEventSerializer extends ObjectMapperDeserializer<MusicShopEvent> {
    public MusicShopEventSerializer() {
        super(MusicShopEvent.class);
    }
}

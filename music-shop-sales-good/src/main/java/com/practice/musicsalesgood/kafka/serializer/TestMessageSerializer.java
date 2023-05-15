package com.practice.musicsalesgood.kafka.serializer;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class TestMessageSerializer extends ObjectMapperDeserializer<MusicShopEvent> {
    public TestMessageSerializer() {
        super(MusicShopEvent.class);
    }
}

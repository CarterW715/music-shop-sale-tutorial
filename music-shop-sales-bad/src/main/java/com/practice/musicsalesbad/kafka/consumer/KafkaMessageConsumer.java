package com.practice.musicsalesbad.kafka.consumer;



@Slf4j
@AllArgsConstructor
public class KafkaMessageConsumer {

    @Inject
    KafkaProcessorFactory kafkaProcessorFactoryImpl;

    ObjectMapper objectMapper;

    @Incoming("music_sales_stream_local")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void consume(MusicShopEvent message) {
        log.info("Received kafka message: " + message.toString());
        kafkaProcessorFactoryImpl.getKafkaProcessors().forEach(listener -> {
            if (listener.acceptsEventType(message.getHeader().getEventType()))
                listener.handleMessage(message);
        });
    }

}

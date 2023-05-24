package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.mapper.MessageMapper;
import com.practice.musicsalesgood.service.rest.RewardsServiceRest;
import com.practice.musicsalesgood.validation.processor.RewardsProcessorValidator;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Slf4j
@ApplicationScoped
public class RewardsProcessor extends MusicShopEventProcessor<RewardsProcessorValidator> {

    @RestClient
    RewardsServiceRest rewardsServiceRest;

    @Override
    public boolean acceptsEventType(String eventType) {
        return eventType.equals(MusicShopEvents.sold.name()) || eventType.equals(MusicShopEvents.scheduled.name());
    }

    @Override
    public String getListenerName() {
        return "Rewards";
    }

    @Override
    public RewardsProcessorValidator getValidator() {
        return new RewardsProcessorValidator();
    }

    public void processEvent(MusicShopEvent message) {

        var request = MessageMapper.messageToRewardsRequest(message);

        var response = rewardsServiceRest.submitRewards(request);
        if (response.getStatus().getCode() != 200) {
            log.error("Something went wrong: {}", response.getStatus().getMessage());
        } else {
            log.info("Rewards successfully awarded");
        }
    }

}

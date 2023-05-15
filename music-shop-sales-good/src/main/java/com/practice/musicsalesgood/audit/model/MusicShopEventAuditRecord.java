package com.practice.musicsalesgood.audit.model;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import lombok.Setter;

import java.time.LocalDateTime;

public class MusicShopEventAuditRecord {
    MusicShopEvent receivedEvent;
    LocalDateTime receivedTime;
    @Setter
    String error;
}

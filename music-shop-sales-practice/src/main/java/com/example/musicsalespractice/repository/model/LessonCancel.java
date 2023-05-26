package com.example.musicsalespractice.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson_cancel", schema = "good")
public class LessonCancel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_cncl_id")
    long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "shop_lesson_id", referencedColumnName = "shop_lesson_id")
    ShopLesson shopLesson;

    @Column(name = "rfnd_amt")
    Double refundAmount;

    @Column(name = "msg_id")
    UUID messageId;

    @Column(name = "evnt_ts")
    LocalDateTime eventTimestamp;

    @Column(name = "msg_version")
    String version;
}

package com.practice.musicsalesgood.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
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

}
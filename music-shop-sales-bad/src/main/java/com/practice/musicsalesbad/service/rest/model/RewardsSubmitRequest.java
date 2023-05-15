package com.practice.musicsalesbad.service.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RewardsSubmitRequest {
    String employeeName;
    String teacherName;
}

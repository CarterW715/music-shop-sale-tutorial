package com.practice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardsSubmitRequest {
    String employeeName;
    String teacherName;
}

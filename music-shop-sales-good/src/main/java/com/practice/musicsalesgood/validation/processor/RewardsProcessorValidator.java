package com.practice.musicsalesgood.validation.processor;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.util.StringUtil;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RewardsProcessorValidator extends ProcessorValidator {

    @Override
    public void validationLogic(MusicShopEvent event, List<String> errorList) {
        if (StringUtil.isEmptyOrNull(event.getSale().getEmployeeName()) &&
                StringUtil.isEmptyOrNull(event.getLesson().getTeacherName())) {
            errorList.add("Employee Name or Teacher Name is required");
        }
    }
}

package com.msa.attributesmanagementapi.document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Data
public class TimeSchedule {

    @NotNull(message = "The start time can not be null")
    private LocalTime startTime;
    @NotNull(message = "The end time can not be null")
    private LocalTime endTime;
    @NotEmpty(message = "The week schedule can not be empty")
    private Set<String> daysOfWeek;

    public TimeSchedule(LocalTime startTime, LocalTime endTime, Set<String> daysOfWeek) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.daysOfWeek = daysOfWeek;
    }

}
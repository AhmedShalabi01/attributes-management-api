package org.pacs.attributesmanagementapi.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class TimeSchedule {
    @NotNull(message = "The start time can not be null")
    @JsonProperty("ST")
    private LocalTime startTime;
    @NotNull(message = "The end time can not be null")
    @JsonProperty("ET")
    private LocalTime endTime;
    @NotEmpty(message = "The week schedule can not be empty")
    @JsonProperty("DW")
    private Set<String> daysOfWeek;
}
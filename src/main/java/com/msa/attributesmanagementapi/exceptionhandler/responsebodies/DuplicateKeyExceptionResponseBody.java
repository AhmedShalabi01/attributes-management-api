package com.msa.attributesmanagementapi.exceptionhandler.responsebodies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DuplicateKeyExceptionResponseBody {

    @JsonProperty("timestamp")
    private final LocalDateTime dateTime;
    @JsonProperty("status")
    private final Integer status;
    @JsonProperty("message")
    private String errorMessages;


    public DuplicateKeyExceptionResponseBody(HttpStatusCode status) {
        this.dateTime = LocalDateTime.now();
        this.status = status.value();
        this.errorMessages = "The ID trying to add is already existing";
    }

}

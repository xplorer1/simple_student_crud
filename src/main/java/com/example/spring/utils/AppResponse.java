package com.example.spring.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppResponse {
    protected LocalDateTime timeStamp;
    protected String message;
    protected HttpStatus status;
    protected Map<?, ?> data;
}
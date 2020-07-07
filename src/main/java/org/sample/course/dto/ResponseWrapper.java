package org.sample.course.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponseWrapper<T> {
    Timestamp now;
    String message;
    T data;

    public ResponseWrapper(T data, String message) {
        this.data = data;
        this.message = message;
        now = new Timestamp(new Date().getTime());
    }
}

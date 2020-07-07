package org.sample.course.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.sample.course.dto.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthCheckController {

    @GetMapping(value = "/health")
    @Operation(summary = "Endpoint to be used as heartbeat check")
    public ResponseWrapper<Map> healthCheck() {
        Map<String,Object> data = new HashMap<>();
        data.put("status", "UP");
        return new ResponseWrapper<>(data, "Health Check");
    }
}

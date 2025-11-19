package com.osrs.hiscores.controller;


import com.osrs.hiscores.dto.HelloWorldDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @PostMapping("/hello")
    public ResponseEntity<String> hello(@RequestBody HelloWorldDto helloWorldDto) {
        final String message = "Hi.." + helloWorldDto.getName();
        return ResponseEntity.ok(message);
    }
}

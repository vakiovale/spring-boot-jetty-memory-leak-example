package com.example.bughunter;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class BugHunterController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/bug/hunt")
    public Flux<ServerSentEvent<Object>> bugHunt() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(aLong -> ServerSentEvent.builder().event("heartbeat: " + aLong).build())
                .take(Duration.ofSeconds(10));
    }

}
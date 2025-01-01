package com.example.digiWatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/timer")
public class watchController {

    private long endTime;

    @PostMapping("/set")
    public String setTimer(@RequestParam(required = false, defaultValue = "0") long hours,
                           @RequestParam(required = false, defaultValue = "0") long minutes) {
        long totalMilliseconds = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000);
        if (totalMilliseconds <= 0) {
            return "Please provide a valid time (hours or minutes greater than 0).";
        }
        this.endTime = System.currentTimeMillis() + totalMilliseconds;
        return "Timer set for " + hours + " hour(s) and " + minutes + " minute(s).";
    }

    @GetMapping("/status")
    public long getTimeRemaining() {
        long remaining = endTime - System.currentTimeMillis();
        return Math.max(remaining, 0); 
    }

    @PostMapping("/reset")
    public String resetTimer() {
        this.endTime = 0;
        return "Timer reset.";
    }
}

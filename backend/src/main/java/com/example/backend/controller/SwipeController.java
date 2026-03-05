package com.example.backend.controller;

import com.example.backend.dto.SwipeRequest;
import com.example.backend.entity.Match;
import com.example.backend.service.SwipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/swipes")
public class SwipeController {

    private final SwipeService swipeService;

    public SwipeController(SwipeService swipeService) {
        this.swipeService = swipeService;
    }

    @PostMapping
    public Match swipe(@RequestBody SwipeRequest req) {
        return swipeService.swipe(req.getSwiperId(), req.getSwipedId(), req.getType());
    }
}

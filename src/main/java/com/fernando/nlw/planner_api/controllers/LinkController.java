package com.fernando.nlw.planner_api.controllers;

import com.fernando.nlw.planner_api.responses.LinkResponse;
import com.fernando.nlw.planner_api.services.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @GetMapping("/{linkID}")
    public ResponseEntity<LinkResponse> findLinkByID(@PathVariable UUID linkID) {
        return ResponseEntity.ok(linkService.findLinkByID(linkID));
    }
}

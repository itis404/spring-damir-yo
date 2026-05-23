package com.itis.test4.controller;

import com.itis.test4.dto.CreatePollRequest;
import com.itis.test4.dto.PollResultDto;
import com.itis.test4.dto.VoteRequest;
import com.itis.test4.entity.Poll;
import com.itis.test4.entity.Vote;
import com.itis.test4.service.PollService;
import com.itis.test4.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
@RequiredArgsConstructor
public class PollController {
    private final PollService pollService;
    private final VoteService voteService;

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllActivePolls();
    }

    @PostMapping
    public Poll createPoll(
            @Valid @RequestBody CreatePollRequest request
    ) {
        return pollService.createPoll(request);
    }

    @PutMapping("/{id}/close")
    public Poll closePoll(@PathVariable Long id) {
        return pollService.closePoll(id);
    }

    @PostMapping("/{id}/vote")
    public Vote vote(
            @PathVariable Long id,
            @Valid @RequestBody VoteRequest request
    ) {
        return voteService.vote(id, request);
    }

    @GetMapping("/{id}/results")
    public List<PollResultDto> getResults(
            @PathVariable Long id
    ) {
        return pollService.getResults(id);
    }
}
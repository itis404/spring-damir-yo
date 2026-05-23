package com.itis.test4.service;

import com.itis.test4.dto.VoteRequest;
import com.itis.test4.entity.Option;
import com.itis.test4.entity.Poll;
import com.itis.test4.entity.Vote;
import com.itis.test4.exception.AlreadyVotedException;
import com.itis.test4.exception.OptionNotFoundException;
import com.itis.test4.exception.PollNotFoundException;
import com.itis.test4.repository.OptionRepository;
import com.itis.test4.repository.PollRepository;
import com.itis.test4.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteService {
    private final VoteRepository voteRepository;
    private final OptionRepository optionRepository;
    private final PollRepository pollRepository;

    public Vote vote(Long pollId, VoteRequest request) {

        log.info("user {} vote in poll {}", request.getUserId(), pollId);

        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new PollNotFoundException("Poll not found"));

        if (!poll.isActive()) {
            throw new RuntimeException("poll closed");
        }

        boolean alreadyVoted = voteRepository.existsByUserIdAndOptionPollId(request.getUserId(), pollId);

        if (alreadyVoted) {
            throw new AlreadyVotedException(
                    "user already voted"
            );
        }

        Option option = optionRepository.findById(request.getOptionId()).orElseThrow(() -> new OptionNotFoundException("option not found"));

        Vote vote = new Vote();
        vote.setUserId(request.getUserId());
        vote.setOption(option);
        vote.setVotedAt(LocalDateTime.now());

        return voteRepository.save(vote);
    }
}
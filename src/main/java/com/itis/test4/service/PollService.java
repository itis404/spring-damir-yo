package com.itis.test4.service;

import com.itis.test4.dto.CreatePollRequest;
import com.itis.test4.dto.PollResultDto;
import com.itis.test4.entity.Option;
import com.itis.test4.entity.Poll;
import com.itis.test4.exception.PollNotFoundException;
import com.itis.test4.repository.PollRepository;
import com.itis.test4.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PollService {
    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    public List<Poll> getAllActivePolls() {
        log.info("get all active polls");
        return pollRepository.findByActiveTrue();
    }

    public Poll createPoll(CreatePollRequest request) {
        log.info("create poll: {}", request.getQuestion());
        Poll poll = new Poll();
        poll.setQuestion(request.getQuestion());
        poll.setCreatedAt(LocalDateTime.now());
        poll.setActive(true);

        List<Option> options = new ArrayList<>();

        for (String text : request.getOptions()) {
            Option option = new Option();
            option.setText(text);
            option.setPoll(poll);

            options.add(option);
        }
        poll.setOptions(options);

        return pollRepository.save(poll);
    }

    public Poll closePoll(Long id) {
        log.info("close poll with id {}", id);
        Poll poll = pollRepository.findById(id).orElseThrow(() -> new PollNotFoundException("poll not found"));
        poll.setActive(false);

        return pollRepository.save(poll);
    }

    public List<PollResultDto> getResults(Long pollId) {
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new PollNotFoundException("poll not found"));

        long totalVotes = 0;

        for (Option option : poll.getOptions()) {
            totalVotes += voteRepository.countByOptionId(option.getId());
        }

        List<PollResultDto> results = new ArrayList<>();

        for (Option option : poll.getOptions()) {
            long votes = voteRepository.countByOptionId(option.getId());
            double percentage = totalVotes == 0 ? 0 : (votes * 100.0) / totalVotes;
            results.add(new PollResultDto(option.getText(), votes, percentage)
            );
        }

        return results;
    }
}
package com.itis.test4.repository;

import com.itis.test4.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findByActiveTrue();
}

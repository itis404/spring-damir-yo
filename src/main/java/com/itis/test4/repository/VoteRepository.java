package com.itis.test4.repository;

import com.itis.test4.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("""
            SELECT COUNT(v) > 0
            FROM Vote v
            WHERE v.userId = :userId
            AND v.option.poll.id = :pollId
            """)
    boolean existsByUserIdAndOptionPollId(
            Long userId,
            Long pollId
    );
    long countByOptionId(Long optionId);
}
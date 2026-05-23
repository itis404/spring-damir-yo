package com.itis.test4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;
    private Long userId;
    private LocalDateTime votedAt;

    @PrePersist
    public void init() {
        votedAt = LocalDateTime.now();
    }
}
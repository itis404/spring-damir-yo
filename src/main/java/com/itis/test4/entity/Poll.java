package com.itis.test4.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "polls")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private LocalDateTime createdAt;
    private boolean active = true;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Option> options;
}
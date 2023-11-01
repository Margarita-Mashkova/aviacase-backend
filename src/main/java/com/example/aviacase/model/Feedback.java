package com.example.aviacase.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 1000)
    private String text;

    @NonNull
    private int rate;

    @NonNull
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    @NonNull
    private Tour tour;

    public Feedback(String text, @NonNull int rate, @NonNull User user, @NonNull Tour tour) {
        this.text = text;
        this.rate = rate;
        this.user = user;
        this.tour = tour;
    }
}

package com.example.aviacase.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@IdClass(PurchaseId.class)
public class Purchase {
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @NonNull
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "tour_id")
//    @NonNull
//    private Tour tour;

    @Id
    private Long userId;
    @Id
    private Long tourId;

    //Date of purchase
    @NonNull
    private Date date;
    @NonNull
    private int nights;
    @NonNull
    private int tourists;
    @NonNull
    private float sum;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

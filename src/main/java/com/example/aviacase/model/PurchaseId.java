package com.example.aviacase.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class PurchaseId implements Serializable {
    private Long userId;
    private Long tourId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseId that = (PurchaseId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(tourId, that.tourId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, tourId);
    }
}

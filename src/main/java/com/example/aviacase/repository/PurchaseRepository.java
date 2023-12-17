package com.example.aviacase.repository;

import com.example.aviacase.model.Purchase;
import com.example.aviacase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUserOrderByDateDesc(User user);
    List<Purchase> findAllByDateBetween(Date dateStart, Date dateEnd);
//    List<Purchase> countAllByTourId(Long tourId);
}

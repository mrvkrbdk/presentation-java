package com.assignment.presentationprogrammer.repository;

import com.assignment.presentationprogrammer.model.Lightning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightningRepository extends JpaRepository<Lightning,Long> {
}

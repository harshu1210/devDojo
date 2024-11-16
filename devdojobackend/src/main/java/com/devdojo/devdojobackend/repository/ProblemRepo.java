package com.devdojo.devdojobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.devdojo.devdojobackend.models.Problem;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Repository
public interface ProblemRepo extends JpaRepository<Problem, Long> {

    @Query("Select p from Problem p where p.pid = :pid")
    public Optional<Problem> findByPID(@Param("pid") Long pID);

    @Modifying
    @Transactional
    @Query("Delete from Problem p where p.pid = :pid")
    public void deleteByPid(@Param("pid") Long pID);
}
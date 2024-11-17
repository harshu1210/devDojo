package com.devdojo.devdojobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devdojo.devdojobackend.models.problemContent;

import jakarta.transaction.Transactional;

@Repository
public interface problemContentRepo extends JpaRepository<problemContent, Long> {

    @Query("Select pc from problemContent pc where pcid = :pcid")
    public Optional<problemContent> findByPcid(@Param("pcid") Long pcid);

    @Modifying
    @Transactional
    @Query("Delete from problemContent pc where pcid = :pcid")
    public void deleteByPcid(@Param("pcid") Long pcid);

}

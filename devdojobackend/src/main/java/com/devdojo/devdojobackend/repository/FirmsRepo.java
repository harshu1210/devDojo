package com.devdojo.devdojobackend.repository;

import com.devdojo.devdojobackend.models.Firms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
public interface FirmsRepo extends JpaRepository<Firms, Long> {

    @Query("SELECT f FROM Firms f WHERE f.cid = :cid")
    public Optional<Firms> findByCid(@Param("cid") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Firms f WHERE f.cid = :cid")
    public void deleteByCid(@Param("cid") Long id);

}

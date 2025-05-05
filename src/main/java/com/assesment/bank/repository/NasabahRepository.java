package com.assesment.bank.repository;

import com.assesment.bank.entity.Nasabah;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NasabahRepository extends JpaRepository<Nasabah,String>{
    @Query("""
            SELECT nas
            FROM Nasabah nas
            WHERE
                nas.nomorKtp = %:nomorKtp%
            """)
    List<Nasabah> getAllByKtp(String nomorKtp);
}

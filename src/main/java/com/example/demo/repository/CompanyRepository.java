package com.example.demo.repository;

import com.example.demo.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

    @Query(value = "select * from company \n" +
            "order by latest_price desc limit 5", nativeQuery = true)
    List<CompanyEntity> findTop5ByLatestPrice();

    @Query(value = """
            select * 
            from company
            order by Abs(change_percent)  desc
            limit 5
            """, nativeQuery = true)
    List<CompanyEntity> findTop5ByChangePercent();

    @Query("select c from company c")
    List<CompanyEntity> getAllCompanies();
}

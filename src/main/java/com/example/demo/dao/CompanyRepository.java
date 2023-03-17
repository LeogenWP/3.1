package com.example.demo.dao;

import com.example.demo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query(value = "select  * from company \n" +
            "order by latest_price desc limit 5", nativeQuery = true)
    List<Company> findTop5ByLatestPrice();

    @Query(value = """
            select * 
            from company
            order by Abs(change_percent)  desc
            limit 5
            """, nativeQuery = true)
    List<Company> findTop5ByChangePercent();

    @Query("select c from company c")
    List<Company> getAllCompanies();
}

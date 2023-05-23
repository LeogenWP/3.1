package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name="company")
@Table(name = "company", schema = "stocks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CompanyEntity {
    @Id
    private String symbol;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "change_percent")
    private float changePercent;
    @Column(name = "latest_price")
    private float latestPrice;

    @Override
    public String toString() {
        return "Company{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", changePercent=" + changePercent +
                ", latestPrice=" + latestPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity company = (CompanyEntity) o;
        return Float.compare(company.changePercent, changePercent) == 0 && Float.compare(company.latestPrice, latestPrice) == 0 && symbol.equals(company.symbol) && companyName.equals(company.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, companyName, changePercent, latestPrice);
    }
}

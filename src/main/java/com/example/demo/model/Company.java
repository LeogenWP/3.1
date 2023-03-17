package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity(name="company")
public class Company {
    @Id
    private String symbol;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "change_percent")
    private float changePercent;
    @Column(name = "latest_price")
    private float latestPrice;

    public Company(String symbol, String companyName, float changePercent, float latestPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.changePercent = changePercent;
        this.latestPrice = latestPrice;
    }

    public Company() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public float getChangePercent() {
        return changePercent;
    }

    public float getLatestPrice() {
        return latestPrice;
    }

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
        Company company = (Company) o;
        return Float.compare(company.changePercent, changePercent) == 0 && Float.compare(company.latestPrice, latestPrice) == 0 && symbol.equals(company.symbol) && companyName.equals(company.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, companyName, changePercent, latestPrice);
    }
}

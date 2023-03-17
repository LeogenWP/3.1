package com.example.demo.model;

import java.util.Date;

public class Symbol {
    private String symbol;
    private Date date;
    private boolean isEnable;
    private String name;

    public Symbol(String symbol, Date date, boolean isEnable, String name) {
        this.symbol = symbol;
        this.date = date;
        this.isEnable = isEnable;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Date getDate() {
        return date;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbol='" + symbol + '\'' +
                ", date=" + date +
                ", isEnable=" + isEnable +
                ", name='" + name + '\'' +
                '}';
    }
}

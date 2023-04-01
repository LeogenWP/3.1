package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Symbol {
    private String symbol;
    private Date date;
    private boolean isEnable;
    private String name;

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

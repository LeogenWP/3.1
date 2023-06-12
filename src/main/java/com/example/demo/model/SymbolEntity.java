package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SymbolEntity {
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

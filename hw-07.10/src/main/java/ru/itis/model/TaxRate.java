package ru.itis.model;

import java.math.BigDecimal;

public class TaxRate {

    private Integer id;
    private BigDecimal rate;

    public TaxRate() {
    }

    public TaxRate(Integer id, BigDecimal rate) {
        this.id = id;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
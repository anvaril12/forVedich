package ru.itis.service;

import ru.itis.model.TaxRate;
import ru.itis.repository.TaxRateRepository;

import java.math.BigDecimal;

public class TaxRateService {

    private final TaxRateRepository taxRateRepository;

    public TaxRateService(TaxRateRepository taxRateRepository) {
        this.taxRateRepository = taxRateRepository;
    }

    public TaxRate getRate() {
        return taxRateRepository.getRate();
    }

    public void updateRate(BigDecimal newRate) {
        validateRate(newRate);
        taxRateRepository.updateRate(newRate);
    }

    private void validateRate(BigDecimal rate) {

        if (rate == null) {
            throw new IllegalArgumentException(
                    "Ставка не может быть пустой"
            );
        }

        if (rate.compareTo(BigDecimal.ONE) < 0 ||
                rate.compareTo(new BigDecimal("100")) > 0) {

            throw new IllegalArgumentException(
                    "Ставка должна быть от 1 до 100"
            );
        }

        if (rate.scale() > 2) {
            throw new IllegalArgumentException(
                    "Ставка может содержать максимум 2 знака после запятой"
            );
        }
    }
}
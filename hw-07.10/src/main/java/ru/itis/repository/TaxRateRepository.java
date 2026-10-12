package ru.itis.repository;

import ru.itis.model.TaxRate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxRateRepository {

    private final Connection connection;

    public TaxRateRepository(Connection connection) {
        this.connection = connection;
    }

    public TaxRate getRate() {
        String sql = "SELECT id, rate FROM tax_rate WHERE id = 1";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                BigDecimal rate = resultSet.getBigDecimal("rate");

                return new TaxRate(id, rate);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRate(BigDecimal newRate) {
        String sql = "UPDATE tax_rate SET rate = ? WHERE id = 1";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBigDecimal(1, newRate);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
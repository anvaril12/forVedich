package ru.itis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.model.TaxRate;
import ru.itis.repository.TaxRateRepository;
import ru.itis.service.TaxRateService;
import ru.itis.util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/api/rate")
public class RateServlet extends HttpServlet {

    private Connection connection;
    private TaxRateService taxRateService;

    @Override
    public void init() throws ServletException {
        connection = DatabaseConnection.getConnection();

        TaxRateRepository taxRateRepository =
                new TaxRateRepository(connection);

        taxRateService =
                new TaxRateService(taxRateRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        TaxRate taxRate = taxRateService.getRate();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                "{\"rate\": " + taxRate.getRate() + "}"
        );
    }

    @Override
    public void destroy() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
package ru.itis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.model.TaxRate;
import ru.itis.repository.TaxRateRepository;
import ru.itis.service.TaxRateService;
import ru.itis.util.DatabaseConnection;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private Connection connection;
    private TaxRateService taxRateService;

    @Override
    public void init() throws ServletException {
        connection = DatabaseConnection.getConnection();

        TaxRateRepository taxRateRepository = new TaxRateRepository(connection);
        taxRateService = new TaxRateService(taxRateRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        showPage(request, response, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String rateParameter = request.getParameter("rate");

        try {
            BigDecimal newRate = new BigDecimal(rateParameter);

            taxRateService.updateRate(newRate);

            showPage(request, response, "Ставка успешно изменена");

        } catch (NumberFormatException e) {
            showPage(request, response, "Введите корректное число");

        } catch (IllegalArgumentException e) {
            showPage(request, response, e.getMessage());
        }
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("admin") != null;
    }

    private void showPage(HttpServletRequest request,
                          HttpServletResponse response,
                          String message) throws ServletException, IOException {

        TaxRate taxRate = taxRateService.getRate();

        request.setAttribute("rate", taxRate.getRate());
        request.setAttribute("message", message);

        request.getRequestDispatcher("/jsp/admin.jsp").forward(request, response);
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
package cw1.dao;

import cw1.Entity.Employees;
import cw1.dao.interfaces.EmployeeDao;
import cw1.mappers.EmployeesMapper;
import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private final Connection connection;
    private final EmployeesMapper employeesMapper;

    public EmployeeDaoImpl(Connection connection, EmployeesMapper employeesMapper) {
        this.connection = connection;
        this.employeesMapper = employeesMapper;
    }

    private static final String SQL_FIND_ALL = "select * from employees";
    private static final String SQL_SAVE = "insert into employees(name, position) values(?, ?)";

    @SneakyThrows
    @Override
    public List<Employees> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return employeesMapper.toEntity(resultSet);
        }
    }

    @SneakyThrows
    @Override
    public void save(Employees employees) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE)) {
            preparedStatement.setString(1, employees.getName());
            preparedStatement.setString(2, employees.getPosition());
            preparedStatement.executeUpdate();
        }
    }
}

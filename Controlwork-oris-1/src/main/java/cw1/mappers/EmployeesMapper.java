package cw1.mappers;

import cw1.Entity.Employees;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeesMapper {

    @SneakyThrows
    public List<Employees> toEntity(ResultSet resultSet) {
        List<Employees> result = new ArrayList<>();

        while(resultSet.next()) {
            Employees employees = new Employees(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("position")
            );
            result.add(employees);
        }
        return result;
    }
}

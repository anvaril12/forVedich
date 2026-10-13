package cw1.mappers;

import cw1.Entity.Employees;
import cw1.Entity.Enums.Status;
import cw1.Entity.Tasks;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TasksMapper {

    @SneakyThrows
    public List<Tasks> toEntity(ResultSet resultSet) {
        List<Tasks> list = new ArrayList<>();
        while(resultSet.next()) {
            Employees employee = null;
            if (resultSet.getObject("employee_id") != null) {
                employee = new Employees(
                        resultSet.getInt("employee_id"),
                        resultSet.getString("name"),
                        resultSet.getString("position")
                );
            }

            Tasks tasks = new Tasks(
                    resultSet.getInt("id"),
                    resultSet.getString("taskname"),
                    Status.valueOf(resultSet.getString("status")),
                    employee,
                    resultSet.getObject("deadline", LocalDate.class)
            );
            list.add(tasks);
        }
        return list;
    }
}

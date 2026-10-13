package cw1.dao;

import cw1.Entity.Enums.Status;
import cw1.Entity.Tasks;
import cw1.dao.interfaces.TaskDao;
import cw1.mappers.TasksMapper;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    private final Connection connection;
    private final TasksMapper tasksMapper;

    public TaskDaoImpl(Connection connection, TasksMapper tasksMapper) {
        this.connection = connection;
        this.tasksMapper = tasksMapper;
    }

    private static final String SQL_FIND_ALL = "select t.*, em.name, em.position from tasks t left join employees em on em.id = t.employee_id";
    private static final String SQL_SAVE = "insert into tasks (taskname, status, employee_id, deadline) values (?,?,?,?)";
    private static final String SQL_ASSIGN_EMPLOYEE = "update tasks set employee_id = ? where id = ?";
    private static final String SQL_UPDATE_STATUS = "update tasks set status = ? where id = ?";
    private static final String SQL_FIND_OVERDUE = SQL_FIND_ALL + " where t.deadline < current_date and t.status <> 'DONE'";

    @SneakyThrows
    @Override
    public List<Tasks> findAll() {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            return tasksMapper.toEntity(resultSet);
        }
    }

    @SneakyThrows
    @Override
    public void save(Tasks tasks) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE)) {
            preparedStatement.setString(1, tasks.getTaskname());
            preparedStatement.setString(2, tasks.getStatus() != null ? tasks.getStatus().name() : Status.NEW.name());
            if (tasks.getEmployee() != null) {
                preparedStatement.setInt(3, tasks.getEmployee().getId());
            } else {
                preparedStatement.setNull(3, Types.INTEGER);
            }
            preparedStatement.setObject(4, tasks.getDeadline());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public void assigneEmployee(int taskId, int employeeId) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_ASSIGN_EMPLOYEE)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, taskId);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public void updateStatus(int taskId, Status status) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS)) {
            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, taskId);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public List<Tasks> findOverdue() {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_OVERDUE);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            return tasksMapper.toEntity(resultSet);
        }
    }
}

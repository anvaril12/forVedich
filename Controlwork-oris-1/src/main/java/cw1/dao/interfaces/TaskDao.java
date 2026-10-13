package cw1.dao.interfaces;

import cw1.Entity.Enums.Status;
import cw1.Entity.Tasks;

import java.util.List;

public interface TaskDao {
    List<Tasks> findAll();
    void save(Tasks tasks);
    void assigneEmployee(int taskId, int employeeId);
    void updateStatus(int taskId, Status status);
    List<Tasks> findOverdue();
}

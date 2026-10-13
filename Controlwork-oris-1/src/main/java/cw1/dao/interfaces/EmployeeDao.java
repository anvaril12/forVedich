package cw1.dao.interfaces;

import cw1.Entity.Employees;

import java.util.List;

public interface EmployeeDao {
    List<Employees> findAll();
    void save(Employees employees);
}

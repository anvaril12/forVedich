package cw1;

import cw1.Entity.Employees;
import cw1.Entity.Enums.Status;
import cw1.Entity.Tasks;
import cw1.bdconnect.BdConnection;
import cw1.dao.EmployeeDaoImpl;
import cw1.dao.TaskDaoImpl;
import cw1.mappers.EmployeesMapper;
import cw1.mappers.TasksMapper;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Connection connection = BdConnection.getConnection();
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(connection, new EmployeesMapper());
        TaskDaoImpl taskDao = new TaskDaoImpl(connection, new TasksMapper());
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("1-сотрудники 2-добавить сотр. 3-задачи " +
                    "4-добавить задачу 5-назначить 6-статус 7-просроченные 0-выход");
            try {
                int choise = Integer.parseInt(scanner.nextLine());

                switch (choise) {
                    case 1:
                        System.out.println(employeeDao.findAll());
                        break;
                    case 2:
                        System.out.print("Имя: ");
                        String name = scanner.nextLine();
                        System.out.print("Должность: ");
                        String position = scanner.nextLine();
                        employeeDao.save(new Employees(0, name, position));
                        break;
                    case 3:
                        System.out.println(taskDao.findAll());
                        break;
                    case 4:
                        System.out.print("Название: ");
                        String title = scanner.nextLine();
                        System.out.print("Дедлайн (ГГГГ-ММ-ДД): ");
                        LocalDate deadline = LocalDate.parse(scanner.nextLine());
                        taskDao.save(new Tasks(0, title, Status.NEW, null, deadline));
                        break;

                    case 5:
                        System.out.print("id задачи: ");
                        int taskId = Integer.parseInt(scanner.nextLine());
                        System.out.print("id сотрудника: ");
                        int employeeId = Integer.parseInt(scanner.nextLine());
                        taskDao.assigneEmployee(taskId, employeeId);
                        break;

                    case 6:
                        System.out.print("id задачи: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Статус (NEW, IN_PROGRESS, DONE): ");
                        Status status = Status.valueOf(scanner.nextLine());
                        taskDao.updateStatus(id, status);
                        break;

                    case 7:
                        System.out.println(taskDao.findOverdue());
                        break;
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }
}

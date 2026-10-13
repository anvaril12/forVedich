package cw1.Entity;

import cw1.Entity.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {
    private int id;
    private String taskname;
    private Status status;
    private Employees employee;
    private LocalDate deadline;
}

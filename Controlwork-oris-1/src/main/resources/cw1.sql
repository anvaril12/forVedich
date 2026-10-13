create table employees (
    id serial primary key,
    name varchar not null,
    position varchar not null
);

create table tasks (
    id serial primary key,
    taskname varchar not null,
    status varchar not null default 'NEW' check ( status in ('NEW', 'IN_PROGRESS', 'DONE') ),
    employee_id int references employees(id),
    deadline date
);
create schema calculator;

create table credit(
                       id int AUTO_INCREMENT primary key,
                       period_credit double,
                       amount double,
                       cost_percentage double,
                       status varchar(50) default 'Payments is not calculated'
);

create table payment(
                        id int AUTO_INCREMENT primary key,
                        month int,
                        debt_before_payment numeric(10, 2),
                        percent_payment numeric(10, 2),
                        debt_payment numeric(10, 2),
                        payment numeric(10, 2),
                        debt_after_payment numeric(10, 2),
                        credit_id int references credit(id)
);

insert into credit(period_credit, amount, cost_percentage) values (12, 500000, 17.5);
insert into credit(period_credit, amount, cost_percentage) values (20, 500000, 17.5);
insert into credit(period_credit, amount, cost_percentage) values (30, 500000, 17.5);


create user swdemo identified by 123456;

grant connect to swdemo;
grant resource to swdemo;
grant dba to swdemo;

ALTER USER swdemo quota unlimited on USERS;


create table T_USER
(
  ID      NUMBER(10) not null,
  NAME           VARCHAR2(500) not null,
  USER_AUTHORITY      VARCHAR2(500)
);
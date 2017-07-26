/*******************************************************************************
   Reinbursement Requests
   Script: TRMS.sql
   Description: Creates and populates the TRMS Database
   DB Server: Oracle
   Author: Luis Rocha
   License: http://www.codeplex.com/ChinookDatabase/license
********************************************************************************/
/*******************************************************************************
   Drop database if it exists
********************************************************************************/
DROP USER trms CASCADE;


/*******************************************************************************
   Create database
********************************************************************************/
CREATE USER trmslogin
IDENTIFIED BY projectone
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to trms;
GRANT resource to trms;
GRANT create session TO trms;
GRANT create table TO trms;
GRANT create view TO trms;



conn trmslogin/projectone


/*******************************************************************************
   Create Tables
********************************************************************************/
CREATE TABLE Employee
(
	EmployeeID number primary key,
	Reimbursement_total number,
	Direct_Supervisor number,
	DepartmentID number

);
CREATE TABLE Department 
(
	DepartmentID number primary key,
	Name varchar2(8) not null,
	Department_Head number	
);
CREATE TABLE Form_Submissions
(
	FormID number primary key,
	Date_Made DATE,
	Full_Cost number,
	Grade_Format_ID number,   -- Ask Nick if Grade format reference table is provided
	Event_Type number,        --Not sure what we'll put in Even types table 
	Description varchar2(3999),
	Status number
);
CREATE TABLE Approval_Stage --
(
	StageID number primary key,
	Descript varchar2(128)
);
-- Employee's department foreign key
ALTER TABLE Employee ADD CONSTRAINT FK_DepartmentID
    FOREIGN KEY (DepartmentID) REFERENCES Department (DepartmentID);

ALTER TABLE Department ADD CONSTRAINT FK_Department_Head
    FOREIGN KEY (Department_Head) REFERENCES Employee (EmployeeID);

ALTER TABLE Form_Submissions ADD CONSTRAINT FK_GradeFormatID
	FOREIGN KEY (Grade_Format_ID) REFERENCES Approval_Stage (StageID);




create sequence employee_seq;
create or replace trigger employee_pk_trig
before insert or update on Employee
for each row
begin
    if INSERTING then
        :new.employeeid := employee_seq.NEXTVAL;
    elsif UPDATING then
        :new.employeeid := :old.employeeid;
    end if;
end;
/
create sequence department_seq;
create or replace trigger department_pk_trig
before insert or update on Department
for each row
begin
    if INSERTING then
        :new.departmentID := department_seq.NEXTVAL;
    elsif UPDATING then
        :new.departmentID := :old.departmentID;
    end if;
end;
/
create sequence form_seq;
create or replace trigger department_pk_trig
before insert or update on Form_Submissions
for each row
begin
    if INSERTING then
        :new.FormID := form_seq.NEXTVAL;
    elsif UPDATING then
        :new.FormID := :old.FormID;
    end if;
end;
/





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
--DROP TABLE EMPLOYEE;
--DROP TABLE APPROVAL_STATUS;
--DROP TABLE DEPARTMENT;
--DROP TABLE FORM_SUBMISSIONS;
--DROP TABLE GRADE_FORMAT;

/*******************************************************************************
   Create database
********************************************************************************/
CREATE USER trms
IDENTIFIED BY projectone
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to trms;
GRANT resource to trms;
GRANT create session TO trms;
GRANT create table TO trms;
GRANT create view TO trms;



conn trms/projectone


/*******************************************************************************
   Create Tables
********************************************************************************/
CREATE TABLE Employee
(
  EmployeeID number primary key,
  firstName varchar2(32),
  lastName varchar2(32),
  userName varchar2(32),
  pass varchar2(32),
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
  EmployeeID number,
  Date_Made DATE,
  Full_Cost number,
  Grade_Format_ID number,   -- Ask Nick if Grade format reference table is provided
  Descript varchar2(3999),
  Status number
);
CREATE TABLE Approval_Status
(
  StatusID number primary key,
  Descript varchar2(128)
);
CREATE TABLE Grade_Format
(
  GradeID number primary key,
  Grade_Percent number(8, 2) not null,
  Grade_Descript varchar2(32)
);
CREATE TABLE Messages(
  MessageID number primary key,
  RecieverID number,
  SenderID number,
  Send_Date DATE,
  Message varchar2(2048)
);
---- Employee's department foreign key
--ALTER TABLE Employee ADD CONSTRAINT FK_DepartmentID
--    FOREIGN KEY (DepartmentID) REFERENCES Department (DepartmentID) ON DELETE CASCADE;
--
----Department's Employee Foreign key (for department head)
--ALTER TABLE Department ADD CONSTRAINT FK_Department_Head
--    FOREIGN KEY (Department_Head) REFERENCES Employee (EmployeeID) ON DELETE CASCADE;
--
----Form submissions Employee submission
--ALTER TABLE Form_Submissions ADD CONSTRAINT FK_EmployeeID
--  FOREIGN KEY (EmployeeID) REFERENCES Employee (EmployeeID) ON DELETE CASCADE;

  
--Form_Submissions Grade format FK 
--ALTER TABLE Form_Submissions DROP CONSTRAINT FK_GradeFormatID;
  --FOREIGN KEY (Grade_Format_ID) REFERENCES Approval_Stage (StageID) ON DELETE CASCADE;


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
create or replace trigger form_pk_trig
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
TRUNCATE TABLE EMPLOYEE;
TRUNCATE TABLE APPROVAL_STATUS;
TRUNCATE TABLE Department;
TRUNCATE TABLE FORM_SUBMISSIONS;
TRUNCATE TABLE GRADE_FORMAT;

INSERT INTO Department (DepartmentID, Name, Department_Head) VALUES (1, 'HR', null);
INSERT INTO Department (DepartmentID, Name, Department_Head) VALUES (2, 'BenCo', null);

INSERT INTO Employee (EmployeeID, firstName, lastName, userName, pass, Reimbursement_total, Direct_Supervisor, DepartmentID) VALUES (1,'Basic', 'Employee', 'Emp', 'pass', 1000, 2, 1);
INSERT INTO Employee (EmployeeID, firstName, lastName, userName, pass, Reimbursement_total, Direct_Supervisor, DepartmentID) VALUES (2, 'Direct', 'Supervisor', 'DS', 'pass', 1000, null, 1);
INSERT INTO Employee (EmployeeID, firstName, lastName, userName, pass, Reimbursement_total, Direct_Supervisor, DepartmentID) VALUES (3, 'Department', 'Head', 'DH', 'pass', 1000, null, 1);
INSERT INTO Employee (EmployeeID, firstName, lastName, userName, pass, Reimbursement_total, Direct_Supervisor, DepartmentID) VALUES (4, 'Benco', 'regularBEN', 'Benco', 'pass', 1000, null, 2);


INSERT INTO FORM_SUBMISSIONS (FormID, EmployeeID, Date_Made, Full_Cost, Grade_Format_ID, Descript, Status) VALUES (1, 1, CURRENT_TIMESTAMP, 200, null, 'Testing', 1);

INSERT INTO Approval_Status (StatusID, Descript) VALUES (-1, 'Request Canceled');
INSERT INTO Approval_Status (StatusID, Descript) VALUES (0, 'Request Denied');
INSERT INTO Approval_Status (StatusID, Descript) VALUES (1, 'No approvals');
INSERT INTO Approval_Status (StatusID, Descript) VALUES (2, 'Direct Supervisor approval');
INSERT INTO Approval_Status (StatusID, Descript) VALUES (3, 'Head of Department approval');
INSERT INTO Approval_Status (StatusID, Descript) VALUES (4, 'BenCo Approval');
INSERT INTO Approval_Status (StatusID, Descript) VALUES (5, 'Grade Approved');

INSERT INTO Grade_Format (GradeID, Grade_Percent, Grade_Descript) VALUES (1, 00.80, 'University Course');
INSERT INTO Grade_Format (GradeID, Grade_Percent, Grade_Descript) VALUES (2, 00.60, 'Seminar');
INSERT INTO Grade_Format (GradeID, Grade_Percent, Grade_Descript) VALUES (3, 00.75, 'Certification Preperation Class');
INSERT INTO Grade_Format (GradeID, Grade_Percent, Grade_Descript) VALUES (4, 01.00, 'Certification');
INSERT INTO Grade_Format (GradeID, Grade_Percent, Grade_Descript) VALUES (5, 00.90, 'Technical Training');
INSERT INTO Grade_Format (GradeID, Grade_Percent, Grade_Descript) VALUES (6, 00.30, 'Other');
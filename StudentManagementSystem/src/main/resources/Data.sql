!-- insert users

insert into user_details (id, name, password, role, username)
values (1, 'student1', 'student1', 'student1', 'Student', 'student1');

insert into user_details (id, name, password, role, username)
values (2, 'student2', 'student2', 'student2', 'Student', 'student2');

insert into user_details (id, name, password, role, username)
values (3, 'teacher', 'teacher', 'teacher', 'Teacher', 'teacher');


!-- insert courses

INSERT INTO university_db.course
(id, description, end_date, name, semester, start_date)
VALUES(0, 'This is computer science course', '2022-06-24', 'Computer Science', 'SUMMER', '2022-06-03');


INSERT INTO university_db.course
(id, description, end_date, name, semester, start_date)
VALUES(0, 'Management of people', '2022-06-24', 'Management', 'SUMMER', '2022-06-03');

INSERT INTO university_db.course
(id, description, end_date, name, semester, start_date)
VALUES(0, 'Database and sql quries related knowledge', '2022-06-24', 'Database', 'SUMMER', '2022-06-03');
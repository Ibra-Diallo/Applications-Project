REPLACE INTO `role` VALUES
(1,'Student'),
(2, 'Teacher'),
(3, 'Admin');



insert into user_details (id, name, password, username)
values (1, 'student1', '$2a$10$TGbspBEb7gs6TijErkdrruZ/s2P194eu47zz2hhHugk/YZfjR.fAO', 'student1');

insert into user_details (id, name, password, username)
values (2, 'student2', '$2a$10$HhMS7mj87ORQuC7zEl6WQeG3uJ5UkXMghxbg7/KaPC79xurwYPImW', 'student2');

insert into user_details (id, name, password, username)
values (3, 'teacher', '$2a$10$AO42V/rNI0UGVCsWsc8iD.lyKeIlYUQikgyJL3..Fwtsx.Au7/TYG', 'teacher');

insert into user_details (id, name, password, username)
values (4, 'admin', '$2a$10$z0wUZ5qaHYZtR27SdksLHeyRRsvwiPY0u8VZS1Ks5Q9r/9LPFxx8y', 'admin');


INSERT INTO university_db.user_role
(id, role_id)
VALUES(1, 1),
(2, 1),
(3, 2),
(4, 3);




INSERT INTO university_db.course
(id, description, end_date, name, semester, start_date)
VALUES(0, 'This is computer science course', '2022-06-24', 'Computer Science', 'SUMMER', '2022-06-03');


INSERT INTO university_db.course
(id, description, end_date, name, semester, start_date)
VALUES(1, 'Management of people', '2022-06-24', 'Management', 'SUMMER', '2022-06-03');

INSERT INTO university_db.course
(id, description, end_date, name, semester, start_date)
VALUES(2, 'Database and sql quries related knowledge', '2022-06-24', 'Database', 'SUMMER', '2022-06-03');

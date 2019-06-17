
--Создание ролей
INSERT INTO ROLE (id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLE (id, name) VALUES (2, 'STUDENT');

--Создание пользователей
INSERT INTO USVER (id, login, password, second_name, first_name, middle_name, study_group) VALUES (1,'admin', '$2a$10$0n6VBVR11iAJWsNLuYikEOQT5WuXUTzj.nl6KQDDdb1Ju4B7CIBg6', 'Ivan', 'Ivanov', 'Ivanovich', '');
INSERT INTO USVER (id, login, password, second_name, first_name, middle_name, study_group) VALUES (2, 'student', '$2a$10$.oWfiKU9DsX4rNQDz29d8O/Rdgd5gPrsB4yF2iMy13DEt8oMhNeCG', 'Ивкин', 'Станислав', 'Васильевич', 'ИВТ-42');

--Привязка пользователей к ролям
INSERT INTO USVER_ROLES (usver_id, roles_id) VALUES (1, 1);
INSERT INTO USVER_ROLES (usver_id, roles_id) VALUES (2, 2);

--Создание прав доступа
--Привязка прав доступа к ролям




INSERT INTO LAB_WORK (id, name, number) VALUES (1, 'Лабораторная работа №1	', 1);
INSERT INTO TASK (id, name, number, lab_work_id) VALUES (1, 'Тест', 0, 1);
INSERT INTO TASK (id, name, number, lab_work_id) VALUES (2, 'Задание №1', 1, 1);
INSERT INTO QUESTION (id, answer, attempts_number, name, number, question, question_type, score, skip_question, task_id) VALUES (1, '{"text":"123"}', 1, '123', 1, '<html dir="ltr"><head></head><body contenteditable="true"><p><font face="Segoe UI">123</font></p></body></html>', 'TEXT', 1, 'FALSE', 1);
INSERT INTO QUESTION (id, answer, attempts_number, name, number, question, question_type, score, skip_question, task_id) VALUES (2, '{"{"latexFormula":"\\sum_{ }^{ }\\prod_{ }^{ }\\int"}"}', 1, '456', 1, '<html dir="ltr"><head></head><body contenteditable="true"></body></html>', 'FORMULA', 1, 'FALSE', 2);



INSERT INTO USVER_PROGRESS_LAB_WORK (id, end_time, start_time, lab_work_id, usver_id) VALUES (1, 1560761303850, 1560757703835, 1, 2);
INSERT INTO USVER_PROGRESS_TASK  (id, task_id) VALUES (1, 2);
INSERT INTO USVER_PROGRESS_TASK  (id, task_id) VALUES (2, 1);
INSERT INTO USVER_PROGRESS_QUESTION (id, attempts_number, score , question_id) VALUES (1, 0, 0, 2);
INSERT INTO USVER_PROGRESS_QUESTION (id, attempts_number, score , question_id) VALUES (2, 1, 1, 1);



INSERT INTO USVER_PROGRESS_TASK_USVER_PROGRESS_QUESTIONS (USVER_PROGRESS_TASK_ID , USVER_PROGRESS_QUESTIONS_ID ) VALUES (1, 1);
INSERT INTO USVER_PROGRESS_TASK_USVER_PROGRESS_QUESTIONS (USVER_PROGRESS_TASK_ID , USVER_PROGRESS_QUESTIONS_ID ) VALUES (2, 2);
INSERT INTO USVER_PROGRESS_LAB_WORK_USVER_PROGRESS_TASKS (USVER_PROGRESS_LAB_WORK_ID , USVER_PROGRESS_TASKS_ID ) VALUES (1, 1);
INSERT INTO USVER_PROGRESS_LAB_WORK_USVER_PROGRESS_TASKS (USVER_PROGRESS_LAB_WORK_ID , USVER_PROGRESS_TASKS_ID ) VALUES (1, 2);

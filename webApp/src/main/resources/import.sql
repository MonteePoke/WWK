
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

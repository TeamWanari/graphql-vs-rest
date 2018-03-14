INSERT INTO USER (id, login, password) VALUES (1, 'admin', 'supersecret');
INSERT INTO USER (id, login, password) VALUES (2, 'john', 'supersecret');
INSERT INTO USER (id, login, password) VALUES (3, 'clara', 'supersecret');
INSERT INTO USER (id, login, password) VALUES (4, 'abba', 'supersecret');

INSERT INTO ROLE (key) VALUES ('ADMIN_ROLE');
INSERT INTO ROLE (key) VALUES ('COMMON_ROLE');

INSERT INTO PRIVILEGE (key) VALUES ('VIEW_PRINTER_PRIVILEGE');
INSERT INTO PRIVILEGE VALUES ('DETONATE_PRINTER_PRIVILEGE');

INSERT INTO USER_ROLE (user_id, role_key) VALUES (1, 'ADMIN_ROLE');
INSERT INTO USER_ROLE (user_id, role_key) VALUES (1, 'COMMON_ROLE');
INSERT INTO USER_ROLE (user_id, role_key) VALUES (2, 'COMMON_ROLE');
INSERT INTO USER_ROLE (user_id, role_key) VALUES (3, 'COMMON_ROLE');
INSERT INTO USER_ROLE (user_id, role_key) VALUES (4, 'COMMON_ROLE');

INSERT INTO ROLE_PRIVILEGE (role_key, privilege_key) VALUES ('ADMIN_ROLE', 'DETONATE_PRINTER_PRIVILEGE');
INSERT INTO ROLE_PRIVILEGE (role_key, privilege_key) VALUES ('ADMIN_ROLE', 'VIEW_PRINTER_PRIVILEGE');
INSERT INTO ROLE_PRIVILEGE (role_key, privilege_key) VALUES ('COMMON_ROLE', 'VIEW_PRINTER_PRIVILEGE');

INSERT INTO PRINTER (id, owner_id, name, serial_number) VALUES (1, 1, 'Admin''s printer', 'b823b80d-3bfb-4159-ba90-6ee1c499d816');
INSERT INTO PRINTER (id, owner_id, name, serial_number) VALUES (2, 2, 'John''s printer', 'e59118c0-0048-45af-b365-ca6df8c246a3');
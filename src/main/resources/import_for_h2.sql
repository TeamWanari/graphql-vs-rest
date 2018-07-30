INSERT INTO user (id, login, password) VALUES (1, 'admin', 'supersecret');
INSERT INTO user (id, login, password) VALUES (2, 'john', 'supersecret');

INSERT INTO role (key_name) VALUES ('ADMIN_ROLE');
INSERT INTO role (key_name) VALUES ('COMMON_ROLE');

INSERT INTO privilege (key_name) VALUES ('VIEW_PRINTER_PRIVILEGE');
INSERT INTO privilege (key_name) VALUES ('DETONATE_PRINTER_PRIVILEGE');

INSERT INTO user_role (user_id, role_key) VALUES (1, 'ADMIN_ROLE');
INSERT INTO user_role (user_id, role_key) VALUES (1, 'COMMON_ROLE');
INSERT INTO user_role (user_id, role_key) VALUES (2, 'COMMON_ROLE');

INSERT INTO role_privilege (role_key, privilege_key) VALUES ('ADMIN_ROLE', 'DETONATE_PRINTER_PRIVILEGE');
INSERT INTO role_privilege (role_key, privilege_key) VALUES ('ADMIN_ROLE', 'VIEW_PRINTER_PRIVILEGE');
INSERT INTO role_privilege (role_key, privilege_key) VALUES ('COMMON_ROLE', 'VIEW_PRINTER_PRIVILEGE');

INSERT INTO printer (id, owner_id, name, serial_number) VALUES (1, 1, 'Admin''s printer', 'b823b80d-3bfb-4159-ba90-6ee1c499d816');
INSERT INTO printer (id, owner_id, name, serial_number) VALUES (2, 2, 'John''s printer No. 1', 'e59118c0-0048-45af-b365-ca6df8c246a3');
INSERT INTO printer (id, owner_id, name, serial_number) VALUES (3, 2, 'John''s printer No. 2', '7b845c53-5588-4d3a-b1b6-206fa10a0f07');
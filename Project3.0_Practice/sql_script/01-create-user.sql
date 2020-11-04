DROP USER IF EXISTS  'webstudent'@'localhost';
CREATE USER 'webstudent'@'localhost' IDENTIFIED BY 'webstudent';
GRANT ALL PRIVILEGES ON * . * TO 'webstudent'@'localhost';
-- > Tao acc webstudent de hoc ve JSP-Servlet
select user, host from mysql.user;
CREATE DATABASE `Vidyalaya`;

USE `Vidyalaya`;

CREATE TABLE `admin` (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    institution_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=10000;

DELIMITER $$
CREATE TRIGGER before_admin_insert
BEFORE INSERT ON admin
FOR EACH ROW
BEGIN
    SET NEW.username = CONCAT(
        UPPER(LEFT(NEW.institution_name, 1)),
        UPPER(RIGHT(NEW.institution_name, 1)),
        '-',
        (SELECT AUTO_INCREMENT FROM information_schema.TABLES 
         WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='admin')
    );
END$$
DELIMITER ;

CREATE TABLE `teacher` (
      id INT NOT NULL AUTO_INCREMENT,
      teacher_id VARCHAR(255) NOT NULL,
      admin_id INT NOT NULL,
      name VARCHAR(255) NOT NULL,
      email VARCHAR(255) NOT NULL,
      password VARCHAR(255) NOT NULL,
      PRIMARY KEY (id),
      FOREIGN KEY (admin_id) REFERENCES admin(id)
) ENGINE=InnoDB AUTO_INCREMENT=50000;

DELIMITER $$
CREATE TRIGGER `before_teacher_insert`
BEFORE INSERT ON `teacher`
FOR EACH ROW
BEGIN
    SET NEW.teacher_id = CONCAT(
        'TE-',
        (SELECT AUTO_INCREMENT FROM information_schema.TABLES 
         WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='teacher')
    );
END$$
DELIMITER ;

CREATE TABLE `student` (
      id INT NOT NULL AUTO_INCREMENT,
      student_id VARCHAR(255) NOT NULL,
      admin_id INT NOT NULL,
      name VARCHAR(255) NOT NULL,
      email VARCHAR(255) NOT NULL,
      password VARCHAR(255) NOT NULL,
      PRIMARY KEY (id),
      FOREIGN KEY (admin_id) REFERENCES admin(id)
) ENGINE=InnoDB AUTO_INCREMENT=20000;

DELIMITER $$
CREATE TRIGGER `before_student_insert`
BEFORE INSERT ON `student`
FOR EACH ROW
BEGIN
    SET NEW.student_id = CONCAT(
        'ST-',
        (SELECT AUTO_INCREMENT FROM information_schema.TABLES 
         WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='student')
    );
END$$
DELIMITER ;

CREATE TABLE `module` (
    code INT NOT NULL AUTO_INCREMENT,
    admin_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (code),
    FOREIGN KEY (admin_id) REFERENCES admin(id)
) ENGINE=InnoDB AUTO_INCREMENT=200;

CREATE TABLE `module_teacher` (
    teacher_id INT NOT NULL,
    module_code INT NOT NULL,
    PRIMARY KEY (teacher_id, module_code),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id) ON DELETE CASCADE,
    FOREIGN KEY (module_code) REFERENCES module(code) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `notice` (
    id INT NOT NULL AUTO_INCREMENT,
    admin_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    notice_type ENUM('admin', 'teacher', 'student') NOT NULL,
    effective_date VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (admin_id) REFERENCES admin(id)
) ENGINE=InnoDB;

CREATE TABLE `routine` (
    id INT NOT NULL AUTO_INCREMENT,
    weekday VARCHAR(255) NOT NULL,
    module_code INT NOT NULL,
	time VARCHAR(255) NOT NULL,
    routine_content TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (module_code) REFERENCES module(code) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `material` (
    id INT NOT NULL AUTO_INCREMENT,
    module_code INT NOT NULL,
    uploader_id INT NOT NULL,
    material_title VARCHAR(255) NOT NULL,
    material_text TEXT NOT NULL,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (module_code) REFERENCES module(code) ON DELETE CASCADE,
    FOREIGN KEY (uploader_id) REFERENCES teacher(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `attendance` (
    id INT NOT NULL AUTO_INCREMENT,
    admin_id INT NOT NULL,
    student_id INT NOT NULL,
    module_code INT NOT NULL,
    attendance_date DATE NOT NULL,
    attended BOOLEAN NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (admin_id) REFERENCES admin(id),
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (module_code) REFERENCES module(code) ON DELETE CASCADE
) ENGINE=InnoDB;
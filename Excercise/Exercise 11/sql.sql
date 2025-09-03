CREATE TABLE students (
	id VARCHAR (10) PRIMARY KEY,
    name VARCHAR (100) NOT NULL,students
    subject VARCHAR (100) NOT NULL
    );
ALTER TABLE students MODIFY COLUMN subject VARCHAR(100) NOT NULL;
INSERT INTO students (id, name, subject) VALUES
('SV01', 'Nguyễn Văn A', 'AI'),
('SV02', 'Trần Thị B', 'IT');

INSERT INTO students (id, name, subject) VALUES
('SV03', 'Nguyễn Văn C', 'SE'),
('SV04', 'Trần Thị D', 'SE');


SELECT * FROM students;

    
USE springweb2;

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    dept_id INT,
    salary INT,
    hire_date DATE,
    email VARCHAR(100)
);
-- 부서 테이블 생성
DROP TABLE IF EXISTS department;
CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(50)
);

-- 샘플 데이터
INSERT INTO department VALUES
(1, '개발팀'),
(2, '기획팀'),
(3, '디자인팀');

INSERT INTO employee (name, dept_id, salary, hire_date, email) VALUES
('유재석', 1, 5000, '2023-01-10', 'yu@test.com'),
('강호동', 2, 4000, '2024-02-12', 'kang@test.com'),
('신동엽', 1, 7000, '2022-06-05', 'shin@test.com'),
('이수근', 2, 5500, '2025-03-22', 'lee@test.com'),
('하하', 3, 3500, '2025-04-10', 'haha@test.com'),
('정형돈', 1, 6200, '2023-07-11', 'don@test.com'),
('박명수', 2, 4800, '2023-09-02', 'park@test.com'),
('노홍철', 3, 3700, '2024-05-14', 'noh@test.com'),
('김종국', 1, 8000, '2022-11-01', 'kim@test.com'),
('양세형', 2, 4300, '2024-06-21', 'yang@test.com'),
('이광수', 3, 3900, '2023-12-12', 'kwang@test.com'),
('조세호', 1, 5100, '2023-03-18', 'cho@test.com'),
('김용만', 2, 4600, '2022-09-23', 'yong@test.com'),
('정준하', 3, 3600, '2024-04-04', 'jun@test.com'),
('김태호', 1, 9000, '2021-10-15', 'taeho@test.com'),
('서장훈', 2, 5800, '2024-08-25', 'seo@test.com'),
('전현무', 3, 4000, '2022-12-01', 'jeon@test.com'),
('김구라', 1, 7500, '2023-11-05', 'gura@test.com'),
('유병재', 2, 4200, '2025-01-20', 'yoo@test.com'),
('김민아', 3, 3800, '2024-10-08', 'mina@test.com');

# Index란? 데이터를 빠르게 검색하기위한 색인
# PK는 기본적인 인덱스를 갖는다. <테이블 1개당 PK 1개 권장>
# 관계형 데이터베이스 구조 상, 특정한 데이터를 찾을 때 검색기준(인덱스)를 미리 지정하면 빠르다.

EXPLAIN ANALYZE SELECT * FROM employee WHERE id = 1;
# 1. 인덱스 목록 조회
# SHOW INDEX FROM 테이블명;
SHOW INDEX FROM employee;

# 2. 단일 컬럼 인덱스 생성
# CREATE INDEX 인덱스명 ON 테이블명(속성명);
CREATE INDEX idx_name ON employee(name);

# 3. 쿼리 성능 조회
# EXPLAIN ANALYZE 쿼리문;
EXPLAIN ANALYZE SELECT * FROM employee WHERE name = '유재석';

# 4. 인덱스 삭제
# DROP INDEX 인덱스명 ON 테이블명;
DROP INDEX idx_name ON employee;

# 5. 복합 인덱스 생성
# CREATE INDEX 인덱스명 ON 테이블명(속성명1, 속성명2);
CREATE INDEX idx_salary ON employee( dept_id, salary );
# 첫번째 인덱스에 대해서는 단일 사용이 가능하다.
EXPLAIN ANALYZE SELECT * FROM employee WHERE dept_id = 1;
# 두번째 인덱스부터는 단일 사용이 불가능하다.
EXPLAIN ANALYZE SELECT * FROM employee WHERE salary = 3800;
# 복합 사용 가능
EXPLAIN ANALYZE SELECT * FROM employee WHERE dept_id = 1 AND salary = 3800;

# 6. JOIN에서의 인덱스
# FK에 인덱스 추가
CREATE INDEX idx_dept ON employee(dept_id);
DROP INDEX idx_dept ON employee;
# 인덱스 추가 후 JOIN하면 실행시간이 단축된다.
EXPLAIN ANALYZE SELECT * FROM employee JOIN department USING (dept_id);

# 7. 문자열 검색 : 자연어(사람이 사용하는 언어) VS 기계어(컴퓨터가 사용하는 언어)
# TYPE이 LONGTEXT, TEXT, CHAR, VARCHAR만 가능하다.
# CREATE FULLTEXT INDEX 인덱스명 ON 테이블명(속성명);
CREATE FULLTEXT INDEX idx_name_full ON employee(name);
EXPLAIN ANALYZE SELECT * FROM employee WHERE name = '유재석';
EXPLAIN ANALYZE SELECT * FROM employee WHERE MATCH(name) AGAINST('유재석');
# 번역되는 과정이 없어지기에 MATCH로 검색하면, 실행속도가 많이 단축된다.
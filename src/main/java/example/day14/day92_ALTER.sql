USE springweb2;

/*
[ DDL ]
	CREATE	테이블/데이터베이스 생성
    DROP	테이블/데이터베이스 삭제
    ALTER	테이블 정보 수정 -> Java에서 동적으로 테이블을 관리할 수 있다.(MyBatis에서 UPDATE로 사용 가능)
*/

# [1] 테이블 생성 및 삭제
DROP TABLE IF EXISTS employee;
CREATE TABLE employee(
	id int,
    name varchar(50),
    dept varchar(30)
);

# [2] 기존 테이블에 속성 추가
# ALTER TABLE 테이블명 ADD COLUMN 새로운속성명 타입|제약조건;
ALTER TABLE employee ADD COLUMN age int default 19;
ALTER TABLE employee ADD COLUMN date date;

# [3] 기존 테이블의 속성 수정
# ALTER TABLE 테이블명 MODIFY COLUMN 수정할속성명 타입|제약조건;
ALTER TABLE employee MODIFY COLUMN dept longtext;

# [4] 기존 테이블의 속성명 수정
# ALTER TALBE 테이블명 CHANGE COLUMN 수정할속성명 새로운속성명 타입;
ALTER TABLE employee CHANGE COLUMN name nickname varchar(30);

# [5] 기존 테이블의 속성 삭제
# ALTER TABLE 테이블명 DROP COLUMN 삭제할속성명;
ALTER TABLE employee DROP COLUMN date;

# [6] 테이블의 속성 정보 확인
# SHOW COLUMNS FROM 테이블명;
SHOW COLUMNS FROM employee;	# MyBatis에서 SELECT로 확인 가능(반환타입 Map)

# [7] 기존 테이블에 제약조건 추가
# ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 제약조건;
ALTER TABLE employee ADD CONSTRAINT PRIMARY KEY (id);
ALTER TABLE employee ADD CONSTRAINT employee_name UNIQUE (name);

# [8] 기존 테이블의 제약조건 삭제
# ALTER TABLE 테이블명 DROP PRIMARY KEY;
# ALTER TABLE 테이블명 DROP FOREIGN KEY 삭제할FK제약조건명;
# ALTER TABLE 테이블명 DROP CONSTRAINT 삭제할제약조건명;
ALTER TABLE employee DROP PRIMARY KEY;
ALTER TABLE employee DROP CONSTRAINT employee_name;

# [9] 제약조건 확인
SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS;
SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_SCHEMA='springweb2';
SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_SCHEMA='springweb2' AND TABLE_NAME='employee';

SELECT * FROM employee;		# 테이블의 속성값 확인
# DCL : 계정 관리, 계정의 사용권한 관리

# [1]
# 1. 계정 만들기 : 최고관리자(root) 계정으로 가능
# 강의 기준으로 root 계정 사용중
# CREATE USER '계정명'@'허용도메인' IDENTIFIED BY '비밀번호';
CREATE USER 'dev1'@'localhost' IDENTIFIED BY '1234';	-- 로컬 전용
CREATE USER 'dev2'@'%' IDENTIFIED BY 'abcd';			-- 외부 모든 접속 허용

# 2. 다른 계정 접속 : 워크벤치 기준
# Database > Connect to Database > Username : 계정명
# 데이터베이스 확인 : SHOW DATABASES;

# 3. 계정 권한 부여 : GRANT
# GRANT 권한 ON 데이터베이스명.테이블명 TO '계정명'@'도메인';
# 모든 권한 부여 : GRANT ALL PRIVILEGES ON 데이터베이스명.테이블명 TO '계정명'@'도메인';

GRANT ALL PRIVILEGES ON springweb2.* TO 'dev1'@'localhost';
GRANT SELECT ON springweb2.student TO 'dev2'@'%';

# 계정 권한 확인 : SHOW GRANTS FOR '계정명'@'도메인';
SHOW GRANTS FOR 'dev1'@'localhost';
SHOW GRANTS FOR 'dev2'@'%';

# 4. 계정 권한 취소 : REVOKE
# REVOKE 권한 ON 데이터베이스명.테이블명 FROM '계정명'@'도메인';
REVOKE SELECT ON springweb2.student FROM 'dev2'@'%';

# 5. 계정 비밀번호 수정
# ALTER USER '계정명'@'도메인' IDENTIFIED BY 새로운비밀번호;
ALTER USER 'dev2'@'%' IDENTIFIED BY '1234';

# 6. 계정 삭제
# DROP USER '계정명'@'도메인';
DROP USER 'dev2'@'%';

# 7. 모든 계정 확인
SELECT * FROM mysql.user;

# [2]
# 1. 새로운 계정 생성
CREATE USER 'dev3'@'localhost' IDENTIFIED BY '1234';
# 2. 새로운 뷰 생성
USE springweb2;
CREATE OR REPLACE VIEW student_view AS SELECT * FROM student;
# 3. 새로운 계정에게 뷰 조회 권한 부여
GRANT SELECT ON springweb2.student_view TO 'dev3'@'localhost';
# 4. dev3 접속 후 테스트
SELECT * FROM student_view;
UPDATE student_view SET name = '유재석';
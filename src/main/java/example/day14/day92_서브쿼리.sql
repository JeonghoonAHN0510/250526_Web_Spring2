USE springweb2;
# SELECT 중첩 : 서브쿼리
# SELECT 서브쿼리 FROM 서브쿼리 WHERE 서브쿼리;

# 1. 일반 SELECT
SELECT AVG((kor + math) / 2) 평균점수 FROM student;
# 2. 중첩 SELECT
SELECT name FROM student
	WHERE (kor + math) / 2 > (SELECT AVG((kor + math) / 2) FROM student);
    
# [1] 국어 점수가 평균 이상인 학생 조회 - WHERE 서브쿼리
# 1) 먼저 내부쿼리(서브쿼리) 기준으로 작성한다.
# 2) 메인 쿼리를 작성한다
SELECT * FROM student;
# 국어점수 평균
SELECT AVG(kor) FROM student;
# 국어점수 평균 이상인 국어점수
SELECT kor FROM student WHERE kor >= (SELECT AVG(kor) FROM student);
# 해당 점수를 가진 학생이름 조회
# in(값1, 값2, 값3) : 하나라도 포함하면, true
SELECT name FROM student WHERE kor in (SELECT kor FROM student WHERE kor >= (SELECT AVG(kor) FROM student));

# [2] 서브쿼리를 이용한 각 학생들과 총점 비교 - SELECT 서브쿼리
SELECT s1.name, (SELECT COUNT(*) FROM student s2 WHERE (s2.kor + s2.math) >= (s1.kor + s1.math)) 등수
	FROM student s1;
    
# [3] 서브쿼리를 이용한 평균점수 정렬- FROM 서브쿼리
SELECT * FROM (SELECT name, (kor + math) / 2 as 평균점수 FROM student) as 평균테이블 ORDER BY 평균점수 DESC;
USE springweb2;				-- 사용할 데이터베이스 선택
SET SQL_SAFE_UPDATES = 0;	-- MySQL 워크벤치에서 UPDATE 사용 설정

# 트랜잭션 : 여러 작업들을 하나의 묶음으로 간주하여 모두 성공하면 COMMIT, 하나라도 실패하면 ROLLBACK
# Java Spring : @Transactional을 사용하되, RuntimeException을 통해 롤백, savepoint는 지원 X
# Java JDBC(Dao) : savepoint 지원 O
SET AUTOCOMMIT = 0;			-- MySQL 워크벤치에서 자동 COMMIT 비활성화(학습용)

# [1] 트랜잭션 예제 1
# 1. 트랜잭션 시작
START TRANSACTION;
# 2. 여러 작업 - DML || DDL은 불가능하다.
UPDATE trans SET money = money - 30000 WHERE name = '신동엽';		-- 출금
UPDATE trans SET money = money + 30000 WHERE name = '서장훈';		-- 입금
# 3-1. 완료
COMMIT;
# 3-2. 되돌리기(취소)
ROLLBACK;
# 4. 확인
# 1 > 2 > 4 > 3-1(COMMIT) > 4
# 1 > 2 > 4 > 3-2(ROLLBACK) > 4
SELECT * FROM trans;

# [2] 트랜잭션 예제 2
# 1. 트랜잭션 시작
START TRANSACTION;
# 2-1. 여러 작업 - DML || DDL은 불가능하다.
UPDATE trans SET money = money - 30000 WHERE name = '신동엽';		-- 출금
# 2-2. 저장지점 만들기
SAVEPOINT pointA;
# 2-3. 여러 작업
UPDATE trans SET money = money - 30000 WHERE name = '서장훈';		-- 출금
# 3-1. 완료
COMMIT;
# 3-2. 저장지점으로 되돌리기(취소)
ROLLBACK TO pointA;
# 4. 확인
# 1 > 2-1 > 2-2 > 2-3 > 4 > 3-1(COMMIT) > 4 : 둘 다 출금
# 1 > 2-1 > 2-2 > 2-3 > 4 > 3-2(ROLLBACK) > 4 > 3-1(COMMIT) > 4 : 신동엽만 출금
SELECT * FROM trans;

# [3] 트랜잭션 예제 3
# 1. 트랜잭션 시작
START TRANSACTION;
# 2-1. 여러 작업 - DML || DDL은 불가능하다.
UPDATE trans SET money = money - 10000 WHERE name = '신동엽';		-- 출금
# 2-2. 저장지점 만들기
SAVEPOINT pointA;
# 2-3. 여러 작업
UPDATE trans SET money = money - 10000 WHERE name = '서장훈';		-- 출금
# 2-4. 저장지점 만들기
SAVEPOINT pointB;
# 2-5. 여러 작업
UPDATE trans SET money = money - 10000 WHERE name = '신동엽';		-- 출금
# 2-6. 저장지점 만들기
SAVEPOINT pointC;
# 3-1. 완료
COMMIT;
# 3-2. 저장지점으로 되돌리기(취소)
ROLLBACK TO pointA;		# 2-1 반영
ROLLBACK TO pointB;		# 2-1, 2-3 반영
ROLLBACK TO pointC;		# 2-1, 2-3, 2-5 반영

SELECT * FROM trans;
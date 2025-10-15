USE springweb2;
SET SQL_SAFE_UPDATES = 0;	-- MySQL 워크벤치에서 UPDATE 사용 설정
-- 1.기존 테이블 초기화
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS member;

-- 2.회원(member) 테이블 생성
CREATE TABLE member (
    mno INT AUTO_INCREMENT PRIMARY KEY,   -- 회원번호 (PK)
    name VARCHAR(50),                     -- 이름
    grade VARCHAR(20)                     -- 등급 (VIP, GOLD, SILVER)
);

-- 3. 주문(orders) 테이블 생성
CREATE TABLE orders (
    ono INT AUTO_INCREMENT PRIMARY KEY,   -- 주문번호 (PK)
    mno INT,                              -- 회원번호 (FK)
    product VARCHAR(50),                  -- 상품명
    price INT,                            -- 가격
    order_date DATE,                      -- 주문일자
    FOREIGN KEY (mno) REFERENCES member(mno)
);

-- 4. 샘플 데이터 삽입
INSERT INTO member (name, grade)
VALUES ('유재석', 'VIP'), ('강호동', 'GOLD'), ('신동엽', 'SILVER');

INSERT INTO orders (mno, product, price, order_date)
VALUES
(1, '노트북', 1500000, '2025-10-10'),
(1, '마우스', 30000, '2025-10-11'),
(2, '키보드', 50000, '2025-10-11'),
(3, '모니터', 200000, '2025-10-12');
#=======================================================================
SELECT * FROM member;
SELECT * FROM orders;
# 뷰(View) : 하나 이상의 원본 테이블을 기반으로 만들어진 가상 테이블
# 사용목적 : 권한 및 보안을 위하여, 복잡한 쿼리문 결과 저장(재사용을 위하여)
# 데이터가 많다면, 조회 속도의 차이가 있을 것
# 주의할점 : 특별한 경우가 아니라면, SELECT쿼리만 가능 -> INSERT, UPDATE, DELETE는 원본 테이블에서만 사용
# 수정이 불가능한 속성 : 집계/통계/그룹/계싼 등의 속성
# 수정이 가능한 뷰 : 단일(원본) 테이블 기반
# [1] View
# 1. 뷰 생성
# CREATE OR REPLACE VIEW 뷰이름 AS 쿼리;
CREATE OR REPLACE VIEW orders_test as SELECT * FROM orders;
# 2. 뷰 목록 확인
SHOW FULL TABLES WHERE TABLE_TYPE = "VIEW";
# 3. 뷰 수정
# ALTER VIEW 뷰이름 as 새로운쿼리;
ALTER VIEW orders_test AS SELECT product, price FROM orders;
# 4. 뷰 조회
SELECT * FROM orders_test;
# 5. 뷰 삭제
DROP VIEW IF EXISTS orders_test;

# [2] WHERE View
# 1. VIP 뷰 생성
CREATE OR REPLACE VIEW vip_member AS SELECT * FROM member WHERE grade = 'VIP';
# 2. VIP 조회 : 결과는 같지만, 조회 속도에서 차이가 날 것이다.
SELECT * FROM vip_member;
SELECT * FROM member WHERE grade = 'VIP';
# 3. 뷰 수정
UPDATE vip_member SET name = '유재석2';

# [3] JOIN View
# 1. JOIN 뷰 생성
CREATE OR REPLACE VIEW join_mno AS SELECT * FROM member JOIN orders USING (mno);
# 2. JOIN 뷰 조회
SELECT * FROM join_mno;
SELECT * FROM member JOIN orders USING (mno);
# 3. JOIN 뷰 수정
UPDATE join_mno SET name = '유재석3';

# [4] 집계 View
# 1. 집계 뷰 생성
CREATE OR REPLACE VIEW vip_member2 AS SELECT *, 10+10 집계 FROM member WHERE grade = 'VIP';
# 2. 집계 뷰 조회
SELECT * FROM vip_member2;
# 3. 집계 뷰 수정 : 집계 속성은 수정 불가능
UPDATE vip_member2 SET 집계 = 30;
UPDATE vip_member2 SET name = '유재석4';
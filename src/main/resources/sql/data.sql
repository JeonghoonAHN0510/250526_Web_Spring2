-- --------------------------------------- Insert ----------------------------------------
INSERT INTO products (product_name, stock_quantity) VALUES
    ('무선 이어폰', 25),
    ('스마트워치', 12),
    ('게이밍 키보드', 30),
    ('기계식 마우스', 8),
    ('휴대용 충전기', 15);

-- --------------------------------------- Insert ----------------------------------------
INSERT INTO student (name, kor, math) VALUES ('홍길동', 85, 90);
INSERT INTO student (name, kor, math) VALUES ('김철수', 78, 88);
INSERT INTO student (name, kor, math) VALUES ('이영희', 92, 95);
INSERT INTO student (name, kor, math) VALUES ('박지민', 70, 65);
INSERT INTO student (name, kor, math) VALUES ('최유리', 88, 82);

-- --------------------------------------- Insert ----------------------------------------
INSERT INTO board (bcontent, bwriter) VALUES ('안녕하세요', '유재석');
INSERT INTO board (bcontent, bwriter) VALUES ('오늘도 좋은 하루 되세요!', '김태호');
INSERT INTO board (bcontent, bwriter) VALUES ('점심 뭐 드셨나요?', '박명수');
INSERT INTO board (bcontent, bwriter) VALUES ('날씨가 참 좋네요', '정준하');
INSERT INTO board (bcontent, bwriter) VALUES ('이번 주말 계획은?', '하하');
INSERT INTO board (bcontent, bwriter) VALUES ('파이팅입니다!', '이광수');
INSERT INTO board (bcontent, bwriter) VALUES ('다들 행복하세요', '송지효');
INSERT INTO board (bcontent, bwriter) VALUES ('무한도전 그립다', '노홍철');
INSERT INTO board (bcontent, bwriter) VALUES ('커피 한 잔의 여유', '길');
INSERT INTO board (bcontent, bwriter) VALUES ('오늘은 집에서 푹 쉬어요', '정형돈');
INSERT INTO board (bcontent, bwriter) VALUES ('운동은 하셨나요?', '김종국');
INSERT INTO board (bcontent, bwriter) VALUES ('영화 추천해 주세요', '양세찬');
INSERT INTO board (bcontent, bwriter) VALUES ('책 읽기 좋은 날이네요', '전소민');
INSERT INTO board (bcontent, bwriter) VALUES ('비가 와서 기분이 우울해요', '유희열');
INSERT INTO board (bcontent, bwriter) VALUES ('점심 메뉴 고민중...', '이효리');
INSERT INTO board (bcontent, bwriter) VALUES ('졸려요...', '강호동');
INSERT INTO board (bcontent, bwriter) VALUES ('주말까지 며칠 남았죠?', '이수근');
INSERT INTO board (bcontent, bwriter) VALUES ('야근은 너무 싫어요', '서장훈');
INSERT INTO board (bcontent, bwriter) VALUES ('오늘 기온이 몇 도일까요?', '장도연');
INSERT INTO board (bcontent, bwriter) VALUES ('여름엔 역시 냉면', '홍진경');
INSERT INTO board (bcontent, bwriter) VALUES ('지금 듣는 노래는?', '장성규');
INSERT INTO board (bcontent, bwriter) VALUES ('퇴근하고 싶다', '이상민');
INSERT INTO board (bcontent, bwriter) VALUES ('다이어트는 내일부터', '박나래');
INSERT INTO board (bcontent, bwriter) VALUES ('버스 기다리는 중', '김숙');
INSERT INTO board (bcontent, bwriter) VALUES ('늦잠 잤어요', '이영자');
INSERT INTO board (bcontent, bwriter) VALUES ('스트레스 풀고 싶다', '유세윤');
INSERT INTO board (bcontent, bwriter) VALUES ('아이스 아메리카노 최고', '김신영');
INSERT INTO board (bcontent, bwriter) VALUES ('게임 하다 왔어요', '이홍기');
INSERT INTO board (bcontent, bwriter) VALUES ('일상 공유해요~', '김재중');
INSERT INTO board (bcontent, bwriter) VALUES ('오늘 하루 어땠나요?', '장나라');
INSERT INTO board (bcontent, bwriter) VALUES ('마라탕 먹고 싶다', '현아');
INSERT INTO board (bcontent, bwriter) VALUES ('퇴근길 너무 막혀요', '정은지');
INSERT INTO board (bcontent, bwriter) VALUES ('곧 여행 가요!', '수지');
INSERT INTO board (bcontent, bwriter) VALUES ('카페에서 힐링 중', '아이유');
INSERT INTO board (bcontent, bwriter) VALUES ('노래 추천해 주세요~', '로제');
INSERT INTO board (bcontent, bwriter) VALUES ('배가 너무 고파요', '지수');
INSERT INTO board (bcontent, bwriter) VALUES ('헬스장 다녀왔어요', '제니');
INSERT INTO board (bcontent, bwriter) VALUES ('오늘도 화이팅!', '리사');
INSERT INTO board (bcontent, bwriter) VALUES ('운전 조심하세요~', '태연');

-- --------------------------------------- Insert ----------------------------------------
insert into phonebook ( name, phone, age ) values
    ( '신동엽', '010-1234-5678', 50 ),
    ( '강호동', '010-9876-5432', 40 ),
    ( '유재석', '010-4567-1234', 30 );

-- --------------------------------------- Insert ----------------------------------------
insert into movie ( mtitle, mdirector, mgenre, mcomment, mpwd ) values
    ( '영화1', '감독1', '장르1', '감독1의 영화1', '1234' ),
    ( '영화2', '감독2', '장르2', '감독2의 영화2', '1234' ),
    ( '영화3', '감독3', '장르3', '감독3의 영화3', '1234' );

-- --------------------------------------- Insert ----------------------------------------
insert into discussion ( mno, dcontent, dpwd ) values
    ( 1, '토론내용1', '1234' ),
    ( 1, '토론내용2', '1234' ),
    ( 2, '토론내용2', '1234' ),
    ( 3, '토론내용3', '1234' );

-- --------------------------------------- Insert ----------------------------------------
INSERT INTO trans (name, money) VALUES
    ('신동엽', 200000),
    ('서장훈', 200000);

-- --------------------------------------- Insert ----------------------------------------
INSERT INTO books (id, title, stock) VALUES (1, '자바의 정석', 3);
INSERT INTO books (id, title, stock) VALUES (2, '스프링 인 액션', 2);
INSERT INTO books (id, title, stock) VALUES (3, '토비의 스프링', 1);
INSERT INTO books (id, title, stock) VALUES (4, '리액트 교과서', 5);

-- --------------------------------------- Insert ----------------------------------------
INSERT INTO rentals (id, book_id, member) VALUES (1, 1, '홍길동');
INSERT INTO rentals (id, book_id, member) VALUES (2, 2, '강호동');
-- --------------------------------------- 실습1 ----------------------------------------
DROP TABLE IF EXISTS products;
CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT, -- 상품 ID (자동 증가)
    product_name VARCHAR(255) NOT NULL,        -- 상품명
    stock_quantity INT NOT NULL                -- 재고 수량
);

-- --------------------------------------- day06 example ----------------------------------------
-- 학생 테이블
DROP TABLE IF EXISTS student;
CREATE TABLE student (
    sno INT AUTO_INCREMENT,         -- 학생 번호 (자동 증가)
    name VARCHAR(50) NOT NULL,      -- 이름
    kor INT NOT NULL,               -- 국어 점수
    math INT NOT NULL,              -- 수학 점수
    CONSTRAINT PRIMARY KEY (sno)  	-- 기본키 제약 조건 추가
);

-- -------------------------------- day07 boardService13 ---------------------------------
DROP TABLE IF EXISTS board;
create table board(
    bno int auto_increment ,
    bcontent longtext not null ,
    bwriter varchar(30) not null ,
    constraint primary key(bno)
);

-- --------------------------------------- Task05 ----------------------------------------
DROP TABLE IF EXISTS phoneBook;
create table phoneBook(
    mno int auto_increment,
    name varchar(20) not null,
    phone varchar(13) not null,
    age int not null,
    constraint primary key( mno )
);

-- --------------------------------------- Movie ----------------------------------------
DROP TABLE IF EXISTS discussion;
DROP TABLE IF EXISTS movie;
create table movie(
    mno int auto_increment,				-- 번호
    mtitle varchar(30) not null,		-- 제목
    mdirector varchar(20) not null,		-- 감독
    mgenre varchar(10) not null,		-- 장르
    mcomment varchar(100) not null,		-- 간단한 소개
    mpwd varchar(20) not null,			-- 비밀번호
    constraint primary key( mno )
);

-- ------------------------------------- Discussion --------------------------------------
create table discussion(
    dno int auto_increment,				-- 번호
    mno int not null,					-- 영화번호
    dcontent varchar(100) not null,		-- 토론(댓글) 내용
    dpwd varchar(20) not null,			-- 토론(댓글) 비밀번호
    constraint primary key( dno ),
    constraint foreign key( mno ) references movie ( mno ) on delete cascade on update cascade
);

-- --------------------------------------- Trans ----------------------------------------
DROP TABLE IF EXISTS trans;
CREATE TABLE trans(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    money INT UNSIGNED DEFAULT 0
);

-- --------------------------------------- books ----------------------------------------
DROP TABLE IF EXISTS rentals;
DROP TABLE IF EXISTS books;
CREATE TABLE books (
    id INT NOT NULL auto_increment,
    title VARCHAR(255) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

-- --------------------------------------- rentals ----------------------------------------
CREATE TABLE rentals (
    id INT NOT NULL auto_increment,
    book_id INT NOT NULL,
    member VARCHAR(100) NOT NULL,
    rent_date DATETIME DEFAULT NOW(),
    return_date DATETIME NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

-- --------------------------------------- web2 ----------------------------------------
-- [2] 회원 테이블 생성
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    uno INT AUTO_INCREMENT PRIMARY KEY,     -- 회원번호 (PK)
    uid VARCHAR(50) NOT NULL UNIQUE,        -- 아이디
    upwd VARCHAR(255),                      -- 비밀번호 (암호화)
    uname VARCHAR(50) NOT NULL,             -- 닉네임 (이름)
    uphone VARCHAR(20),                     -- 연락처
    urole VARCHAR(20) DEFAULT 'USER',       -- 권한 (기본 USER)
    udate DATETIME DEFAULT NOW()            -- 가입일
);
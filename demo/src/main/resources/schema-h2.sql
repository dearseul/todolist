DROP TABLE IF EXISTS friendconnection CASCADE;
DROP TABLE IF EXISTS post CASCADE;
DROP TABLE IF EXISTS member CASCADE;

-- 사용자 데이터이다.
CREATE TABLE member
(
    seq           bigint      NOT NULL AUTO_INCREMENT,              -- 사용자 PK
    member_id       varchar(50) NOT NULL,                             -- 사용자 PK
    password      varchar(80) NOT NULL,                             -- 비밀번호
    name          varchar(80) NOT NULL,                             -- 이름
    login_count   int         NOT NULL DEFAULT 0,                   -- 로그인횟수
    last_login_at datetime,                -- 마지막 로그인일시
    create_at     datetime    NOT NULL, -- 생성일시
    img_url       varchar(50)                 DEFAULT NULL,                -- 생성일시
    PRIMARY KEY (seq),
    CONSTRAINT unq_user_id UNIQUE (member_id)
);
-- 글(post) 데이터이다.
CREATE TABLE post
(
    seq       bigint       NOT NULL AUTO_INCREMENT,              -- list PK
    user_seq  bigint       NOT NULL,                             -- list 작성자 PK
    title     varchar(100) NOT NULL,                             -- 제목
    is_done   char         NOT NULL DEFAULT 0,                   -- 완료 여부
    scope     varchar(20)  NOT NULL DEFAULT 0,                   -- 공개 범위
    create_at datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 생성일시
    update_at datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 수정일시
    PRIMARY KEY (seq),
    CONSTRAINT fk_list_to_user FOREIGN KEY (user_seq) REFERENCES member (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- 사용자들의 친구 관계 데이터이다.
CREATE TABLE friendconnection
(
    seq        bigint   NOT NULL AUTO_INCREMENT,              -- 친구관계 PK
    writer_id  bigint   NOT NULL,                             -- 작성자 아이디
    friend_seq bigint   NOT NULL,                             -- 친구 사용자 PK
    create_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 생성일시
    PRIMARY KEY (seq),
    CONSTRAINT fk_connection_to_user FOREIGN KEY (writer_id) REFERENCES member (seq) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_connection_to_user2 FOREIGN KEY (friend_seq) REFERENCES member (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);
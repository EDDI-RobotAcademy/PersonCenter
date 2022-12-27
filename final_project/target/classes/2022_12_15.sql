--*****************여기부터 테이블 생성*************************--
-- 회원정보(board_customer)
create table board_customer( 
 cus_id varchar2(50) primary key,
 cus_pw varchar2(50) not null,
 cus_gender varchar2(6) not null,
 cus_nickname varchar2(50) not null,
 cus_question int not null,
 cus_answer varchar2(50) not null,
 cus_bank varchar2(10) not null,
 cus_account varchar2(20) not null,
 cus_zip_code varchar2(100) not null,
 cus_addr varchar2(1000) not null,
 cus_tel varchar2(20) not null
 );

--회원 이미지파일(board_cus_file)
 create table board_cus_file(
 board_num int,
 board_fileid varchar(200) not null,
 board_uploadfileid varchar(200) not null,
 cus_id varchar(50),
 foreign key(cus_id) references board_customer(cus_id) 
 on delete cascade
 )

--친구테이블(board_friend)
 create table board_friend(
 cus_id varchar(50) primary key,
 friend_id varchar(50),
 cus_status varchar(30),
 foreign key(cus_id) references board_customer(cus_id)
 on delete cascade
 );

--메세지 테이블(board_message)
create table board_message(
 message_num number(10) primary key,
 cus_id varchar2(50) not null,
 friend_id varchar2(50) not null,
 message_title varchar2(50) not null,
 message varchar2(1000) not null,
 cus_status varchar2(50),
 friend_status varchar2(50),
 message_date timestamp(0) default SYSTIMESTAMP,
 foreign key(cus_id) references board_customer(cus_id)
 on delete cascade
 );

--거래테이블(board)
 create table board(
 board_num number(10),
 board_title varchar2(100) not null,
 board_content varchar2(1000) not null,
 board_date timestamp(0) default SYSTIMESTAMP,
 board_id varchar2(50) not null,
 board_nickname varchar2(50) not null,
 board_hits number(10) default 0,
 board_replies number(10) default 0,
 board_see varchar(50) not null,
 board_cost varchar2(50) not null,
 board_category varchar2(20) not null,
 foreign key(board_id) references board_customer(cus_id)
 on delete cascade,
 primary key(board_num)
 );

-- 거래게시글 사진(board_file)
 create table board_file(
 board_num int,
 board_fileid varchar(200) not null,
 board_uploadfileid varchar(200) not null,
 foreign key(board_num) references board(board_num) on delete cascade
 );

--댓글 (reply)
 create table reply(
 board_num number(10),
 reply_num number(10) primary key,
 rreply_num number(10),
 reply_id varchar2(50) not null,
 reply_content varchar2(200),
 reply_date timestamp(0) default SYSTIMESTAMP,
 reply_nickname varchar2(50) not null,
 rreply_id varchar2(50),
 rreply_nickname varchar2(50),
 foreign key(board_num) references board(board_num),
 foreign key(reply_id) references board_customer(cus_id)
 on delete cascade
 );

--자유게시판(board_free)
 create table board_free(
 board_num number(10),
 board_title varchar2(100) not null,
 board_content varchar2(1000) not null,
 board_date timestamp(0) default SYSTIMESTAMP,
 board_id varchar2(50) not null,
 board_nickname varchar2(50) not null,
 board_hits number(10) default 0,
 board_replies number(10) default 0,
 board_see varchar(50) not null,
 foreign key(board_id) references board_customer(cus_id)
 on delete cascade,
 primary key(board_num)
 );

--자유게시판 사진 업로드(board_file_free)
 create table board_file_free(
 board_num int,
 board_fileid varchar(200) not null,
 board_uploadfileid varchar(200) not null,
 foreign key(board_num) references board_free(board_num) on delete cascade
 );

--공지사항(board_notice)
create table board_notice(
 board_num number(10),
 board_no_title varchar2(100) not null,
 board_no_content varchar2(1000) not null,
 board_no_date date default SYSDATE,
 board_no_id varchar2(50) not null,
 board_no_nickname varchar2(50) not null,
 board_no_hits number(10) default 0,
 board_no_replies number(10) default 0,
 board_no_see varchar(50) not null,
 board_no_seq number default 0,
 foreign key(board_no_id) references board_customer(cus_id)
 on delete cascade,
 primary key(board_num)
 );
 
--*****************여기부터 시퀀스*************************--

-- 메세지 시퀀스 
create sequence board_message_seq start with 1 increment by 1;

-- 댓글 시퀀스
create sequence reply_seq start with 1 increment by 1;

--거래 게시판 시퀀스
create sequence board_seq start with 1 increment by 1;

--거래 게시판 파일 시퀀스
create sequence board_file_seq start with 1 increment by 1;

--자유 게시판 시퀀스
create sequence board_free_seq start with 1 increment by 1;

--자유 게시판 파일 시퀀스
create sequence board_file_free_seq start with 1 increment by 1;

--공지글 시퀀스
create sequence board_notice_seq start with 1 increment by 1;

--*****************여기부터 트리거************************--

-- 메시지 트리거
create or replace trigger board_message_seq_tr
  before insert on board_message for each row
  when (new.message_num is null)
 begin
 select board_message_seq.nextval into :new.message_num from dual;
end;

-- 댓글 트리거
create or replace trigger reply_seq_tr
  before insert on reply for each row
  when (new.reply_num is null)
 begin
 select reply_seq.nextval into :new.reply_num from dual;
end;

--*****************테이블 삭제************************--

drop table board_customer purge;
drop table board_cus_file purge;
drop table board_friend purge;
drop table board_message purge;
drop table board purge;
drop table board_file purge;
drop table board_free purge;
drop table board_file_free purge;
drop table board_reply purge;
drop table board_notice purge;

--*****************테이블 조회************************--

select * from board_customer;
select * from board_cus_file;
select * from board_friend;
select * from board_message;
select * from board;

select * from board_file;
select * from board_free;
select * from board_file_free;
select * from board_reply;
select * from board_notice;


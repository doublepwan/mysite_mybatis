
select g.gallery_no, u.name, g.title, g.content, g.reg_date, g.gallery_hit, g.gallery_downcount, f.gallery_file_realname
from gallery g, gallery_file f, users u
where g.gallery_file_no = f.gallery_file_no and g.user_no = u.user_no
order by g.reg_date desc



create table gallery
(
    gallery_no number not null,
    gallery_title varchar2(2000) not null,
    gallery_content varchar2(2000) not null,
    reg_date date default sysdate,
    gallery_hit number default 0, not null,
    gallery_downcount number default 0, not null,
    user_no number not null,
    gallery_file_no number not null,
    primary key (gallery_no)
);

create table gallery_file
(
    gallery_file_no number not null,
    gallery_file_name varchar2(2000) not null,
    gallery_file_realname varchar2(2000) not null,
    gallery_file_path varchar2(2000) not null,
    gallery_file_size varchar2(2000) not null,
    primary key (gallery_file_no)
);

create sequence seq_gallery_no start with 1 increment by 1;
create sequence seq_gallery_file_no start with 1 increment by 1;

alter table gallery
    add foreign key (gallery_file_no)
    references gallery_file (gallery_file_no);
    
    alter table gallery
    add foreign key (user_no)
    references users (no);
CREATE TABLE sys_res
(
    id bigint(11),
    name varchar(255) ,
    spell varchar(255),
    url varchar(255) ,
    `describe` varchar(255),
    method varchar(255) default 'GET',
    sort smallint default '1',
    create_user varchar(255),
    create_time datetime,
    modify_user varchar(255),
    modify_time datetime,
    remark varchar(255) ,
    is_enable boolean default true,
    scopes varchar(55) default 'ALL'
);

create table sys_user
(
    id bigint primary key,
    name varchar(55),
    spell varchar(255),
    password varchar(255),
    status varchar(25) default 'NORMAL',
    image varchar(255) default '图标：images/guest.jpg',
    email varchar(255) default NULL,
    phone varchar(20) default NULL,
    online_status boolean default true,
    sort smallint,
    create_user varchar(255) default NULL,
    create_time datetime default CURRENT_TIMESTAMP,
    modify_user varchar(255) default NULL,
    modify_time datetime default CURRENT_TIMESTAMP,
    remark varchar(255) default NULL,
    is_enable boolean default true not null,
    avatar varchar(255) default '图标：images/guest.jpg'
);

create table client_details
(
    id bigint  primary key,
    name varchar(255) default NULL,
    client_id varchar(256) not null,
    resource_ids varchar(256) not null,
    client_secret varchar(256) not null,
    scope varchar(256) default 'all',
    grant_types varchar(256) not null,
    redirect_url varchar(256) default NULL,
    authorities varchar(256) default NULL,
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information varchar(4096) default NULL,
    auto_approve_scopes varchar(256) default '' not null,
    spell varchar(255) default NULL,
    sort smallint default '1' not null,
    create_user varchar(255) default NULL,
    create_time datetime default CURRENT_TIMESTAMP,
    modify_user varchar(255) default NULL,
    modify_time datetime default CURRENT_TIMESTAMP,
    remark varchar(255) default NULL,
    is_enable boolean default true not null
);


create table sys_data
(
    id bigint primary key,
    name varchar(50) not null,
    spell varchar(255) default NULL,
    parent_id bigint default '0',
    brief varchar(2048) default NULL,
    type smallint default '0',
    sort smallint default '1',
    create_user varchar(255) default NULL,
    create_time datetime default CURRENT_TIMESTAMP,
    modify_user varchar(255) default NULL,
    modify_time datetime default CURRENT_TIMESTAMP,
    remark varchar(255) default NULL,
    is_enable boolean default true not null
);

create table semester
(
    id bigint primary key,
    name varchar(255) default NULL,
    spell varchar(255) default NULL,
    school_id bigint not null,
    start_date date,
    end_date date,
    sort smallint,
    create_user varchar(255) default NULL,
    create_time datetime default CURRENT_TIMESTAMP,
    modify_user varchar(255) default NULL,
    modify_time datetime default CURRENT_TIMESTAMP,
    remark varchar(255) default NULL,
    is_enable boolean default true not null
);

create index fk_school
    on semester (school_id);

create table student
(
    id bigint primary key,
    name varchar(255) default NULL,
    spell varchar(255) default NULL,
    user_id bigint,
    school_id bigint,
    college_id bigint,
    dep_id bigint,
    specialty_id bigint,
    classes_id bigint,
    no varchar(20) default NULL,
    gender varchar(255) default NULL,
    id_number varchar(18) default NULL,
    birthday date,
    enter_date date,
    academic varchar(255) default NULL,
    graduation_date date,
    graduate_institution varchar(255) default NULL,
    original_major varchar(255) default NULL,
    resume varchar(2048) default NULL,
    sort smallint,
    create_user varchar(255) default NULL,
    create_time datetime default CURRENT_TIMESTAMP,
    modify_user varchar(255) default NULL,
    modify_time datetime default CURRENT_TIMESTAMP,
    remark varchar(255) default NULL,
    is_enable boolean default true not null
);

create table sys_role
(
    id bigint primary key,
    name varchar(255) not null,
    spell varchar(255) default NULL,
    des varchar(128) default NULL,
    icon_cls varchar(55) default 'status_online',
    parent_id bigint default '0' not null,
    sort smallint,
    create_user varchar(255) default NULL,
    create_time datetime default CURRENT_TIMESTAMP,
    modify_user varchar(255) default NULL,
    modify_time datetime default CURRENT_TIMESTAMP,
    remark varchar(255) default NULL,
    is_enable boolean default true not null
);

create table sys_role_res
(
    id bigint primary key,
    name varchar(255) default NULL,
    spell varchar(255) default NULL,
    role_id bigint,
    res_id bigint,
    sort smallint,
    create_user varchar(255) default NULL,
    create_time datetime default CURRENT_TIMESTAMP,
    modify_user varchar(255) default NULL,
    modify_time datetime default CURRENT_TIMESTAMP,
    remark varchar(255) default NULL,
    is_enable boolean default true not null
);

create table sys_user_role
(
    id bigint primary key,
    name varchar(254) default NULL,
    spell varchar(254) default NULL,
    user_id bigint not null,
    role_id bigint not null,
    sort smallint,
    create_user varchar(255) default NULL,
    create_time datetime default CURRENT_TIMESTAMP,
    modify_user varchar(255) default NULL,
    modify_time datetime default CURRENT_TIMESTAMP,
    remark varchar(255) default NULL,
    is_enable boolean default true not null
);

create table teacher
(
    id bigint primary key,
    name varchar(255) default NULL,
    spell varchar(255) default NULL,
    user_id bigint,
    school_id bigint,
    college_id bigint,
    dep_id bigint,
    gender varchar(255) default NULL,
    birthday date,
    nation varchar(255) default NULL,
    degree varchar(255) default NULL,
    academic varchar(255) default NULL,
    graduation_date date,
    major varchar(255) default NULL,
    graduate_institution varchar(255) default NULL,
    major_research varchar(255) default NULL,
    resume varchar(2048) default NULL,
    work_date date,
    prof_title varchar(255) default NULL,
    prof_title_ass_date date,
    is_academic_leader boolean,
    subject_category varchar(255) default NULL,
    id_number varchar(18) default NULL,
    sort smallint,
    create_user varchar(255) default NULL,
    create_time datetime default CURRENT_TIMESTAMP,
    modify_user varchar(255) default NULL,
    modify_time datetime default CURRENT_TIMESTAMP,
    remark varchar(255) default NULL,
    is_enable boolean default true not null
);



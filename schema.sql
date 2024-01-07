DROP SCHEMA IF EXISTS `loginApp` ;
CREATE SCHEMA IF NOT EXISTS `loginApp`;
USE `loginApp` ;

drop table if exists users;

create table if not exists users(
	username varchar(128) not null,
    password varchar(128) not null,
    role, varchar(128) not null
    
    primary key(username)
);


--select * from users;

insert ignore into users(email, password, role) values ('user1', 'pw1', "user");
insert ignore into users(email, password, role) values ('manager1', 'pw1', "manager");

--select * from users where BINARY email='A1@email.com';

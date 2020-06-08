create schema test;

create table test.framework
(
    id       serial primary key,
    name     varchar(255) not null unique,
    language varchar(255),
    link     varchar(255)
);

insert into test.framework (name, language, link)
values ('Spring Framework', 'Java', 'https://spring.io'),
       ('Vue.js', 'JavaScript', 'https://vuejs.org'),
       ('Laravel', 'PHP', 'https://laravel.com');

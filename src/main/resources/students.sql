create database if not exists newuniversity default char set utf8;
use newuniversity;
grant all privileges on newuniversity.* to root identified by 'root';
SHOW VARIABLES LIKE 'character_set_database';
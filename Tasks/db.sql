create database organizer;
use  organizer;

create table todo_items (
	id integer primary key auto_increment,
    title text not null,
    item_description text,
    due_date date not null, 
    created_date date not null, 
    priority varchar(8) not null,
    estimate_hours integer,
    item_state varchar(16)
);

ALTER TABLE `organizer`.`todo_items` 
CHANGE COLUMN `created_date` `created_date` DATE NOT NULL DEFAULT (CURRENT_DATE);

ALTER TABLE `organizer`.`todo_items` 
CHANGE COLUMN `item_state` `item_state` VARCHAR(16) NULL DEFAULT 'To Do' ;


/*ROLES*/
insert into `role` (`role_id`, `role`)
value (1, 'ADMIN');

insert into `role` (`role_id`, `role`)
value (2, 'USER');

/*USERS*/
insert into user (`user_id`, `active`, `name`, `password`)
values (1,1, 'admin' , '$2a$10$mMFSgm6mXVqDyjiiQcscHOBpdhatqNIT0F5x9qNbRDdGWm5qvjkJC' );

insert into user (`user_id`, `active`, `name`, `password`)
values (2,1,'user', '$2a$10$i0YJ0OMSkc5WNxDtGHW7QOtIjiPuhJhSPoW2ptGg5szB8FpxQTape' );

/*ADD ROLES TO USERS*/
/*ADMIN ROLE WITH admin and user role*/
insert into user_role (`user_id`, `role_id`) values ( 1,1 );
insert into user_role (`user_id`,`role_id`) values ( 1,2 );

/*USER ROLE WITH only user role*/
insert into user_role (`user_id`, `role_id`) values ( 2,2 );


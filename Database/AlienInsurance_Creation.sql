drop database if exists AlienInsurance;

create database AlienInsurance;

use AlienInsurance;

create table users (
	user_name varchar(30) primary key,
    password char(64) not null,
    date_created datetime not null default now(),
    date_modified datetime null default now(),
    modified_by varchar(30) null,					-- This could the be same user
    active bit not null default 1
) comment 'Generic user.';

create table roles (
	role_type varchar(50) primary key,				-- Type of role
    description varchar(1000) null,					-- Description of role
    active bit not null default 1					-- Is role available?
) comment 'Types of roles in the system';

create table user_roles (
	user_name varchar(30) not null,
    role_type varchar(50) not null,					-- Type of role for user
    date_created datetime not null default now(),
    date_modified datetime null default now(),
    modified_by varchar(30) null,					-- This could the be same user
    active bit not null default 1,					-- Is user still in that role?
    foreign key (user_name) references users(user_name),
    foreign key (role_type) references roles(role_type),
    foreign key (modified_by) references users(user_name),
    primary key (user_name, role_type)
) comment 'Roles for a user.';

create table products (
	product_id int auto_increment primary key,
	title varchar(30) not null,						-- Title of the product
    content varchar(255) not null,					-- Description of product
    cost numeric(15,2) not null,					-- Cost of the product
	date_created datetime not null default now(),
    date_modified datetime null default now(),
    modified_by varchar(30) null,
    active bit not null default 1,
    foreign key (modified_by) references users(user_name)
) comment 'Insurance products.';

create table customer_products (
	user_name varchar(30) not null,
	product_id int not null,
	date_created datetime not null default now(),
    date_modified datetime null default now(),
    modified_by varchar(30) null,					-- This could the be same user
    active bit not null default 1,
    foreign key (user_name) references users(user_name),
    foreign key (product_id) references products(product_id),
    foreign key (modified_by) references users(user_name),
    primary key (user_name, product_id)
) comment 'Products a customer has.';

create table claims (
	claim_id int auto_increment primary key,
	content varchar(255) not null,					-- Customer's story of what happened
    occurance_date datetime not null default now(), -- Date when customer's claim occurred
    claim_by varchar(30) not null,					-- User that made the claim
	date_created datetime not null default now(),	-- Date when claim was submitted
    active bit not null default 1,
    approved bit null,								-- When processed, if it was approved or not
    processed_by varchar(30) null,					-- User that processed the claim
    date_processed datetime null,					-- Date when claim was processed by employee
    foreign key (claim_by) references users(user_name),
    foreign key (processed_by) references users(user_name)
) comment 'Claims made by a customer.';

create table blogs (
	blog_id int auto_increment primary key,
	title varchar(30) not null,
    content varchar(255) not null,
    created_by varchar(30) not null,
    disable_comments bit not null default 0,
	date_created datetime not null default now(),
    date_modified datetime null default now(),
    modified_by varchar(30) null,					-- This could the be same user
    active bit not null default 1,
    foreign key (created_by) references users(user_name)
) comment 'Blog made by a user.';

create table blog_comments (
	blog_comment_id int auto_increment primary key,
	blog_id int not null,
	content varchar(255) not null,
    created_by varchar(30) not null,
	date_created datetime not null default now(),
    date_modified datetime null default now(),
    modified_by varchar(30) null,					-- This could the be same user
    active bit not null default 1,
    foreign key (blog_id) references blogs(blog_id),
    foreign key (created_by) references users(user_name)
) comment 'Comments made for a blog.';

delimiter $$

create procedure sp_insert_user (
	in user_name_param varchar(30),
    in password_param char(64),
    in date_created_param date
)
begin
	insert into users(user_name, password, date_created, date_modified, modified_by, active)
    values(user_name_param, password_param, date_created_param, null, null, default); 
end$$

create procedure sp_insert_role (
	in role_type_param varchar(30),
    in description_param varchar(255)
)
begin
	insert into roles(role_type, description, active)
    values(role_type_param, description_param, default);
end$$

create procedure sp_assign_user_role (
	in user_name_param varchar(30),
    in role_type_param varchar(30),
    in date_created_param datetime
)
begin
	insert into user_roles(user_name, role_type, date_created, date_modified, modified_by, active)
    values(user_name_param, role_type_param, date_created_param, null, null, default);
end$$

create procedure sp_insert_product (
	in title_param varchar(30),
    in content_param varchar(255),
    in cost_param numeric(15,2),
    in date_created_param datetime
)
begin
	insert into products(title, content, cost, date_created, date_modified, modified_by, active)
    values(title_param, content_param, cost_param, date_create_param, null, null, default);
end$$

create procedure sp_assign_customer_product (
	in user_name_param varchar(30),
    in product_id_param int,
    in date_created_param datetime
)
begin
	insert into customer_products(user_name, product_id, date_created, date_modified, modified_by, active)
    values(user_name_param, product_id_param, date_created_param, null, null, default);
end$$

create procedure sp_insert_claim (
	in content_param varchar(255),
    in occurance_date_param datetime,
    in user_name_param varchar(30),
    in date_created_param datetime
)
begin
	insert into claims(content, occurance_date, claim_by, date_created, active, approved, processed_by, date_processed)
    values(content_param, occurance_date_param, user_name_param, date_created_param, default, null, null, null);
end$$

create procedure sp_insert_blog (
	in title_param varchar(30),
    in content_param varchar(255),
    in created_by_param varchar(30),
    in disable_comments_param bit,
    in date_created_param datetime
)
begin
	insert into blogs(title, content, created_by, disable_comments, date_created, date_modified, modified_by, active)
    values(title_param, content_param, created_by_param, disable_comments_param, date_created_param, null, null, default);
end$$

create procedure sp_assign_blog_comment (
	in blog_id_param int,
    in content_param varchar(255),
    in created_by_param varchar(30),
    in date_created_param datetime
)
begin
	insert into blog_comments(blog_id, content, created_by, date_created, date_modified, modified_by, active)
    values(blog_id_param, content_param, created_by_param, date_created_param, null, null, default);
end$$

create procedure sp_update_user (
	in user_name_param varchar(30),
    in password_param char(64),
    in date_modified datetime,
    in modified_by_param varchar(30),
	in active_param bit
)
begin
	if exists (select 1 from users where user_name = user_name) then
		update users
        set password = password_param,
			date_modified = date_modified_param,
			modified_by = modified_by_param,
			active = active_param
        where user_name = user_name_param;
    else
		insert into users(user_name, password, date_created, date_modifed, modified_by, active)
        values(user_name_param, password_param, date_modified, null, null, default);
    end if;
end$$

create procedure sp_update_role (
	in role_type_param varchar(30),
    in description_param varchar(255),
	in active_param bit
)
begin
	if exists (select 1 from roles where role_type = role_type_param) then
		update roles
        set description = description_param,
			active = active_param
        where active = active_param and role_type = role_type_param;
    else
		insert into roles(role_type, description, active)
        values(role_type_param, description_param, active_param);
	end if;
end$$

create procedure sp_update_user_role (
	in user_name_param varchar(30),
    in role_type_param varchar(30),
    in date_modified_param datetime,
    in modified_by_param varchar(30),
	in active_param bit
)
begin
	if exists (select 1 from user_roles where role_type = role_type_param) then
		update user_roles
        set date_modified = date_modified_param,
			modified_by = modified_by_param,
            active = active_param
        where user_name = user_name_param and role_type = role_type_param;
    else
		insert into user_roles(user_name, role_type, date_created, date_modified, modified_by, active)
        values(user_name_param, role_type_param, date_modified_param, null, null, active_param);
    end if;
end$$

create procedure sp_update_product (
	in product_id_param int,
    in title_param varchar(30),
    in content_param varchar(255),
    in cost_param numeric(15,2),
    in date_modified_param datetime,
    in modified_by_param varchar(30),
	in active_param bit
)
begin
	if exists (select 1 from products where product_id = product_id_param) then
		update products
		set title = title_param,
			content = content_param,
            cost = cost_param,
            date_modified = date_modified_param,
			modified_by = modified_by_param,
            active = active_param
        where product_id = product_id_param;
    else
		insert into products(title, content, cost, date_created, date_modified, modified_by, active)
        values(title_param, content_param, cost_param, date_created_param, null, null, active_param);
    end if;
end$$

create procedure sp_update_customer_product (
	in user_name_param varchar(30),
    in product_id_param int,
    in date_modified_param datetime,
    in modified_by_param varchar(30),
	in active_param bit
)
begin
	if exists (select 1 from customer_product where user_name = user_name_param and product_id = product_id_param) then
		update customer_products
        set date_modified = date_modified_param,
			modified_by = modified_by_param,
			active = active_param
        where user_name = user_name_param and product_id = product_id_param;
    else
		insert into customer_products(user_name, product_id, date_created, date_modified, modified_by, active)
        values(user_name_param, product_id_param, date_modified_param, null, null, active_param);
    end if;
end$$

create procedure sp_update_claim (
	in claim_id_param int,
    in approved_param bit,
    in processed_by_param varchar(30),
    in date_processed_param datetime,
	in active_param bit
)
begin
	update claims
    set approved = approved_param,
		processed_by = processed_by_param,
        date_processed = date_processed_param,
        active = active_param
    where claim_id = claim_id_param;
end$$

create procedure sp_update_blog (
	in blog_id_param int,
    in title_param varchar(30),
    in content_param varchar(255),
    in disable_comments_param bit,
    in date_modified_param datetime,
    in modified_by_param varchar(30),
	in active_param bit
)
begin
	if exists (select 1 from blogs where blog_id = blog_id_param) then
		update blogs
        set title = title_param,
			content = content_param,
            disable_comments = disable_comments_param,
            date_modified = date_modified_param,
			modified_by = modified_by_param,
            active = active_param
        where blog_id = blog_id_param;
    else
		insert into blogs(title, content, created_by, disable_comments, date_created, date_modified, modified_by, active)
        values(title_param, content_param, disable_comments_param, date_modified_param, null, null, active_param);
    end if;
end$$

create procedure sp_update_blog_comment (
	in blog_comment_id_param int,
    in content_param varchar(255),
    in date_modified_param datetime,
    in modified_by_param varchar(30),
	in active_param bit
)
begin
	update blog_comments
	set content = content_param,
		date_modified = date_modified_param,
        modified_by = modified_by_param,
		active = active_param
	where blog_comment_id = blog_comment_id_param;
end$$

create procedure sp_select_users (
	in active_param bit
)
begin
	select user_name, date_created, date_modified, modified_by
    from users
    where active = active_param;
end$$

create procedure sp_select_roles (
	in active_param bit
)
begin
	select role_type, description
    from roles
    where active = active_param;
end$$

create procedure sp_select_user_roles (
	in user_name_param varchar(30),
	in active_param bit
)
begin
	select role_type, date_created, date_modified, modified_by
    from user_roles
    where user_name = user_name_param and active = active_param;
end$$

create procedure sp_select_products (
	in active_param bit
)
begin
	select product_id, title, content, cost, date_created, date_modified, modified_by
    from products
    where active = active_param;
end$$

create procedure sp_select_customer_products (
	in user_name_param varchar(30),
	in active_param bit
)
begin
	select product_id, date_created, date_modified, modified_by
    from customer_products
    where user_name = user_name_param and active = active_param;
end$$

create procedure sp_select_product_customers (
	in product_id_param varchar(30),
	in active_param bit
)
begin
	select user_name, date_created, date_modified, modified_by
    from customer_products
    where product_id = product_id_param and active = active_param;
end$$

create procedure sp_select_claims (
	in active_param bit
)
begin
	select claim_id, content, occurance_date, claim_by, date_created, approved, processed_by, date_processed
    from claims
    where active = active_param;
end$$

create procedure sp_select_unprocessed_claims (
	in active_param bit
)
begin
	select claim_id, content, occurance_date, claim_by, date_created
    from claims
    where active = active_param and approved is null;
end$$

create procedure sp_select_processed_claims (
	in active_param bit
)
begin
	select claim_id, content, occurance_date, claim_by, date_created, approved, processed_by, date_processed
    from claims
    where active = active_param and approved is not null;
end$$

create procedure sp_select_blogs (
	in active_param bit
)
begin
	select blog_id, title, content, created_by, disable_comments, date_created, date_modified, modified_by
    from blogs
    where active = active_param;
end$$

create procedure sp_select_blog_comments (
	in active_param bit
)
begin
	select blog_comment_id, blog_id, content, created_by, date_created, date_modified, modified_by
    from blog_comments
    where active = active_param;
end$$

create procedure sp_select_user (
	in user_name_param varchar(30)
)
begin
	select date_created, date_modified, modified_by, active
    from users
    where user_name = user_name_param;
end$$

create procedure sp_select_role (
	in role_type_param varchar(30)
)
begin
	select description, active
    from roles
    where role_type = role_type_param;
end$$

create procedure sp_select_product (
	in product_id_param int
)
begin
	select title, content, cost, date_created, date_modified, modified_by, active
    from products
    where product_id = product_id_param;
end$$

create procedure sp_select_claim (
	in claim_id_param int
)
begin
	select content, occurance_date, claim_by, date_created, active, approved, processed_by, date_processed
    from claims
    where claim_id = claim_id_param;
end$$

create procedure sp_select_user_unprocessed_claims (
	in user_name_param varchar(30),
    in active_param bit
)
begin
	select content , occurance_date, claim_by, date_created, active, approved, processed_by, date_processed
    from claims
    where active = active_param and user_name = user_name_param and approved is null;
end$$

create procedure sp_select_user_processed_claims (
	in user_name_param varchar(30),
    in active_param bit
)
begin
	select content, occurance_date, claim_by, date_created, active, approved, processed_by, date_processed
    from claims
    where active = active_param and user_name = user_name_param and approved is not null;
end$$

create procedure sp_select_blog (
	in blog_id_param int
)
begin
	select title, content, created_by, disable_comments, date_created, date_modified, modified_by, active
    from blogs
    where blog_id = blog_id_param;
end$$

create procedure sp_select_user_blogs (
	in user_name_param varchar(30),
    in active_param bit
)
begin
	select title, content, created_by, disable_comments, date_created, date_modified, modified_by, active
    from blogs
    where active = active_param and created_by = user_name_param; 
end$$

create procedure sp_select_blog_comment (
	in blog_comment_id_param int
)
begin
	select blog_id, content, created_by, date_created, date_modifed, modified_by, active
    from blog_comments
    where blog_comment_id = blog_comment_id_param;
end$$

create procedure sp_select_blog_blog_comments (
	in blog_id_param int,
    in active_param bit
)
begin
	select blog_id, content, created_by, date_created, date_modifed, modified_by, active
    from blog_comments
    where blog_comment_id = blog_comment_id_param;
end$$

create procedure sp_select_user_blog_comments (
	in user_name_param varchar(30),
    in active_param bit
)
begin
	select blog_id, content, created_by, date_created, date_modifed, modified_by, active
    from blog_comments
    where active = active_param and user_name = user_name_param;
end$$

delimiter ; 
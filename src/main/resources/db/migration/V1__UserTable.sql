CREATE TABLE bsuser (
    user_id UUID NOT NULL PRIMARY KEY,
    username VARCHAR(99) NOT NULL,
    first_name VARCHAR(99),
    last_name VARCHAR(99),
    ownedtask VARCHAR(99)
);

CREATE table task (
    task_id serial primary key,
    bsuser UUID references bsuser(user_id),
    task_name VARCHAR(99),
    description text,
    timestamp timestamp
)
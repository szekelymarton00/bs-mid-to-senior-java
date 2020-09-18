CREATE TABLE bsuser (
    id UUID NOT NULL PRIMARY KEY,
    username VARCHAR(99) NOT NULL,
    first_name VARCHAR(99),
    last_name VARCHAR(99),
    ownedtask VARCHAR(99)
);

CREATE table task (
    id serial primary key,
    bsuser UUID references bsuser(id),
    task_name VARCHAR(99),
    description text,
    timestamp timestamp
)
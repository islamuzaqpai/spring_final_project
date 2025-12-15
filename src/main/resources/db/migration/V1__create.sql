create table t_directors (
    id serial primary key,
    name text not null
);

create table t_actors (
    id serial primary key,
    name text not null
);

create table t_movies (
    id serial primary key,
    title text not null,
    director_id integer not null,
    constraint fk_director foreign key (director_id) references t_directors(id)
);

create table t_movie_actor (
    movie_id integer not null,
    actor_id integer not null,
    constraint pk_movie primary key (movie_id, actor_id),
    constraint fk_movie foreign key (movie_id) references t_movies(id),
    constraint fk_actor foreign key (actor_id) references t_actors(id)
);
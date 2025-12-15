insert into t_directors (name) values
                                   ('Aqan Satai'),
                                   ('Aitore Zhalgasqali'),
                                   ('Alisher Utev');

insert into t_actors (name) values
                                ('Asqar Ilyasov'),
                                ('Asylkhan Tolepov'),
                                ('Ayan Otepbergen'),
                                ('Nyshanbek'),
                                ('Daniyar Alshinov');

insert into t_movies (title, director_id) values
                                              ('Biznesmeny', 1),
                                              ('Auru', 2),
                                              ('Munai', 3),
                                              ('Zhau zhurek, myn bala', 1);

insert into t_movie_actor (movie_id, actor_id) values
                                                   (1, 1),
                                                   (2, 3),
                                                   (3, 5),
                                                   (3, 4),
                                                   (4, 2);
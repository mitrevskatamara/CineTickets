insert into city (id, name) values (1, 'Skopje');
insert into city (id, name) values (2, 'Ohrid');
insert into city (id, name) values (3, 'Bitola');

insert into app_user (id, email, username, password, first_name, last_name, roles) values (1, 'admin@test.com', 'admin', 'admin', 'Admin', 'Admin', 'ROLE_ADMIN');
insert into app_user (id, email, username, password, first_name, last_name, roles) values (2, 'user@test.com', 'user', 'user', 'User', 'User', 'ROLE_USER');
insert into app_user (id, email, username, password, first_name, last_name, roles) values (3, 'tamara@test.com', 'tamaramitrevska', 'test12', 'Tamara', 'Mitrevska', 'ROLE_USER');
insert into app_user (id, email, username, password, first_name, last_name, roles) values (4, 'filip@test.com', 'filipbogdanovski', 'test12', 'Filip', 'Bogdanovski', 'ROLE_USER');

insert into cinema (id, name, address, phone_number, image, city_id) values (1, 'Cineplexx Skopje', 'Ul. Ljubljanska br.4', '+389 2 3074 477', '/uploads/images/cinema3.jpg', 1);
insert into cinema (id, name, address, phone_number, image, city_id) values (2, 'Cineplexx Ohrid', 'Bul. Turistichka', '+389 2 3074 478', '/uploads/images/cinema1.jpg', 2);
insert into cinema (id, name, address, phone_number, image, city_id) values (3, 'Cineplexx Bitola', 'Ul. Partizanska', '+389 2 3074 479', '/uploads/images/cinema2.jpg', 3);

insert into movie (id, title, duration, year, rating, director, writer, stars, image, trailer_URL, genre, country, is_showing, description)
    values (1, 'The Batman', 176, 2022, 8.0, 'Matt Reeves', 'Matt Reeves, Peter Craig, Bill Finger', 'Robert Pattinson, Zoë Kravitz, Jeffrey Wright', '/uploads/images/batman.jpg', 'https://www.youtube.com/watch?v=mqqft2x_Aa4&t=7s', 'Action, Crime, Drama', 'USA', true, 'When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city''s hidden corruption and question his family''s involvement.');
insert into movie (id, title, duration, year, rating, director, writer, stars, image, trailer_URL, genre, country, is_showing, description)
    values (5, 'Doctor Strange in the Multiverse of Madness', 126, 2022, 7.4, 'Sam Raimi', 'Michael Waldron', 'Benedict Cumberbatch, Elizabeth Olsen, Chiwetel Ejiofor', '/uploads/images/doctor.jpg', 'https://www.youtube.com/watch?v=aWzlQ2N6qqg', 'Action, Fantasy', 'USA', true, 'Dr. Stephen Strange casts a forbidden spell that opens the doorway to the multiverse, including alternate versions of himself, whose threat to humanity is too great for the combined forces of Strange, Wong, and Wanda Maximoff.');
insert into movie (id, title, duration, year, rating, director, writer, stars, image, trailer_URL, genre, country, is_showing, description)
    values (3, 'Sonic the Hedgehog 2', 126, 2022, 6.7, 'Jeff Fowler', 'Pat Casey', 'James Marsden, Jim Carrey, Ben Schwartz', '/uploads/images/sonic.jpg', 'https://www.youtube.com/watch?v=47r8FXYZWNU', 'Adventure, Comedy', 'USA', true, 'When the manic Dr Robotnik returns to Earth with a new ally, Knuckles the Echidna, Sonic and his new friend Tails is all that stands in their way.');
insert into movie (id, title, duration, year, rating, director, writer, stars, image, trailer_URL, genre, country, is_showing, description)
    values (4, 'The Lost City', 112, 2022, 6.3, 'Aaron Nee, Adam Nee', 'Oren Uziel', 'Sandra Bullock, Channing Tatum, Daniel Radcliffe', '/uploads/images/lostcity.jpg', 'https://www.youtube.com/watch?v=nfKO9rYDmE8', 'Action, Adventure, Comedy', 'USA', true, 'A reclusive romance novelist on a book tour with her cover model gets swept up in a kidnapping attempt that lands them both in a cutthroat jungle adventure.');

insert into showing_type (id, name) values (1, '4K');
insert into showing_type (id, name) values (2, '4K 3D');
insert into showing_type (id, name) values (3, 'Digital 2D');
insert into showing_type (id, name) values (4, 'Digital 3D');
insert into showing_type (id, name) values (5, 'MX4D 2D');
insert into showing_type (id, name) values (6, 'MX4D 3D');

insert into club (id, name, price, image, description) values (1, 'Premium Membership', 2000, '/uploads/images/premium.png', 'Become premium member by paying 2000 MKD yearly. Benefits: 10% discount on ticket prices. Pick up reserved tickets up to 10 minutes before the start of the screening (instead of the usual 30 minutes). An exclusive tour of the Cineplexx cinema. Invitation for premieres and special events.');
insert into club (id, name, price, image, description) values (3, 'Standard Membership' , 1000, '/uploads/images/standard.png', 'Become standard member by paying 1000 MKD yearly. Benefits: Pick up reserved tickets up to 10 minutes before the start of the screening (instead of the usual 30 minutes). Free parking. Invitation for premieres and special events.');
insert into club (id, name, price, image, description) values (2, 'Premium Bonus Card ', 500, '/uploads/images/premium-card.png', 'Buy CineTickets bonus card and use card for the rest of your life. Benefits: 1 bonus point for every MKD spent. Attractive prizes in exchange for point. Buy tickets with earned bonus points. Online check of points status.');

insert into showing(id, date, price, time, hall_id, movie_id, type_id) VALUES (1,'2022-06-06',300,'23:00',1,1,1);
insert into showing(id, date, price, time, hall_id, movie_id, type_id) VALUES (2,'2022-06-07',300,'22:00',1,4,1);
insert into showing(id, date, price, time, hall_id, movie_id, type_id) VALUES (3,'2022-06-08',300,'20:00',1,3,1);
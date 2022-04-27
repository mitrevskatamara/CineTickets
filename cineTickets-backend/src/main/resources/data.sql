insert into cinema(id, address, city, image, name, phone_number) values (1, 'Ul. Ljubljanska', 'Skopje', 'image', 'Cineplexx Skopje City Mall', '+389 2 3074 477');
insert into cinema(id, address, city, image, name, phone_number) values (2, 'Ul. Belasitsa', 'Skopje', 'image', 'Cineplexx East Gate Mall', '+389 2 3074 477');
insert into cinema(id, address, city, image, name, phone_number) values (3, 'Ul. Kej 13ti Noemvri', 'Skopje', 'image', 'Kino Milenium', '+389 2 3120 389');

insert into membership (id, name, price, image, description) values (1, 'CineStar Premium Membership', 2000, 'image', 'Become premium member by paying 5000 RSD yearly. Benefits: 10% discount on ticket prices. Pick up reserved tickets up to 10 minutes before the start of the screening (instead of the usual 30 minutes). An exclusive tour of the CineStar cinema. Invitation for premieres and special events.');
insert into membership (id, name, price, image, description) values (2, 'CineStar Standard Membership' , 1000, 'image', 'Become standard member by paying 2000 RSD yearly. Benefits: Pick up reserved tickets up to 10 minutes before the start of the screening (instead of the usual 30 minutes). Free parking. Invitation for premieres and special events.');
insert into membership (id, name, price, image, description) values (3, 'CineStar Premium Bonus Card ', 500, 'image', 'Buy CineStar bonus card and use card for the rest of your life. Benefits: 1 bonus point for every RSD spent. Attractive prizes in exchange for point. Buy tickets with earned bonus points. Online check of points status.');

insert into movie(id, director, duration, country, description, genre, is_showing, rating, title, trailerurl, year) values (1, 'Jon Watts', '2h 28m', 'USA', 'With Spider-Man''s identity now revealed, our friendly neighborhood web-slinger is unmasked and no longer able to separate his normal life as Peter Parker from the high stakes of being a superhero. When Peter asks for help from Doctor Strange, the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.', 'Action/Adventure', 1, 8.5, 'Spider-Man: No Way Home', 'https://www.youtube.com/watch?v=JfVOs4VSpmA', '2021');
insert into movie(id, director, duration, country, description, genre, is_showing, rating, title, trailerurl, year) values (2, 'Matt Reeves', '2h 56m', 'USA', 'Batman ventures into Gotham City''s underworld when a sadistic killer leaves behind a trail of cryptic clues. As the evidence begins to lead closer to home and the scale of the perpetrator''s plans become clear, he must forge new relationships, unmask the culprit and bring justice to the abuse of power and corruption that has long plagued the metropolis.', 'Action/Adventure', 1, 8.3, 'The Batman', 'https://www.youtube.com/watch?v=mqqft2x_Aa4', '2022');
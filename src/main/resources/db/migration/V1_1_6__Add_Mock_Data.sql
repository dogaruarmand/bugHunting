--insert values in category table
insert into categories (category)
values
('Exotic'),
('Aquatic'),
('Birds'),
('Cat');

insert into categories2 (category)
values
('Exotic'),
('Aquatic'),
('Birds'),
('Cat');

--insert values in users table
insert into users (first_name, last_name, phone, email, password, role)
values ('Andrei', 'Serdulet', '+40712345678', 'andrei.serdulet@endava.com', 'Parola12','ADMIN');

insert into users2 (first_name, last_name, phone, email, password, role)
values ('Andrei', 'Serdulet', '+40712345678', 'andrei.serdulet@endava.com', 'Parola12','ADMIN');

--insert values in locations table
insert into locations (name)
values
('Spain'),
('Austria'),
('France'),
('Venezuela'),
('Italy'),
('Hungary'),
('Portugal'),
('Romania'),
('Poland'),
('Mars'),
('Australia'),
('Unknown'),
('India');

insert into locations2(name)
values
('Spain'),
('Austria'),
('France'),
('Venezuela'),
('Italy'),
('Hungary'),
('Portugal'),
('Romania'),
('Poland'),
('Mars'),
('Australia'),
('Unknown'),
('India');

--insert values in pets table
insert into pets(name, age, owners_name, owners_phone, owners_email, gender, addopted, category_id,location_id, description, foster_user_id, user_id, image)
values
('M''key', 4, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 1, 1,
'Meet M''key, a regal pet figure of a princess. While she''s every bit a princess in demeanor and dress, M''key retains the playful spirit of a monkey, ensuring that her reign is filled with laughter, and the occasional stolen banana.',
 null, 1, 'monkey.png'),
 ('Fro''Gleam', 3, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 2, 2,
'Meet Fro''Gleam, the pet of amphibian elegance. This sophisticated frog doesn''t hop around the pond without her lustrous pearls adorning her delicate neck. By day, you,ll find her catching the dragonflies that admire her pearls. By night, she serenades the moon with her melodious croaks. But don''t let her grace and charm, fool you - she''s still a friendly leapy froggy.',
 null, 1, 'frog.png'),
 ('Tuturkey', 1, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 3, 3,
'One of the world-class ballerinas, this is Tuturkey, the feathered prima donna. This isn''t just any turkey - she''s a vision of balletic grace with her tutu dress. Tuturkey is a reminder that passion and flair can be found in the most unexpected places, her pirouettes.',
 null, 1, 'turkey.png'),
 ('Don Capybara', 10, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 1, 4,
'One of the world-class ballerinas, this is Tuturkey, the feathered prima donna. This isn''t just any turkey - she''s a vision of balletic grace with her tutu dress. Tuturkey is a reminder that passion and flair can be found in the most unexpected places, her pirouettes.',
 null, 1, 'capybara.png'),
 ('Lizzie the Ballerina', 2, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 2, 5,
'Her tail sways gracefully as she attempts to pirouette on her four tiny feet, a sight that''s equal parts endearing and comedic. Lizzie''s big, expressive eyes seem to light up with joy whenever she "dances" making it impossible not to smile when you see her in action.',
 null, 1, 'Lizzy.png'),
 ('Olive', 25, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 3, 6,
'The forest becomes her opera house, and Olive the star of the evening, her arias echoing through the trees and captivating all who hear her. She produces a series of melodious notes.',
 null, 1, 'Owl.png'),
 ('Oscar the Disco Okapi', 18, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 1, 7,
'Oscar moves his hooves in rhythm to the beat of ''70s classics. Her long neck sways gracefully as he twirls and shimmies, capturing the attention and hearts of every creature in the discotheque. With Oscar on the dance floor, it''s impossible not to catch the fever of Saturday night.',
 null, 1, 'okapi.png'),
 ('Bobocel', 0, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 3, 8,
'Bobocel is an irresistibly adorable duckling with soft, fluffy, yellow feathers that seem to glow in the sunlight. Just for the record, he doesn''t quack.',
 null, 1, 'Bobocel.png'),
 ('Leonard', 19, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 4, 9,
'Leonard captivates everyone around him, exuding an air of elegance that''s simply timeless.',
 null, 1, 'Lion.png'),
('Astro the Spacefaring Aardvark', 22, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 1, 10,
'Astro looks like he''s ready to explore the final frontier rather than burrowing into anthills. His suit is emblazoned with patches that mimic those of NASA.',
 null, 1, 'Aardvark.png'),
 ('Sclify', 79, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 2, 11,
'As Sclify meanders gracefully along, her dress flutters in the breeze, giving her the appearance of a floating garden. While she may not win any races, Sclify certainly wins hearts, her style exuding a unique blend of natural beauty and whimsical fashion. In her case, age is just a number.',
 null, 1, 'Sclify.png'),
 ('Bruv', 16, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 1, 12,
'The natural lifespan of a pig is 15-20 years. They are not often allowed to live twenty years on farms as they are popular livestock animals. If they are being grown for food they are usually slaughtered at six months of age. Somehow this was not the case for Bruv, he is living the life.',
 null, 1, 'pig.png'),
 ('Roopak Jar', 20, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Both', 'NO', 1, 13,
'A charming snail with a spiral shell that sports shades of deep brown and amber. It is dressed in an intricately designed, miniature Indian costume.',
 null, 1, 'snail.png');

 insert into pets2(name, age, owners_name, owners_phone, owners_email, gender, addopted, category_id,location_id, description, foster_user_id, user_id, image)
values
('M''key', 4, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 1, 1,
'Meet M''key, a regal pet figure of a princess. While she''s every bit a princess in demeanor and dress, M''key retains the playful spirit of a monkey, ensuring that her reign is filled with laughter, and the occasional stolen banana.',
 null, 1, 'monkey.png'),
 ('Fro''Gleam', 3, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 2, 2,
'Meet Fro''Gleam, the pet of amphibian elegance. This sophisticated frog doesn''t hop around the pond without her lustrous pearls adorning her delicate neck. By day, you,ll find her catching the dragonflies that admire her pearls. By night, she serenades the moon with her melodious croaks. But don''t let her grace and charm, fool you - she''s still a friendly leapy froggy.',
 null, 1, 'frog.png'),
 ('Tuturkey', 1, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 3, 3,
'One of the world-class ballerinas, this is Tuturkey, the feathered prima donna. This isn''t just any turkey - she''s a vision of balletic grace with her tutu dress. Tuturkey is a reminder that passion and flair can be found in the most unexpected places, her pirouettes.',
 null, 1, 'turkey.png'),
 ('Don Capybara', 10, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 1, 4,
'One of the world-class ballerinas, this is Tuturkey, the feathered prima donna. This isn''t just any turkey - she''s a vision of balletic grace with her tutu dress. Tuturkey is a reminder that passion and flair can be found in the most unexpected places, her pirouettes.',
 null, 1, 'capybara.png'),
 ('Lizzie the Ballerina', 2, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 2, 5,
'Her tail sways gracefully as she attempts to pirouette on her four tiny feet, a sight that''s equal parts endearing and comedic. Lizzie''s big, expressive eyes seem to light up with joy whenever she "dances" making it impossible not to smile when you see her in action.',
 null, 1, 'Lizzy.png'),
 ('Olive', 25, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 3, 6,
'The forest becomes her opera house, and Olive the star of the evening, her arias echoing through the trees and captivating all who hear her. She produces a series of melodious notes.',
 null, 1, 'Owl.png'),
 ('Oscar the Disco Okapi', 18, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 1, 7,
'Oscar moves his hooves in rhythm to the beat of ''70s classics. Her long neck sways gracefully as he twirls and shimmies, capturing the attention and hearts of every creature in the discotheque. With Oscar on the dance floor, it''s impossible not to catch the fever of Saturday night.',
 null, 1, 'okapi.png'),
 ('Bobocel', 0, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 3, 8,
'Bobocel is an irresistibly adorable duckling with soft, fluffy, yellow feathers that seem to glow in the sunlight. Just for the record, he doesn''t quack.',
 null, 1, 'Bobocel.png'),
 ('Leonard', 19, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 4, 9,
'Leonard captivates everyone around him, exuding an air of elegance that''s simply timeless.',
 null, 1, 'Lion.png'),
('Astro the Spacefaring Aardvark', 22, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 1, 10,
'Astro looks like he''s ready to explore the final frontier rather than burrowing into anthills. His suit is emblazoned with patches that mimic those of NASA.',
 null, 1, 'Aardvark.png'),
 ('Sclify', 79, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Female', 'NO', 2, 11,
'As Sclify meanders gracefully along, her dress flutters in the breeze, giving her the appearance of a floating garden. While she may not win any races, Sclify certainly wins hearts, her style exuding a unique blend of natural beauty and whimsical fashion. In her case, age is just a number.',
 null, 1, 'Sclify.png'),
 ('Bruv', 16, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Male', 'NO', 1, 12,
'The natural lifespan of a pig is 15-20 years. They are not often allowed to live twenty years on farms as they are popular livestock animals. If they are being grown for food they are usually slaughtered at six months of age. Somehow this was not the case for Bruv, he is living the life.',
 null, 1, 'pig.png'),
 ('Roopak Jar', 20, 'Andrei', '+40712345678', 'andrei.serdulet@endava.com', 'Both', 'NO', 1, 13,
'A charming snail with a spiral shell that sports shades of deep brown and amber. It is dressed in an intricately designed, miniature Indian costume.',
 null, 1, 'snail.png');


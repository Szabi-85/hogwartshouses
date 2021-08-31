INSERT INTO building (number_of_rooms) VALUES (10);

INSERT INTO room (building_id, room_number, capacity, number_of_beds, empty_bed) VALUES (1, 1, 1, 10, true);

INSERT INTO student (name, house_type, pet_type, pure_blood) VALUES ('Harry Potter', 'GRYFFINDOR', 'OWL', true);
INSERT INTO student (name, house_type, pet_type, pure_blood) VALUES ('Hermione Granger', 'GRYFFINDOR', 'CAT', false);

INSERT INTO resident (student_id, room_id) VALUES (1, 1);
INSERT INTO resident (student_id, room_id) VALUES (2, 1);

INSERT INTO picture (name, building_id) VALUES ('Mona Lisa', 1);

INSERT INTO recipe (name) VALUES ('Black Fire potion');
INSERT INTO recipe (name) VALUES ('Cupid Crystals');

INSERT INTO ingredient (recipe_id, ingredient) VALUES (1, 'BAT_WING');
INSERT INTO ingredient (recipe_id, ingredient) VALUES (1, 'BEETLE_EYE');
INSERT INTO ingredient (recipe_id, ingredient) VALUES (1, 'CINNAMON');
INSERT INTO ingredient (recipe_id, ingredient) VALUES (1, 'DRAGON_BLOOD');
INSERT INTO ingredient (recipe_id, ingredient) VALUES (1, 'EYEBALL');
INSERT INTO ingredient (recipe_id, ingredient) VALUES (2, 'FROG');
INSERT INTO ingredient (recipe_id, ingredient) VALUES (2, 'GRIFFIN_CLAW');
INSERT INTO ingredient (recipe_id, ingredient) VALUES (2, 'IGUANA_BLOOD');
INSERT INTO ingredient (recipe_id, ingredient) VALUES (2, 'LAVENDER');
INSERT INTO ingredient (recipe_id, ingredient) VALUES (2, 'LIONFISH');
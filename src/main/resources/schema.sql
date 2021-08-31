DROP TABLE IF EXISTS room CASCADE;
DROP SEQUENCE IF EXISTS room_id_seq;
CREATE TABLE room
(
    id                long identity NOT NULL PRIMARY KEY,
    building_id       long,
    room_number       INTEGER,
    capacity          INTEGER,
    number_of_beds    INTEGER,
    empty_bed         BOOLEAN
);

DROP TABLE IF EXISTS student CASCADE;
DROP SEQUENCE IF EXISTS student_id_seq;
CREATE TABLE student
(
    id                long identity NOT NULL PRIMARY KEY,
    name              TEXT,
    house_type        ENUM('GRYFFINDOR', 'HUFFLEPUFF', 'RAVENCLAW', 'SLYTHERIN'),
    pet_type          ENUM('CAT', 'RAT', 'OWL', 'NONE'),
    pure_blood        BOOLEAN
);

DROP TABLE IF EXISTS resident CASCADE;
DROP SEQUENCE IF EXISTS resident_id_seq;
CREATE TABLE resident
(
    id                long identity NOT NULL PRIMARY KEY,
    student_id        long,
    room_id           long
);

DROP TABLE IF EXISTS building CASCADE;
DROP SEQUENCE IF EXISTS building_id_seq;
CREATE TABLE building
(
    id                long identity NOT NULL PRIMARY KEY,
    number_of_rooms   INTEGER
);

DROP TABLE IF EXISTS picture CASCADE;
DROP SEQUENCE IF EXISTS picture_id_seq;
CREATE TABLE picture
(
    id                long identity NOT NULL PRIMARY KEY,
    name              TEXT,
    building_id       long
);

DROP TABLE IF EXISTS recipe CASCADE;
DROP SEQUENCE IF EXISTS recipe_id_seq;
CREATE TABLE recipe
(
    id                long identity NOT NULL PRIMARY KEY,
    name              TEXT
);

DROP TABLE IF EXISTS ingredient CASCADE;
-- DROP SEQUENCE IF EXISTS ingredient_id_seq;
CREATE TABLE ingredient
(
    recipe_id         long,
    ingredient        ENUM('BAT_WING', 'BEETLE_EYE', 'CINNAMON', 'DRAGON_BLOOD', 'EYEBALL', 'FROG', 'GRIFFIN_CLAW', 'IGUANA_BLOOD', 'LAVENDER', 'LIONFISH', 'MINT', 'MUSHROOM', 'PEPPERMINT', 'RAT_TAIL', 'ROSE_OIL', 'SNAKE', 'SPIDER', 'THYME', 'TOOTH_OF_WOLF', 'WATER')
);

DROP TABLE IF EXISTS student_recipe CASCADE;
--DROP SEQUENCE IF EXISTS student_recipe_id_seq;
CREATE TABLE student_recipe
(
    student_id        long,
    recipe_id         long
);

ALTER TABLE room
    ADD FOREIGN KEY (building_id)
        REFERENCES building(id)
        ON DELETE SET NULL;

ALTER TABLE resident
    ADD FOREIGN KEY (student_id)
        REFERENCES student(id)
        ON DELETE CASCADE;

ALTER TABLE resident
    ADD FOREIGN KEY (room_id)
        REFERENCES room(id)
        ON DELETE CASCADE;

ALTER TABLE picture
    ADD FOREIGN KEY (building_id)
        REFERENCES building(id)
        ON DELETE SET NULL;

ALTER TABLE ingredient
    ADD FOREIGN KEY (recipe_id)
        REFERENCES recipe(id)
        ON DELETE CASCADE;

ALTER TABLE student_recipe
    ADD FOREIGN KEY (student_id)
        REFERENCES student(id)
        ON DELETE CASCADE;

ALTER TABLE student_recipe
    ADD FOREIGN KEY (recipe_id)
        REFERENCES recipe(id)
        ON DELETE CASCADE;
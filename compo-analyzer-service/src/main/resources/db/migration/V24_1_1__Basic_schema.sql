CREATE SCHEMA IF NOT EXISTS component;

CREATE TABLE component.t_naturalness_category(
    id SERIAL PRIMARY KEY,
    c_name TEXT NOT NULL UNIQUE
);

CREATE TABLE component.t_component(
    id SERIAL PRIMARY KEY,
    c_inci_name TEXT NOT NULL UNIQUE,
    c_danger_factor INTEGER CHECK(c_danger_factor BETWEEN 0 AND 10),
    naturalness_id INTEGER REFERENCES component.t_naturalness_category (id)
);

CREATE TABLE component.t_cosmetic_property(
    id SERIAL PRIMARY KEY,
    c_name TEXT NOT NULL UNIQUE
);

CREATE TABLE component.t_cosmetic_feature(
    id SERIAL PRIMARY KEY,
    component_id INTEGER NOT NULL REFERENCES component.t_component (id),
    cosmetic_property_id INTEGER NOT NULL REFERENCES component.t_cosmetic_property (id),
    c_value INTEGER NOT NULL CHECK (c_value BETWEEN 0 AND 10)
);

CREATE TABLE component.t_skin_type(
    id SERIAL PRIMARY KEY,
    c_name TEXT NOT NULL UNIQUE
);

CREATE TABLE component.t_skin_type_component(
    skin_type_id INTEGER NOT NULL REFERENCES component.t_skin_type (id),
    component_id INTEGER NOT NULL REFERENCES component.t_component (id)
)
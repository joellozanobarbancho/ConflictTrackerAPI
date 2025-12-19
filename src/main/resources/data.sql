-- ============================
-- COUNTRIES
-- ============================
INSERT INTO countries (id, name, code) VALUES
  (1, 'Spain', 'ESP'),
  (2, 'France', 'FRA'),
  (3, 'Germany', 'DEU'),
  (4, 'Russia', 'RUS'),
  (5, 'Ukraine', 'UKR');

-- ============================
-- CONFLICTS
-- ============================
INSERT INTO conflicts (id, name, city_date, status, city_description) VALUES
  (1, 'Eastern Border Tensions', '2022-02-24', 'ACTIVE', 'Military escalation in the eastern region'),
  (2, 'Mediterranean Naval Dispute', '2021-06-10', 'FROZEN', 'Territorial waters dispute between neighboring states'),
  (3, 'Northern Cyber Conflict', '2020-11-01', 'ENDED', 'Series of cyberattacks between major powers');

-- ============================
-- CONFLICT–COUNTRY RELATION
-- ============================
INSERT INTO conflict_countries (conflict_id, country_id) VALUES
  (1, 4), -- Russia
  (1, 5), -- Ukraine
  (2, 1), -- Spain
  (2, 2), -- France
  (3, 3); -- Germany

-- ============================
-- FACTIONS
-- ============================
INSERT INTO factions (id, name, conflict_id) VALUES
  (1, 'Eastern Alliance', 1),
  (2, 'Western Coalition', 1),
  (3, 'Mediterranean Patrol', 2);

-- ============================
-- FACTION–COUNTRY RELATION
-- ============================
INSERT INTO faction_countries (faction_id, country_id) VALUES
  (1, 4), -- Russia
  (2, 5), -- Ukraine
  (2, 3), -- Germany
  (3, 1), -- Spain
  (3, 2); -- France

-- ============================
-- EVENTS
-- ============================
INSERT INTO events (id, event_date, location, description, conflict_id) VALUES
  (1, '2022-03-01', 'Kyiv', 'Major escalation reported near the capital', 1),
  (2, '2022-04-15', 'Donetsk', 'Ceasefire negotiations attempted', 1),
  (3, '2021-07-20', 'Mediterranean Sea', 'Naval standoff between patrol ships', 2),
  (4, '2020-12-05', 'Berlin', 'Cybersecurity summit held after attacks', 3);

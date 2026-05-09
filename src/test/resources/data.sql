DELETE FROM player;
DELETE FROM coach;
DELETE FROM team;

INSERT INTO `team`(id, name, team_abbr, location)
VALUES (1, 'Golden State Warriors', 'GSW', 'Oakland/California'),
(2, 'San Antonio Spurs', 'SAS', 'San Antonio/Texas'),
(3, 'Los Angeles Clippers', 'LAC', 'Los Angeles/California'),
(4, 'Phoenix Suns', 'PHO', 'Phoenix/Arizona'),
(5, 'Chicago Bulls', 'CHI', 'Chicago/Illinois');


INSERT INTO `coach`(id, name, age, team_id)
VALUES (1, 'Steve Kerr', 50, 1),
(2, 'Gregg Popovich', 70, 2),
(3, 'Mike Malone', 55,3),
(4, 'Jason Kidd', 45,4),
(5, 'Frank Vogel	', 50,5);


INSERT INTO `player`(id, name, pos, age, team_id)
VALUES (1, 'Stephen Curry', 'PG', 34, 1),
(2, 'Klay Thompson', 'SG', 33, 1),
(3, 'Draymond Green', 'PF', 32, 1),
(4, 'Andre Iguodala', 'PF', 32, 1),
(5, 'Khem Birch', 'C', 30, 2),
(6, 'Zach Collins', 'PF', 25, 2),
(7, 'Devonte'' Graham', 'PG', 28, 2),
(8, 'Doug McDermott', 'SF', 31, 2),
(9, 'Paul George', 'SG', 32, 3),
(10, 'Russell Westbrook', 'PG', 34, 3),
(11, 'Kawhi Leonard', 'SF', 31, 3),
(12, 'Ivica Zubac', 'C', 25, 3),
(13, 'Devin Booker', 'SG', 26, 4),
(14, 'Kevin Durant', 'PF', 34, 4),
(15, 'Chris Paul', 'PF', 37, 4),
(16, 'Deandre Ayton', 'C', 24, 4),
(17, 'Lonzo Ball', 'PG', 25, 5),
(18, 'Alex Caruso', 'SG', 28, 5),
(19, 'DeMar DeRozan', 'SF', 28, 5),
(20, 'Zach LaVine', 'SG', 27, 5);


INSERT INTO story (body,created_at,title,author,img,published_date,updated_at) VALUES
('quia et suscipit
suscipit recusandae consequuntur expedita et cum
reprehenderit molestiae ut ut quas totam
nostrum rerum est autem sunt rem eveniet architecto','2026-03-07 06:28:02.196236','accusamus beatae ad facilis cum similique qui sunt','Leanne Graham','https://via.placeholder.com/150/92c952','2026-03-07 03:30:00','2026-03-07 13:28:02.196318+07'),
('est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla','2026-03-07 06:28:02.219576','reprehenderit est deserunt velit ipsam','Ervin Howell','https://via.placeholder.com/150/771796','2026-02-10 05:50:00','2026-03-07 13:28:02.219599+07'),
('et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut','2026-03-07 06:28:02.220893','officia porro iure quia iusto qui ipsa ut modi','Clementine Bauch','https://via.placeholder.com/150/24f355','2026-02-25 02:00:00','2026-03-07 13:28:02.220907+07'),
('ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit','2026-03-07 06:28:02.221689','culpa odio esse rerum omnis laboriosam voluptate repudiandae','Patricia Lebsack','https://via.placeholder.com/150/d32776','2026-01-31 05:00:00','2026-03-07 13:28:02.221699+07');

INSERT INTO post (body,title) VALUES
('body1','title1'),
('body2','tit`le2'),
('body3','title3'),
('body4','title4');

INSERT INTO "comment" (body,email,"name",post_id,story_id) VALUES
('body1','mail1','name1', (SELECT min(id) FROM post), (SELECT min(id) FROM story)),
('body2','mail2','name2', (SELECT min(id) FROM post), (SELECT min(id) FROM story)),
('body3','mail3','name3', (SELECT min(id) FROM post), (SELECT min(id)+1 FROM story)),
('body4','mail4','name4', (SELECT max(id) FROM post), (SELECT min(id)+1 FROM story));
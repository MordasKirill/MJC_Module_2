create table certificate
(
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    name             varchar(45)  null,
    price            double       null,
    duration         int          null,
    create_date      varchar(45)  null,
    last_update_date varchar(45)  null,
    description      varchar(100) null
);

create table tag
(
    id   int         not null
        primary key,
    name varchar(45) null
);

create table certificate_has_tag
(
    cerf_id int not null,
    tag_id              int not null,
    primary key (cerf_id, tag_id),
    constraint fk_cerf_has_tag_cerf
        foreign key (cerf_id) references certificate (id) on delete cascade on update cascade ,
    constraint fk_cerf_has_tag_tag1
        foreign key (tag_id) references tag (id) on delete cascade on update cascade
);

create index fk_cerf_has_tag_cerf_idx
    on certificate_has_tag (cerf_id);

create index fk_cerf_has_tag_tag1_idx
    on certificate_has_tag (tag_id);

INSERT INTO certificate
(id,
 name,
 price,
 duration,
 create_date,
 last_update_date,
 description)
VALUES (1, 'Cinema', 15.2, 10, '2021-10-04', '2021-10-04', 'description'),
       (2, 'Boxing', 16.2, 4, '2021-10-04', '2021-10-04', 'description'),
       (3, 'Medicine', 11.2, 5, '2021-10-04', '2021-10-04', 'description'),
       (4, 'Cycling', 5.2, 2, '2021-10-04', '2021-10-04', 'description'),
       (5, 'Skating', 1.2, 5, '2021-10-04', '2021-10-04', 'description');

INSERT INTO tag (id, name) values (1, 'cinema'),
                                  (2, 'fitness'),
                                  (3, 'food'),
                                  (4, 'test');
INSERT INTO certificate_has_tag (cerf_id, tag_id) values (1,1),
                                                         (1,2),
                                                         (2,2),
                                                         (3,3),
                                                         (4,4),
                                                         (5,4)
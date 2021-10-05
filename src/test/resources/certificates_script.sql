create table gift_certificate
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

create table gift_certificate_tag
(
    gift_certificate_id int not null,
    tag_id              int not null,
    primary key (gift_certificate_id, tag_id),
    constraint fk_gift_certificate_has_tag_gift_certificate
        foreign key (gift_certificate_id) references gift_certificate (id),
    constraint fk_gift_certificate_has_tag_tag1
        foreign key (tag_id) references tag (id)
);

create index fk_gift_certificate_has_tag_gift_certificate_idx
    on gift_certificate_tag (gift_certificate_id);

create index fk_gift_certificate_has_tag_tag1_idx
    on gift_certificate_tag (tag_id);

INSERT INTO gift_certificate
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
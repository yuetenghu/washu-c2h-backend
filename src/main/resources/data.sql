insert into Account (id, surname, given_name) values (10001, 'Who', 'Doctor');
insert into Driver values (10001);

insert into Account (id, surname, given_name) values (10002, 'TestSurname', 'TestGivenName');
insert into Driver values (10002);

insert into Account (id, surname, given_name) values (10003, 'Pond', 'Emily');
insert into Rider (id, status, station) values (10003, 'GRAD', 'MALLINCKRODT');


insert into Trip (id, driver_id, passcode, is_route_up_to_date, start_time, finish_time)
    values (20001, 10001, 'TAR256', false, now(), now());

insert into Addr (id, trip_id, rider_id, addr, lat, lng, has_arrived, boarding_time, arrival_time, seq_id)
    values (30001, 20001, 10003, '210 Summerhill Dr, Ithaca, NY', 42.437409, -76.459810, false, now(), null, -1);


insert into Trip (id, driver_id, passcode, is_route_up_to_date, start_time, finish_time)
    values (20002, 10001, 'TAR256', false, now(), now());
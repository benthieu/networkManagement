
    alter table Device 
        drop constraint FK_lvhyp1m4w4c2kfe4m0978rcli

    alter table Device_Network 
        drop constraint FK_add7kw1vlj4gau6x2xg1sacsh

    alter table Device_Network 
        drop constraint FK_a69f54tyq8ji7hw22bk5kpm7l

    alter table Device_User 
        drop constraint FK_t05vxkmm0tvqmdsj9rpejw1ux

    alter table Device_User 
        drop constraint FK_4ql00flu3v1jen2mn9bwlavv1

    drop table Device if exists

    drop table Device_Network if exists

    drop table Device_User if exists

    drop table Network if exists

    drop table OperatingSystem if exists

    drop table User if exists

    drop sequence hibernate_sequence

    create table Device (
        id bigint not null,
        brand_description varchar(255),
        brand_headquarters varchar(255),
        brand_name varchar(255),
        description varchar(255),
        name varchar(255),
        os_id bigint,
        primary key (id)
    )

    create table Device_Network (
        devices_id bigint not null,
        networks_id bigint not null
    )

    create table Device_User (
        devices_id bigint not null,
        owners_id bigint not null
    )

    create table Network (
        id bigint not null,
        description varchar(255),
        name varchar(255),
        primary key (id)
    )

    create table OperatingSystem (
        id bigint not null,
        brand_description varchar(255),
        brand_headquarters varchar(255),
        brand_name varchar(255),
        name varchar(255),
        primary key (id)
    )

    create table User (
        id bigint not null,
        email varchar(255),
        firstname varchar(255),
        lastname varchar(255),
        primary key (id)
    )

    alter table Device 
        add constraint FK_lvhyp1m4w4c2kfe4m0978rcli 
        foreign key (os_id) 
        references OperatingSystem

    alter table Device_Network 
        add constraint FK_add7kw1vlj4gau6x2xg1sacsh 
        foreign key (networks_id) 
        references Network

    alter table Device_Network 
        add constraint FK_a69f54tyq8ji7hw22bk5kpm7l 
        foreign key (devices_id) 
        references Device

    alter table Device_User 
        add constraint FK_t05vxkmm0tvqmdsj9rpejw1ux 
        foreign key (owners_id) 
        references User

    alter table Device_User 
        add constraint FK_4ql00flu3v1jen2mn9bwlavv1 
        foreign key (devices_id) 
        references Device

    create sequence hibernate_sequence start with 1

create table ms_products
(
    cd_idnt_order UUID default uuid_generate_v4() not null,
    nm_product varchar(255) not null,
    nr_qty_product int not null,
    vl_total_value decimal(10,2) not null,
    primary key(cd_idnt_order)
);

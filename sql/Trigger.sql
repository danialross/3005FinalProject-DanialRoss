create Trigger updateStock
after update
on book
for each row
execute procedure addStock();
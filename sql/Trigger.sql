/* activate trigger when an update has occur */
create Trigger updateStock
after update
on book
for each row
execute procedure addStock();

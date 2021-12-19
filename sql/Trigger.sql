/* activate trigger when an update has occur in the book table*/
create Trigger updateStock
after update
on book
for each row
execute procedure addStock();

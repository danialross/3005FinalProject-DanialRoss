Create function addStock()
	Returns trigger 
	language PLPGSQL as
	
$$
begin
if quantity_available<20 then 
update book
set quantity_available = quantity_available + (select count(*) from placeorder where isbn = new.isbn);
end if ;
return new;
end;
$$










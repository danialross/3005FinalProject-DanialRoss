delete from placeOrder;
delete from Orders;
delete from Book;
delete from Publisher;
delete from Members;
delete from Contact_info;
delete from Contact_info;
delete from City_and_states;
delete from Bank_acc;

insert into Bank_acc values ('1', '1556321423');
insert into Bank_acc values ('2', '7532311153');
insert into Bank_acc values ('3', '1551234216');
insert into Bank_acc values ('4', '1556806538');
insert into Bank_acc values ('5', '3793856835');
insert into Bank_acc values ('6', '7743264474');
insert into Bank_acc values ('7', '9869346557');
insert into Bank_acc values ('8', '1752461423');
insert into Bank_acc values ('9', '1559755423');
insert into Bank_acc values ('10', '1552352423');

--members
insert into City_and_states values('1','Ottawa','ON','K1S4R2');
insert into City_and_states values('2','St.John','NL','T0H4R2');
insert into City_and_states values('3','Downtown','ON','M4C4R2');
insert into City_and_states values('4','Burnaby','BC','V6B5R2');
insert into City_and_states values('5','Cochrane','AB','T1Y4Y2');
insert into City_and_states values('6','Barrhaven','ON','K2J0C2');
insert into City_and_states values('7','Mississauga','ON','K1S4R2');
insert into City_and_states values('8','Surrey','BC','V9B4R2');
insert into City_and_states values('9','Victoria','BC','V8P4R2');
insert into City_and_states values('10','Kanata','ON','K5J4R2');
--publishers
insert into City_and_states values('11','Asbestos','QC','J1T8X1');
insert into City_and_states values('12','Nelson','BC','V1L6T6');
insert into City_and_states values('13','Plaster Rock','NB','E7G0J9');
insert into City_and_states values('14','Saint-Bruno','QC','J3V2C3');
insert into City_and_states values('15','Millville','NB','E6E3Y8');
insert into City_and_states values('16','Saint-Prime','QC','G8J6H9');
insert into City_and_states values('17','Hébertville','QC','G8N6S9');
insert into City_and_states values('18','Sidney','BC','V8L5G6');
insert into City_and_states values('19','Pont-Viau','QC','H7G7L9');
insert into City_and_states values('20','Midland','ON','L4R7B7');

--members
insert into Contact_info values('1','John','Wick','johnnywick22@gmail.com','5145235785');
insert into Contact_info values('2','Samuel','Thompson','stompson5@gmail.com','5146568868');
insert into Contact_info values('3','Jeremy','Watson','jeremywatson@gmail.com','5146727459');
insert into Contact_info values('4','Ash','Wally','ashally@gmail.com','5734278559');
insert into Contact_info values('5','Remy','Ymir','rmir@gmail.com','7524527859');
insert into Contact_info values('6','Yasseen','Johnson','Yasjohn@gmail.com','7254527859');
insert into Contact_info values('7','Tyra','West','twest@gmail.com','5141347759');
insert into Contact_info values('8','Stephanie','Nguyen','stephnguyen@gmail.com','8452857859');
insert into Contact_info values('9','Wally','Kingston','kingston64@gmail.com','5148251579');
insert into Contact_info values('10','Nathan','Grim','nathangrim@gmail.com','5146141759');
--publishers
insert into Contact_info values('11','Stephan','King','StephanKing@gmail.com','5149767859');
insert into Contact_info values('12','Louis','Carrol','LouisCarrol@gmail.com','6136527859');
insert into Contact_info values('13','Mark','Twain','MarkTwain@gmail.com','7524527859');
insert into Contact_info values('14','Albert','Kamou','AlbertKamou@gmail.com','8343427859');
insert into Contact_info values('15','George','Owell','GeorgeOwell@gmail.com','1351627859');
insert into Contact_info values('16','Larry','sanchez','Larrysanchez@gmail.com','7426527859');
insert into Contact_info values('17','Edgar','Allen','EdgarAllen@gmail.com','8545627859');
insert into Contact_info values('18','Miles','Owen','MilesOwen@gmail.com','8358356236');
insert into Contact_info values('19','Washington','Omelly','WashingtonOmelly@gmail.com','9637323646');
insert into Contact_info values('20','Sandra','Kim','SandraKim@gmail.com','5146246859');

insert into Members values('1','31','St. John','1','31','St. John','1','1','123');
insert into Members values('2','7605','Saxon Rd','2','7605','Saxon Rd','2','2','123');
insert into Members values('3','886','Cross Lane','3','886','Cross Lane','3','3','123');
insert into Members values('4','8549','Center Dr.','4','8549','Center Dr.','4','4','123');
insert into Members values('5','883','Mayfield Street','5','883','Mayfield Street','5','5','123');
insert into Members values('6','4','53rd Ave.','6','4','53rd Ave.','6','6','123');
insert into Members values('7','2','Durham St.','7','2','Durham St.','7','7','123');
insert into Members values('8','7664','North Goldfield St.','8','7664','North Goldfield St.','8','8','123');
insert into Members values('9','7764','Wild Rose Dr.','9','7764','Wild Rose Dr.','9','9','123');
insert into Members values('10','8944','New Street','10','8944','New Street','10','10','123');

insert into Publisher values('1','431','College Ave.','11','1','11');
insert into Publisher values('2','9718','Tunnel Lane','12','2','12');
insert into Publisher values('3','7360','Taylor Ave.','13','3','13');
insert into Publisher values('4','243','Smoky Hollow Lane','14','4','14');
insert into Publisher values('5','69','West Goldfield St.','15','5','15');
insert into Publisher values('6','7731','Birchwood Street','16','6','16');
insert into Publisher values('7','196','Beaver Ridge Road','17','7','17');
insert into Publisher values('8','8977','West Golden Star St.','18','8','18');
insert into Publisher values('9','6','Hillcrest St.','11','9','19');
insert into Publisher values('10','2','Jefferson Lane','11','10','20');


insert into Book values('1234567890123','Meaning of Life','Henry','Hawking','Inspirational','764','55.39','5','1000','60','1');
insert into Book values('6956984376869','The Gun in the Mist','Kyle','Remy','Sci-fi','244','14.76','5','300','50','2');
insert into Book values('2662771252537','Son of Fear','Quintin','Jeff','Sci-fi','534','22.99','3','200','44','3');
insert into Book values('9500682165609','Cheat the Specter','Terry','Klaus','Inspirational','439','35.11','4','129','45','4');
insert into Book values('2898883359023','Considered for Error','Celia','Gamble','Drama','345','234.51','7','1000','20','1');
insert into Book values('8487572814676','The Murder in the Picture','Cherish','Taylor','Education','346','232.41','5','1000','30','5');
insert into Book values('4994936133118','Empty Vice','Gia','Jimenez','Horror','464','35.12','12','1000','40','10');
insert into Book values('0547863219693','Marked for Ruin','Saira','Higgs','Mystery','245','54.12','3','1400','44','10');
insert into Book values('1988319335963','The Killer in the Lake','Jean-Luc','Mckinney','Mystery','367','25.12','4','1700','33','8');
insert into Book values('8338099001626','Marked for Death','Murat','Oneal','Horror','278','234.12','12','1000','44','10');
insert into Book values('5294311815583','Phantom of Steel','Mandy','Nolan','Drama','672','56.43','2','1700','23');
insert into Book values('5000682580268','Ice and the Price','Elle','William','Education','724','64.34','4','1000','25','9');
insert into Book values('6492085756914','Maybe, Probably','Mark','Porter','Horror','523','63.32','5','1000','36','1');
insert into Book values('5969876488611','The Shadow in the Night','Mandy','Nolan','Mystery','523','34.12','2','1000','37','2');
insert into Book values('6828800827792','The Saga of the Nameless','Elle','William','Drama','123','23.23','12','1400','48','6');
insert into Book values('0676493562091','Snows of Solaris','Mark','Porter','Education','345','124.31','15','1060','88','4');
insert into Book values('6555585961244','Sound of Blood','Mark','Porter','Drama','234','45.41','21','1300','56','7');
insert into Book values('1352161069291','The Tomb in the Abyss','Mandy','Nolan','Education','624','53.76','4','1000','79','8');
insert into Book values('9312636030054','Sign of the Invisible Fingernail','Callan','Krueger','Mystery','262','66.34','15','1000','67','6');
insert into Book values('8361503584258','Distant Skull','Rubi','Klein','Horror','672','85.56','18','1020','95','5');
insert into Book values('9732685864022','Bionic Gun','Mandy','Nolan','Drama','341','83.64','3','1050','74','7');
insert into Book values('7780925270042','The Demon in the City','River','Donovan','Mystery','364','74.44','4','1200','54','8');
insert into Book values('6154747537477','The Translucent Locket','River','Donovan','Drama','352','75.44','5','300','32','9');
insert into Book values('0633046447560','Death of the Singing Beast','Mandy','Nolan','Education','624','65.34','6','2100','52','4');
insert into Book values('8017476855025','Death of the Blue Boa','River','Donovan','Drama','123','23.43','7','1000','35','6');
insert into Book values('0557232124703','Mystery of the Beheaded Baker','River','Donovan','Mystery','624','76.45','8','3000','62','5');
insert into Book values('1701471594390','The Freaky Violin','Cherish','Taylor','Education','335','65.50','9','2000','43','3');

insert into Orders values('1','Shipping','2021-1-05','88','Fordham Ave.','88','Fordham Ave.','1','1','1');
insert into Orders values('2','Delivered','2021-11-21','7605','Saxon Rd','7605','Saxon Rd','2','2','2');
insert into Orders values('3','Packing','2021-4-23','886','Cross Lane','886','Cross Lane','3','3','3');
insert into Orders values('4','Shipping','2021-3-26','8549','Center Dr.','8549','Center Dr.','4','4','4');
insert into Orders values('5','Shipping','2021-5-12','92','La Sierra Road','92','La Sierra Road','2','2','2');

insert into placeOrder values('1','1','1234567890123');
insert into placeOrder values('2','1','1701471594390');
insert into placeOrder values('3','1','0557232124703');
insert into placeOrder values('4','2','9732685864022');
insert into placeOrder values('5','3','6492085756914');
insert into placeOrder values('6','4','1352161069291');
insert into placeOrder values('7','4','1352161069291');
insert into placeOrder values('8','5','9500682165609');
insert into placeOrder values('9','5','6555585961244');
insert into placeOrder values('10','5','6492085756914');
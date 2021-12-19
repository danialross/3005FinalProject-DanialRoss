# 3005FinalProject-DanialRoss

extract zip and run main.java in a compiler.
import postgresql-42.2.24.jar into the modules for the ide so that it can connect to the database.
for intellij right click the folder of the program and go to "open moudle settings".
go to the modules tab and "add" the jar file in the ide.

in pgadmin, first start by creating a database called finalproject.
open DDL_project.sql in the sql folder to add the schemas into the database.
next, run insertData.sql to populate the database and run function.sql to set what the trigger does.
finally run Trigger.sql to add the trigger

now in the java program change the "user" string on top to the username of your database.
also change the "pwd" string to match the password of your program.

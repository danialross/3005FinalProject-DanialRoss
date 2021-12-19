import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main{
    static final String database = "jdbc:postgresql://localhost:5432/finalproject";
    static final String user = "postgres";
    static final String pwd = "danialross";
    static final String adminPassword = "password";
    public static void main(String[] args) {

        // based on the info from insertData.sql
        int currBankAccId = 10;
        int currCityStatesId = 20;
        int currContactInfoId = 20;
        int currMembersId = 10;
        int currPublisherId = 10;
        int currOrderId = 5;
        int currPlaceOrderId = 10;
        boolean mainLoop = true;
        while (mainLoop) {
            System.out.println("Select Option(1,2,3) [0 to exit]");
            System.out.println("1.Admin login");
            System.out.println("2.Customer login");
            System.out.println("3.Register a Customer");
            Scanner scanner = new Scanner(System.in);
            int input = -1;
            boolean doLoop = true;
            //loops to check valid option
            while(doLoop){
                if(scanner.hasNextInt()){
                    input = scanner.nextInt();                                           //ask user for which page
                }
                if(input<=3 & input>-1){
                    doLoop = false;

                }else{
                    System.out.println("Please input a 0,1,2 or 3");
                    scanner = new Scanner(System.in);
                }

            }

            if (input == 1) {

                // prompt password for admin clients
                System.out.println("Please enter the admin password (type 'quit' to exit)");
                String password="";
                boolean repeatPrompt = true;

                //loops until the right password is entered / quit to exit
                while (repeatPrompt) {
                    if(input==1){                                                                                          // if input is 1 that means it just entered else it is return from the loop
                        password = scanner.next();
                    }
                    if (password.compareTo(adminPassword) == 0) {
                        if(input==1){
                            System.out.println("Login Successful");
                        }

                        System.out.println("Admin Options:( 0 to exit)");
                        System.out.println("1.Sales vs Expenditures");
                        System.out.println("2.Sales Per Genre");
                        System.out.println("3.Sales Per Author");
                        System.out.println("4.Best Selling Books:");
                        System.out.println("5.Add a book");
                        System.out.println("6.Remove a book");

                        doLoop = true;
                        //valid selection
                        while(doLoop){
                            if(scanner.hasNextInt()){
                                input = scanner.nextInt();
                            }
                            if(input<=6 & input>-1){
                                doLoop = false;

                            }else{
                                System.out.println("Please input a 0,1,2,3 or 4 ( 0 to exit)");
                                scanner = new Scanner(System.in);
                            }
                        }

                        if (input == 1) {
                            // minus the royalty amount sent to the publishers
                            String query = "select SUM(price*quantity_available) as expenditures, SUM((price*(1-(royalty_percentage/100)))*quantity_sold) as Sales from book";
                            int sales = 0;
                            int expenditures = 0;
                            try (Connection conn = DriverManager.getConnection(database, user, pwd);                     //tries to connect to db
                                 Statement stmt1 = conn.createStatement();
                                 ResultSet result1 = stmt1.executeQuery(query);) {                                       //search all sales
                                while (result1.next()) {
                                    sales = result1.getInt("Sales");
                                    expenditures = result1.getInt("expenditures");
                                }

                                stmt1.close();
                                result1.close();

                                System.out.print("Showing all sales made:\n");                                            //prints all sales
                                System.out.printf("%-11s Expenditures\n", "Sales");
                                System.out.printf("$%-10s $%d\n", sales, expenditures);
                                System.out.println("");
                                input = -1;                                                                             //reseting input so user does not have to retype password when loop prompts


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } else if (input == 2) {
                            // minus the royalty amount sent to the publishers
                            String query = "select genre, SUM((price*(1-(royalty_percentage/100)))*quantity_sold) as Sales from book group by genre";
                            ArrayList<String> genre = new ArrayList<>();
                            ArrayList<Integer> salesPerGenre = new ArrayList<>();
                            try (Connection conn = DriverManager.getConnection(database, user, pwd);                        //tries to connect to db
                                 Statement stmt1 = conn.createStatement();
                                 ResultSet result1 = stmt1.executeQuery(query);) {                                            //search all sales by genre
                                while (result1.next()) {
                                    genre.add(result1.getString("genre"));
                                    salesPerGenre.add(result1.getInt("sales"));
                                }

                                stmt1.close();
                                result1.close();

                                System.out.print("Showing all sales made per genre:\n");                                     //prints all sales by genre
                                System.out.printf("%-20s Sales\n", "Genre");
                                for (int i = 0; i < genre.size(); i++) {
                                    System.out.printf("%-20s $%d\n", genre.get(i), salesPerGenre.get(i));
                                }
                                System.out.println("");
                                input = -1;                                                                                 // reseting input so user does not have to retype password when loop prompts

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } else if (input == 3) {
                            // minus the royalty amount sent to the publishers
                            String query = "select author_first_name, author_last_name,SUM((price*(1-(royalty_percentage/100)))*quantity_sold) as Sales from book group by author_first_name, author_last_name";
                            ArrayList<String> firstName = new ArrayList<>();
                            ArrayList<String> lastName = new ArrayList<>();
                            ArrayList<Integer> salesPerAuthor = new ArrayList<>();
                            try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                       //tries to connect to db
                                 Statement stmt1 = conn.createStatement();
                                 ResultSet result1 = stmt1.executeQuery(query);) {                                                                       //search all sales sorted  by authors
                                while (result1.next()) {
                                    firstName.add(result1.getString("author_first_name"));
                                    lastName.add(result1.getString("author_last_name"));
                                    salesPerAuthor.add(result1.getInt("sales"));
                                }

                                stmt1.close();
                                result1.close();
                                System.out.print("Showing all sales made per Author:\n");                                                                 //prints all sales by authors
                                System.out.printf("%-25s %-25s Sales\n", "Author's First Name", "Author's Last Name");
                                for (int i = 0; i < salesPerAuthor.size(); i++) {
                                    System.out.printf("%-25s %-25s $%d\n", firstName.get(i), lastName.get(i), salesPerAuthor.get(i));
                                }
                                System.out.println("");
                                input = -1;                                                                                                                 // reseting input so user does not have to retype password when loop prompts

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } else if (input == 4) {
                            String query = "select title, count(*) from placeorder natural join book group by title order by count(*) Desc ";
                            ArrayList<String> title = new ArrayList<>();
                            ArrayList<Integer> counts = new ArrayList<>();
                            try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                    //tries to connect to db
                                 Statement stmt1 = conn.createStatement();
                                 ResultSet result1 = stmt1.executeQuery(query);) {                                                                   //collects data on most book sold from highest to lowest
                                while (result1.next()) {
                                    title.add(result1.getString("title"));
                                    counts.add(result1.getInt("count"));
                                }

                                stmt1.close();
                                result1.close();
                                System.out.print("Showing best selling books in descending order:\n");                                     //prints all books from highest to lowest
                                System.out.printf("Title %42s\n", "Quantity Sold");
                                for (int i = 0; i < title.size(); i++) {
                                    System.out.printf("%-40s %d\n", title.get(i), counts.get(i));
                                }
                                System.out.println("");
                                input = -1;                                                                                                 // reseting input so user does not have to retype password when loop prompts

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else if(input == 5){
                            //add book

                            scanner.nextLine();
                            System.out.println("Please insert the title of the book");
                            String title = scanner.nextLine();

                            System.out.println("Please insert the name of the author");
                            String authorFullName = scanner.nextLine();

                            //seperate first and last name of author

                            String[] authorNames = authorFullName.split(" ",2);
                            String authorFirstName = authorNames[0];
                            String authorLastName = "";

                            if(authorNames.length>1){
                                authorLastName = authorNames[1];
                            }else{
                                authorLastName = " ";
                            }

                            System.out.println("Please insert the genre of the book");
                            String genre = scanner.nextLine();
                            genre = genre.substring(0,1).toUpperCase() + genre.substring(1);
                            System.out.println("Please insert the page number of the book");
                            String pageNumber = scanner.nextLine();
                            System.out.println("Please insert the price of the book");
                            String price = scanner.nextLine();
                            System.out.println("Please insert the royalty percentage of the book");
                            String royalty = scanner.nextLine();
                            System.out.println("Please insert the number of books to added");
                            String quantityAvailable = scanner.nextLine();


                            System.out.println("Please insert the name of the publisher");
                            String publisherFullName = scanner.nextLine();

                            String[] publisherNames = publisherFullName.split(" ",2);
                            String publisherFirstName = publisherNames[0];

                            String publisherLastName = "";
                            if(publisherNames.length>1){
                                publisherLastName = publisherNames[1];
                            }else{
                                publisherLastName = " ";
                            }

                            System.out.println("Please insert the email of the publisher");
                            String publisherEmail = scanner.nextLine();
                            System.out.println("Please insert the phone number of the publisher");
                            String publisherPhoneNumber = scanner.nextLine();

                            System.out.println("Please input your house number of the publisher");
                            String houseNumber = scanner.nextLine();
                            System.out.println("Please input your street name of the publisher");
                            String streetName = scanner.nextLine();
                            System.out.println("Please input your postal code of the publisher");
                            String postalCode = scanner.nextLine();
                            postalCode = postalCode.toUpperCase();
                            System.out.println("Please input your city of the publisher");
                            String city = scanner.nextLine();
                            System.out.println("Please input your state of the publisher");
                            String state = scanner.nextLine();
                            state = state.toUpperCase();

                            System.out.println("Please insert the bank account of the publisher");
                            String bankAcc = scanner.nextLine();

                            String searchPublisher = "select contact_id from contact_info where email='" +publisherEmail+ "'";
                            String publisherContact_id = "";
                            try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                    //tries to connect to db
                                 Statement stmt1 = conn.createStatement();
                                 ResultSet result1 = stmt1.executeQuery(searchPublisher);) {                                                                   //collects data on most book sold from highest to lowest
                                while (result1.next()) {
                                    publisherContact_id = result1.getString("contact_id");

                                }

                                stmt1.close();
                                result1.close();

                                String postalCodeId = "";
                                String searchPostalCode ="select postal_code_id from city_and_states where postal_code ='"+postalCode+"'";
                                Statement stmt2 = conn.createStatement();
                                ResultSet result2 = stmt2.executeQuery(searchPostalCode);
                                //searching if shipping postal code already in database
                                while (result2.next()) {
                                    postalCodeId = result2.getString("postal_code_id");
                                }

                                result2.close();
                                stmt2.close();

                                Statement stmt3 = conn.createStatement();

                                if(postalCodeId.isEmpty()){
                                    currCityStatesId +=1;
                                    String updateStates = "insert into City_and_states values('"+currCityStatesId+"','" + city + "','" + state + "','" + postalCode + "');";
                                    postalCodeId = Integer.toString(currCityStatesId);
                                    stmt3.executeUpdate(updateStates);
                                    stmt3.close();
                                }

                                if(publisherContact_id.isEmpty()){
                                    currBankAccId += 1;
                                    currContactInfoId +=1;
                                    currPublisherId +=1;
                                    String addPublisher = "insert into Bank_acc values('" + currBankAccId + "','" + bankAcc + "');insert into Contact_info values('"+currContactInfoId+"','"+publisherFirstName+"','"+publisherLastName+"','"+ publisherEmail +"','"+publisherPhoneNumber+"');insert into publisher values('"+currPublisherId+"','"+houseNumber+"','"+streetName+"','"+postalCodeId +"','"+currBankAccId+"','"+currContactInfoId+"');";
                                    publisherContact_id = Integer.toString(currPublisherId);
                                    Statement stmt4 = conn.createStatement();
                                    stmt4.executeUpdate(addPublisher);
                                    stmt4.close();
                                }

                                //check db to see if isbn is taken and generates isbn until an available one is made.
                                String isbn = "";
                                boolean isbnExist = true;
                                while(isbnExist){

                                    //generate isbn
                                    Random rdm = new Random();
                                    long checkIfPositive = rdm.nextLong();
                                    if(checkIfPositive < 0){
                                        checkIfPositive *=-1;
                                    }

                                    // getting only the first 13 numbers
                                    isbn = Long.toString(checkIfPositive);
                                    isbn = isbn.substring(0,13);

                                    String searchIsbn ="select isbn from book where isbn ='"+isbn+"'";
                                    Statement stmt5 = conn.createStatement();
                                    ResultSet result3 = stmt5.executeQuery(searchIsbn);
                                    String checkingIsbn = "";
                                    while (result3.next()) {
                                        checkingIsbn = result3.getString("postal_code_id");
                                    }

                                    stmt5.close();
                                    result3.close();

                                    if(checkingIsbn.isEmpty()){
                                       isbnExist = false;
                                    }

                                }
                                String addBook ="insert into book values('"+isbn+"','"+title+"','"+authorFirstName+"','"+ authorLastName +"','"+genre+"','"+pageNumber+"','"+price+"','"+ royalty +"','0','"+quantityAvailable+"','"+publisherContact_id+"')";
                                Statement stmt6 = conn.createStatement();
                                stmt6.executeUpdate(addBook);
                                System.out.println("Book Added");

                                System.out.printf("Title : %s\n",title);
                                System.out.printf("Author's Name : %s\n",authorFullName);
                                System.out.printf("Total Number of Pages : %s\n",pageNumber);
                                System.out.printf("Price of Book : %s\n",price);
                                System.out.printf("Royalty Percentage of the book : %s\n",royalty);
                                System.out.printf("Publisher's Name : %s\n",publisherFullName);
                                System.out.printf("Publisher's Email : %s\n",publisherEmail);
                                System.out.printf("Publisher's Phone Number : %s\n",publisherPhoneNumber);
                                System.out.printf("Publisher's House Number : %s\n",houseNumber);
                                System.out.printf("Publisher's Street Name : %s\n",streetName);
                                System.out.printf("Publisher's State : %s\n",state);
                                System.out.printf("Publisher's City : %s\n",city);
                                System.out.printf("Publisher's Postal Code : %s\n",postalCode);
                                System.out.printf("Publisher's Bank Account Number : %s\n",bankAcc);


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } else if(input == 6){
                            //delete a book
                            String showAllBooks = "select isbn,title from book";
                            ArrayList<String> bookISBNs = new ArrayList<>();
                            ArrayList<String> bookTitles = new ArrayList<>();
                            try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                       //tries to connect to db
                                 Statement stmt1 = conn.createStatement();
                                 ResultSet result1 = stmt1.executeQuery(showAllBooks);) {                                                                //searching all books in db
                                while (result1.next()) {
                                    bookISBNs.add(result1.getString("isbn"));
                                    bookTitles.add(result1.getString("title"));
                                }

                                System.out.printf("%-20s Title\n","ISBN");
                                for(int i = 0;i<bookISBNs.size();i++){
                                    System.out.printf("%-20s %s\n",bookISBNs.get(i),bookTitles.get(i));
                                }

                                //loop to keep asking user until a valid isbn is entered
                                boolean invalidInput = true;
                                while(invalidInput) {
                                    System.out.println("Insert an ISBN of a book to remove.");
                                    scanner.nextLine();
                                    String removeBook = scanner.nextLine();

                                    String checkBook = "select isbn from book where isbn='" + removeBook + "'";
                                    Statement stmt2 = conn.createStatement();
                                    ResultSet result2 = stmt2.executeQuery(checkBook);
                                    String isValid = "";
                                    while (result2.next()) {
                                        isValid = result2.getString("isbn");
                                    }

                                    if(!isValid.isEmpty()){
                                        invalidInput = false;
                                        stmt2.close();
                                        result2.close();
                                    }

                                    //get publisher id to for later checking
                                    String checkPublisherId = "select publisher_id from book where isbn='" + removeBook + "'";
                                    Statement stmt3 = conn.createStatement();
                                    ResultSet result3 = stmt3.executeQuery(checkPublisherId);
                                    String publisherId = "";
                                    while (result3.next()) {
                                        publisherId = result3.getString("publisher_id");
                                    }

                                    //for a realistic explanation, assume that if the owner deletes the book, all placed orders will be cancelled.
                                    String deleteOrders = "delete from placeorder where isbn='" + removeBook + "'";
                                    Statement stmt4 = conn.createStatement();
                                    stmt4.executeUpdate(deleteOrders);

                                    String deleteBook = "delete from book where isbn='" + removeBook + "'";
                                    Statement stmt5 = conn.createStatement();
                                    stmt5.executeUpdate(deleteBook);

                                    //delete publisher if no other books are published by them.
                                    String checkBookForPublisher = "select isbn from book where publisher_id='" + publisherId + "'";
                                    ArrayList<String> BooksWithPublisher = new ArrayList<>();
                                    Statement stmt6 = conn.createStatement();
                                    ResultSet result4 = stmt6.executeQuery(checkBookForPublisher);
                                    while (result4.next()) {
                                        BooksWithPublisher.add(result4.getString("isbn"));
                                    }

                                    if(BooksWithPublisher.isEmpty()){

                                        String findContactId = "select contact_id,postal_code_id,bank_id from publisher where publisher_id='" + publisherId + "'";
                                        String contactId = "";
                                        String postalCodeId = "";
                                        String bankId = "";
                                        Statement stmt7 = conn.createStatement();
                                        ResultSet result5 = stmt7.executeQuery(findContactId);
                                        while (result5.next()) {
                                            contactId = result5.getString("contact_id");
                                            postalCodeId = result5.getString("postal_code_id");
                                            bankId = result5.getString("bank_id");
                                        }

                                        //deleting publisher
                                        String deletePublisher = "delete from publisher where publisher_id='" + publisherId + "'";
                                        Statement stmt8 = conn.createStatement();
                                        stmt8.executeUpdate(deletePublisher);
                                        currPublisherId -=1;

                                        //deleting contact info of publisher
                                        String deleteContactInfo = "delete from contact_info where contact_id='" + contactId + "'";
                                        Statement stmt9 = conn.createStatement();
                                        stmt9.executeUpdate(deleteContactInfo);
                                        currContactInfoId -=1;

                                        //deleteing bank account info of publisher
                                        String deleteBankAcc = "delete from bank_acc where bank_id='" + bankId + "'";
                                        Statement stmt10 = conn.createStatement();
                                        stmt10.executeUpdate(deleteBankAcc);
                                        currBankAccId -=1;

                                        //checking is other member or publishers use the same postal code as them
                                        String findPublisherWithPostal = "select publisher_id from publisher where postal_code_id='" + postalCodeId + "'";
                                        ArrayList<String> publishersWithPostalCode = new ArrayList<>();

                                        Statement stmt11 = conn.createStatement();
                                        ResultSet result6 = stmt11.executeQuery(findPublisherWithPostal);
                                        while (result6.next()) {
                                            publishersWithPostalCode.add(result6.getString("publisher_id"));

                                        }

                                        String findMemberWithPostal = "select member_id from members where postal_code_id='" + postalCodeId + "'";
                                        ArrayList<String> membersWithPostalCode = new ArrayList<>();

                                        Statement stmt12 = conn.createStatement();
                                        ResultSet result7 = stmt12.executeQuery(findMemberWithPostal);
                                        while (result7.next()) {
                                            membersWithPostalCode.add(result7.getString("member_id"));

                                        }

                                        //delete is they are the only ones with that postal code
                                        if(membersWithPostalCode.isEmpty() && publishersWithPostalCode.isEmpty()){
                                            String deletePostalCode = "delete from city_and_states where postal_code_id='" + postalCodeId + "'";
                                            Statement stmt13 = conn.createStatement();
                                            stmt13.executeUpdate(deletePostalCode);
                                            currCityStatesId -=1;
                                        }

                                    }
                                    System.out.println("Book Successfully Removed\n");

                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } else if(input == 0){
                            // exit admin
                            repeatPrompt = false;
                        }
                    } else if (password.compareTo("quit") == 0) {
                        System.out.println("Exiting program");
                        repeatPrompt = false;
                    } else if (!password.isEmpty()) {                              //if anything other than the correct pass is entered
                        System.out.println("Incorrect password,Try again");
                    }

                }


            } else if (input == 2) {

                //search for book
                System.out.println("Login to membership account");
                scanner.nextLine();
                String member_id = "";
                boolean invalidLogin = true;
                while(invalidLogin) {
                    System.out.println("Enter your email");
                    String email = scanner.nextLine();
                    System.out.println("Enter your password");
                    String passwd = scanner.nextLine();

                    String checkMember = "select email,contact_id from contact_info where email='"+email+"'";
                    String emailExist = "";
                    String contact_id = "";

                    try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                       //tries to connect to db
                         Statement stmt1 = conn.createStatement();
                         ResultSet result1 = stmt1.executeQuery(checkMember);) {                                                                //searching if shipping postal code already in database
                        while (result1.next()) {
                            emailExist = result1.getString("email");
                            contact_id = result1.getString("contact_id");
                        }

                        stmt1.close();
                        result1.close();

                        String checkPass = "select member_id,password from members where contact_id='"+contact_id+"'";
                        String pwdExist = "";
                        member_id = "";
                        Statement stmt2 = conn.createStatement();
                        ResultSet result2 = stmt2.executeQuery(checkPass);
                        while (result2.next()) {
                            pwdExist = result2.getString("password");
                            member_id = result2.getString("member_id");
                        }

                        stmt2.close();
                        result2.close();

                        //checking is email and password is valid
                        if (!emailExist.isEmpty() && pwdExist.compareTo(passwd)==0) {
                            invalidLogin = false;
                            System.out.printf("Logged in using %s\n",email);
                        }else{
                            System.out.printf("Invalid login, try \n");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                //selecting books
                String searchMethod ="";
                boolean repeatPrompt = true;
                while(repeatPrompt) {
                    System.out.println("Select method to search ( 0 to exit to main page )");
                    System.out.println("1.Search a book by Title:");
                    System.out.println("2.Search a book by Author:");
                    System.out.println("3.Search a book by ISBN:");
                    System.out.println("4.Search a book by Number of Pages:(all books below selected amount)");
                    System.out.println("5.Search a book by Price:(all books below selected amount)");
                    System.out.println("6.Search a status of an order");

                    //loops to check valid option
                    boolean invalidOption = true;
                    while (invalidOption) {
                        if (scanner.hasNextInt()) {
                            input = scanner.nextInt();                                           //ask user for which page
                        }
                        if (input <= 6 & input > -1) {
                            invalidOption = false;

                        } else {
                            System.out.println("Please input a 0,1,2,3,4 or 5");
                            scanner = new Scanner(System.in);
                        }

                    }

                    if (input == 1) {

                        //searching for all books that contain the word inputted.
                        System.out.println("Insert the title to search for.");
                        scanner.nextLine();
                        String searchByTitle = scanner.nextLine();
                        searchMethod = "select isbn,title,author_first_name,author_last_name,genre,page_number,price from book where title like '%"+searchByTitle+"%'";


                    } else if (input == 2) {

                        //search books by author name
                        scanner.nextLine();
                        System.out.println("Insert the author name to search for.");
                        String searchByAuthor = scanner.nextLine();
                        searchMethod = "select isbn,title,author_first_name,author_last_name,genre,page_number,price from book where author_first_name like '%"+searchByAuthor+"%' or author_last_name like '%"+searchByAuthor+"%'";


                    } else if (input == 3) {

                        //search all books that contain the sequence inputted by user
                        scanner.nextLine();
                        System.out.println("Insert the ISBN to search for.");
                        String searchByISBN = scanner.nextLine();
                        searchMethod = "select isbn,title,author_first_name,author_last_name,genre,page_number,price from book where isbn like '%"+searchByISBN +"%'";


                    } else if (input == 4) {

                        invalidOption = true;
                        int pageNum = -1;
                        while (invalidOption) {
                            System.out.println("Insert Number of Pages");
                            if (scanner.hasNextInt()) {
                                pageNum = scanner.nextInt();                                           //ask user for which page
                                invalidOption = false;
                            } else {
                                System.out.println("Please input a number");
                                scanner = new Scanner(System.in);
                            }

                        }

                        searchMethod = "select isbn,title,author_first_name,author_last_name,genre,page_number,price  from book where page_number between 0 and " + pageNum;
                        scanner.nextLine();


                    } else if (input == 5) {
                        invalidOption = true;
                        int selectedPrice = -1;
                        while (invalidOption) {

                            System.out.println("Insert price limit");
                            if (scanner.hasNextInt()) {
                                selectedPrice = scanner.nextInt();                                           //ask user for which page
                                invalidOption = false;
                            } else {
                                System.out.println("Please input a number");
                                scanner = new Scanner(System.in);
                            }

                        }

                        searchMethod = "select isbn,title,author_first_name,author_last_name,genre,page_number,price  from book where price between 0 and " + selectedPrice;
                        scanner.nextLine();

                    } else if (input == 6) {
                        scanner.nextLine();
                        System.out.println("Insert the order number");
                        String orderNum = scanner.nextLine();

                        String searchOrderStatus = "select status,order_id from orders where order_id ='" + orderNum + "'";
                        String status = "";
                        String order_id = "";

                        try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                       //tries to connect to db
                             Statement stmt1 = conn.createStatement();
                             ResultSet result1 = stmt1.executeQuery(searchOrderStatus);) {                                                                //searching if shipping postal code already in database
                            while (result1.next()) {
                                status = result1.getString("status");
                                order_id = result1.getString("order_id");

                            }

                            stmt1.close();
                            result1.close();

                            if(order_id.isEmpty()){
                                System.out.println("The order id does not exist");
                            }else{
                                System.out.printf("%-10s %s\n","Order Id","Status");
                                System.out.printf("%-10s %s\n",order_id,status);
                            }


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;

                    }else if (input == 0) {
                        System.out.println("Exiting User Page\n");
                        break;

                    }


                    ArrayList<String> isbn = new ArrayList<>();
                    ArrayList<String> title = new ArrayList<>();
                    ArrayList<String> author_first_name = new ArrayList<>();
                    ArrayList<String> author_last_name = new ArrayList<>();
                    ArrayList<String> genre = new ArrayList<>();
                    ArrayList<String> page_number = new ArrayList<>();
                    ArrayList<String> price = new ArrayList<>();

                    try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                       //tries to connect to db
                         Statement stmt1 = conn.createStatement();
                         ResultSet result1 = stmt1.executeQuery(searchMethod);) {                                                                //searching if shipping postal code already in database
                        while (result1.next()) {
                            isbn.add(result1.getString("isbn"));
                            title.add(result1.getString("title"));
                            author_first_name.add(result1.getString("author_first_name"));
                            author_last_name.add(result1.getString("author_last_name"));
                            genre.add(result1.getString("genre"));
                            page_number.add(result1.getString("page_number"));
                            price.add(result1.getString("price"));
                        }

                        stmt1.close();
                        result1.close();

                        System.out.printf("%-20s %-35s %-20s %-20s %-20s %-20s %-20s\n","ISBN","Title","Author's First Name","Author's Last Name","Genre","Page Number","Price");
                        for (int i = 0; i < isbn.size(); i++) {
                            System.out.printf("%-20s %-35s %-20s %-20s %-20s %-20s $%-20s\n", isbn.get(i), title.get(i), author_first_name.get(i), author_last_name.get(i), genre.get(i), page_number.get(i), price.get(i));
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    System.out.println(" ");
                    System.out.println("Select all books to buy by isbn (isbn,isbn,isbn.....)");
                    String booksToBuy = scanner.nextLine();
                    String[] checkoutCart = booksToBuy.split(",");                                                                         //create array to store check out items

                    System.out.println("Do you want to use the address registered to this account?(y/n)");
                    String sameAddress = scanner.nextLine();

                    while (sameAddress.compareTo("y") != 0 && sameAddress.compareTo("n") != 0) {
                        System.out.println("Please select either y or n ");
                        sameAddress = scanner.nextLine();
                    }

                    String SHouseNumber = "";
                    String SStreetName = "";
                    String SPostalCode = "";
                    String SCity = "";
                    String SState = "";
                    String BHouseNumber = "";
                    String BStreetName = "";
                    String BPostalCode = "";
                    String BCity = "";
                    String BState = "";

                    String SPostalCodeId = "";
                    String BPostalCodeId = "";

                    if (sameAddress.compareTo("y") == 0) {

                        //gettin info of member from database
                        String getInfo = "select shipping_house_number, shipping_Street_name,shipping_postal_code_id,billing_house_number,billing_Street_name,billing_postal_code_id from members where member_id ='" + member_id + "'";
                        try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                       //tries to connect to db
                             Statement stmt1 = conn.createStatement();
                             ResultSet result1 = stmt1.executeQuery(getInfo);) {                                                                //searching if shipping postal code already in database
                            while (result1.next()) {
                                SHouseNumber = result1.getString("shipping_house_number");
                                SStreetName = result1.getString("shipping_Street_name");
                                SPostalCodeId = result1.getString("shipping_postal_code_id");
                                BHouseNumber = result1.getString("billing_house_number");
                                BStreetName = result1.getString("billing_Street_name");
                                BPostalCodeId = result1.getString("billing_postal_code_id");

                            }
                            stmt1.close();
                            result1.close();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else {
                        System.out.println("Please input your shipping house number");
                        SHouseNumber = scanner.nextLine();
                        System.out.println("Please input your shipping street name");
                        SStreetName = scanner.nextLine();
                        System.out.println("Please input your shipping postal code");
                        SPostalCode = scanner.nextLine();
                        SPostalCode = SPostalCode.toUpperCase();
                        System.out.println("Please input your shipping city");
                        SCity = scanner.nextLine();
                        SCity = SCity.substring(0,1).toUpperCase() + SCity.substring(1);
                        System.out.println("Please input your shipping state");
                        SState = scanner.nextLine();
                        SState.toUpperCase();

                        System.out.println("Please input your billing house number");
                        BHouseNumber = scanner.nextLine();
                        System.out.println("Please input your billing street name");
                        BStreetName = scanner.nextLine();
                        System.out.println("Please input your billing postal code");
                        BPostalCode = scanner.nextLine();
                        BPostalCode = BPostalCode.toUpperCase();
                        System.out.println("Please input your billing city");
                        BCity = scanner.nextLine();
                        System.out.println("Please input your billing state");
                        BState = scanner.nextLine();
                        BState.toUpperCase();

                        //is city_and_state DNE, add
                        String checkShipping = "select postal_code_id from city_and_states where postal_code = '" + SPostalCode + "'";
                        String checkBilling = "select postal_code_id from city_and_states where postal_code = '" + BPostalCode + "'";
                        String shippingExist = "";
                        String billingExist = "";
                        try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                       //tries to connect to db
                             Statement stmt1 = conn.createStatement();
                             ResultSet result1 = stmt1.executeQuery(checkShipping);) {                                                                //searching if shipping postal code already in database
                            while (result1.next()) {
                                shippingExist = result1.getString("postal_code_id");
                            }
                            stmt1.close();
                            result1.close();

                            Statement stmt2 = conn.createStatement();
                            ResultSet result2 = stmt2.executeQuery(checkBilling);                                                               //searching if shipping postal code already in database
                            while (result2.next()) {
                                billingExist = result2.getString("postal_code_id");
                            }

                            stmt1.close();
                            result1.close();

                            if (shippingExist.isEmpty()) {
                                currCityStatesId += 1;
                                SPostalCodeId = Integer.toString(currCityStatesId);
                                String insertShipping = "insert into city_and_states values('" + currCityStatesId + "','" + SCity + "','" + SState + "','" + SPostalCode + "');";
                                Statement stmt3 = conn.createStatement();
                                stmt3.executeUpdate(insertShipping);
                                stmt3.close();
                            }else{
                                SPostalCodeId = shippingExist;
                            }
                            if (billingExist.isEmpty()) {
                                currCityStatesId += 1;
                                BPostalCodeId = Integer.toString(currCityStatesId);
                                String insertBilling = "insert into city_and_states values('" + currCityStatesId + "','" + BCity + "','" + BState + "','" + BPostalCode + "');";
                                Statement stmt3 = conn.createStatement();
                                stmt3.executeUpdate(insertBilling);
                            }else{
                                BPostalCodeId = billingExist;
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    }

                     try (Connection conn = DriverManager.getConnection(database, user, pwd);) {                                                     //tries to connect to db
                        currOrderId += 1;
                         LocalDate date = LocalDate.now();
                        String addOrder = "insert into orders values('" + currOrderId + "','Shipping','"+date+"','" + SHouseNumber + "','" + SStreetName + "','"+ BHouseNumber + "','" + BStreetName + "','" + member_id + "','" + SPostalCodeId + "','" + BPostalCodeId + "');";
                        Statement stmt1 = conn.createStatement();
                        stmt1.executeUpdate(addOrder);
                        stmt1.close();

                        for (int i = 0; i < checkoutCart.length; i++) {
                            currPlaceOrderId += 1;
                            String addBooks = "insert into placeorder values('" + currPlaceOrderId + "','" + (currOrderId - 1) + "','" + checkoutCart[i] + "')";
                            Statement stmt2 = conn.createStatement();
                            stmt2.executeUpdate(addBooks);
                            stmt2.close();
                        }

                        for(int i = 0;i<checkoutCart.length;i++) {
                            System.out.printf("Order placed for %s.\n\n", checkoutCart[i]);
                        }


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            } else if(input == 3){
                //register
               String fullname = "";
                String email = "";
                String password = "";
                String phoneNumber = "";
                String SHouseNumber = "";
                String SStreetName = "";
                String SPostalCode = "";
                String SCity = "";
                String SState = "";
                String BHouseNumber = "";
                String BStreetName = "";
                String BPostalCode = "";
                String BCity = "";
                String BState = "";
                boolean sameAddr = false;
                boolean correct = false;
                while(!correct) {
                    scanner.nextLine();
                    System.out.println("Please input your full name");
                    fullname = scanner.nextLine();
                    System.out.println("Please input your email");
                    email = scanner.nextLine();
                    System.out.println("Please input your password");
                    password = scanner.nextLine();
                    System.out.println("Please input your phone number");
                    phoneNumber = scanner.nextLine();
                    System.out.println("Please input your shipping house number");
                    SHouseNumber = scanner.nextLine();
                    System.out.println("Please input your shipping street name");
                    SStreetName = scanner.nextLine();
                    System.out.println("Please input your shipping postal code");
                    SPostalCode = scanner.nextLine();
                    SPostalCode = SPostalCode.toUpperCase();
                    System.out.println("Please input your shipping city");
                    SCity = scanner.nextLine();
                    System.out.println("Please input your shipping state");
                    SState = scanner.nextLine();
                    SState = SState.toUpperCase();
                    System.out.println("Would you like to use the same data for your billing information?(y/n)");
                    String choice = scanner.nextLine();

                    while(choice.compareTo("y") != 0 && choice.compareTo("n") != 0){
                        System.out.println("Please select either y or n ");
                        choice = scanner.nextLine();
                    }
                    if(choice.compareTo("y") == 0 ){
                        BHouseNumber = SHouseNumber;
                        BStreetName = SStreetName;
                        BPostalCode = SPostalCode;
                        BCity = SCity;
                        BState = SState;
                        sameAddr = true;

                    }else if(choice.compareTo("n") == 0 ) {
                        System.out.println("Please input your billing house number");
                        BHouseNumber = scanner.nextLine();
                        System.out.println("Please input your billing street name");
                        BStreetName = scanner.nextLine();
                        System.out.println("Please input your billing postal code");
                        BPostalCode = scanner.nextLine();
                        BPostalCode = BPostalCode.toUpperCase();
                        System.out.println("Please input your billing city");
                        BCity = scanner.nextLine();
                        System.out.println("Please input your billing state");
                        BState = scanner.nextLine();
                        BState.toUpperCase();
                    }
                    System.out.println(" ");
                    System.out.println("Is your information correct?(y/n)\n");
                    System.out.printf("fullname : %s\n",fullname);
                    System.out.printf("email : %s\n",email);
                    System.out.printf("phone number : %s\n",phoneNumber);
                    System.out.printf("shipping house number : %s\n",SHouseNumber);
                    System.out.printf("shipping street name : %s\n",SStreetName);
                    System.out.printf("shipping postal code : %s\n",SPostalCode);
                    System.out.printf("shipping city : %s\n",SCity);
                    System.out.printf("shipping state : %s\n",SState);
                    System.out.printf("billing house number : %s\n",BHouseNumber);
                    System.out.printf("billing street name : %s\n",BStreetName);
                    System.out.printf("billing postal code : %s\n",BPostalCode);
                    System.out.printf("billing city : %s\n",BCity);
                    System.out.printf("billing state : %s\n",BState);

                    choice = scanner.nextLine();

                    if(choice.compareTo("y") == 0){
                        correct = true;
                    }
                }

                //splitting full name to first and last
                String[] names;
                names = fullname.split(" ",2);
                String firstName = names[0];
                String lastName;
                if(names.length>1){
                    lastName = names[1];
                }else{
                    lastName = "";
                }

                String SPostalCode_id = "";
                String BPostalCode_id = "";
                String searchShipping = "select postal_code_id from city_and_states where postal_code='" + SPostalCode + "'";

                if(!sameAddr){
                    // check then insert for 2 city_and_state
                    try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                       //tries to connect to db
                         Statement stmt1 = conn.createStatement();
                         Statement stmt2 = conn.createStatement();
                         Statement stmt3 = conn.createStatement();
                         ResultSet result1 = stmt1.executeQuery(searchShipping);) {                                                                //searching if shipping postal code already in database
                         while (result1.next()) {
                            SPostalCode_id =result1.getString("postal_code_id");
                         }

                        stmt1.close();
                        result1.close();
                        String searchBilling = "select postal_code_id from city_and_states where postal_code='" + BPostalCode + "'";

                        ResultSet result2 = stmt2.executeQuery(searchBilling);{                                                                     //searching if billing postal code already in database
                            while (result2.next()) {
                                BPostalCode_id =result2.getString("postal_code_id");
                            }
                        }
                        stmt2.close();
                        result2.close();

                        //increase the id count for all ids used in member
                        currContactInfoId += 1;
                        currMembersId += 1;

                        //postal code not exist in db
                        if(SPostalCode_id.isEmpty() && BPostalCode_id.isEmpty()) {                                                                 // if ship and bill DNE, add both
                            currCityStatesId +=1;                                                                                                  //increment id before query so that the shipping will be 1+ the latest and billing will be 2+
                            SPostalCode_id = Integer.toString(currCityStatesId);                                                                   // using newly created id as shipping postal code id
                            BPostalCode_id = Integer.toString(currCityStatesId);                                                              // using newly created id as billing postal code id, +1 because 2 city_and_states rows are to be added
                            String addBoth = "insert into City_and_states values('"+currCityStatesId+"','" + SCity + "','" + SState + "','" + SPostalCode + "');insert into City_and_states values('"+(currCityStatesId+1)+"','" + BCity + "','" + BState + "','" + BPostalCode + "');insert into Contact_info values('"+currContactInfoId+"','"+firstName+"','"+lastName+"','"+ email +"','"+phoneNumber+"');insert into Members values('"+currMembersId+"','"+SHouseNumber+"','"+SStreetName+"','"+SPostalCode_id+"','"+BHouseNumber+"','"+BStreetName+"','"+BPostalCode_id+"','"+currContactInfoId+"','"+password+"');";
                            stmt3.executeUpdate(addBoth);
                            currCityStatesId +=1;                                                                                                  // increment after so that the program has the same number id as the db

                        }else if(BPostalCode_id.isEmpty()){                                                                                        // if bill DNE, add bill
                            currCityStatesId +=1;                                                                                                  // increment only 1 because only a new billing is added
                            BPostalCode_id = Integer.toString(currCityStatesId);                                                                   // using newly created id as shipping postal code id
                            String addBilling = "insert into City_and_states values('"+currCityStatesId+"','" + BCity + "','" + BState + "','" + BPostalCode + "');insert into Contact_info values('"+currContactInfoId+"','"+firstName+"','"+lastName+"','"+ email +"','"+phoneNumber+"');insert into Members values('"+currMembersId+"','"+SHouseNumber+"','"+SStreetName+"','"+SPostalCode_id+"','"+BHouseNumber+"','"+BStreetName+"','"+BPostalCode_id+"','"+currContactInfoId+"','"+password+"');";
                            stmt3 .executeUpdate(addBilling);

                        }else if(SPostalCode_id.isEmpty()){
                            currCityStatesId +=1;                                                                                                  // increment only 1 because only a new shipping is added
                            SPostalCode_id = Integer.toString(currCityStatesId);                                                                   // using newly created id as shipping postal code id
                            String addShipping = "insert into City_and_states values('"+currCityStatesId+"','" + SCity + "','" + SState + "','" + SPostalCode + "');insert into Contact_info values('"+currContactInfoId+"','"+firstName+"','"+lastName+"','"+ email +"','"+phoneNumber+"');insert into Members values('"+currMembersId+"','"+SHouseNumber+"','"+SStreetName+"','"+SPostalCode_id+"','"+BHouseNumber+"','"+BStreetName+"','"+BPostalCode_id+"','"+currContactInfoId+"','"+password+"');";
                            stmt3.executeUpdate(addShipping);                                                                                       // if shipping DNE, add ship

                        }else{
                            String addNeither = "insert into Contact_info values('"+currContactInfoId+"','"+firstName+"','"+lastName+"','"+ email +"','"+phoneNumber+"');insert into Members values('"+currMembersId+"','"+SHouseNumber+"','"+SStreetName+"','"+SPostalCode_id+"','"+BHouseNumber+"','"+BStreetName+"','"+BPostalCode_id+"','"+currContactInfoId+"','"+password+"');";
                            stmt3.executeUpdate(addNeither);                                                                                        // add neither but use their id with the rest of the data

                        }
                        System.out.println("Register Successful\n");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    //insert and check for only 1 city_and_state
                    try (Connection conn = DriverManager.getConnection(database, user, pwd);                                                       //tries to connect to db
                         Statement stmt1 = conn.createStatement();
                         Statement stmt2 = conn.createStatement();
                         ResultSet result1 = stmt1.executeQuery(searchShipping);) {                                                                //searching if shipping postal code already in database
                        while (result1.next()) {
                            SPostalCode_id = result1.getString("postal_code_id");
                            BPostalCode_id = result1.getString("postal_code_id");
                        }
                        stmt1.close();
                        result1.close();

                        //increase the id count for all ids used in member
                        currContactInfoId += 1;
                        currMembersId += 1;

                        //postal code not exist in db
                        if(SPostalCode_id.isEmpty()) {                                                                                             //searching if shipping postal code already in database add both, since ship and bill are the same
                            currCityStatesId +=1;                                                                                                  // increment id before query so that the shipping will be 1+ the latest and billing will be 2+
                            SPostalCode_id = Integer.toString(currCityStatesId);                                                                   // using newly created id as shipping postal code id
                            BPostalCode_id = Integer.toString(currCityStatesId);                                                              // using newly created id as billing postal code id, +1 because 2 city_and_states rows are to be added
                            String addOne = "insert into City_and_states values('"+currCityStatesId+"','" + SCity + "','" + SState + "','" + SPostalCode + "');insert into Contact_info values('"+currContactInfoId+"','"+firstName+"','"+lastName+"','"+ email +"','"+phoneNumber+"');insert into Members values('"+currMembersId+"','"+SHouseNumber+"','"+SStreetName+"','"+SPostalCode_id+"','"+BHouseNumber+"','"+BStreetName+"','"+BPostalCode_id+"','"+currContactInfoId+"','"+password+"');";
                            stmt2.executeUpdate(addOne);
                            currCityStatesId +=1;                                                                                                  // increment after so that the program has the same number id as the db

                        }else{
                            String addNeither = "insert into Contact_info values('"+currContactInfoId+"','"+firstName+"','"+lastName+"','"+ email +"','"+phoneNumber+"');insert into Members values('"+currMembersId+"','"+SHouseNumber+"','"+SStreetName+"','"+SPostalCode_id+"','"+BHouseNumber+"','"+BStreetName+"','"+BPostalCode_id+"','"+currContactInfoId+"','"+password+"');";
                            stmt2.executeQuery(addNeither);                                                                                       //add none since both are in db so just use their existing id with the new data

                        }
                        System.out.println("Register Successful\n");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                mainLoop=false;
            }
        }
    }
}

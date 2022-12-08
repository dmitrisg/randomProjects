**How to Save Data From Your Game to the SQL Server.**

**Prerequisites:**
  1. Be on a campus desktop (or connect to one remotely).

**1. Connect to the server in MySQLWorkbench.**

    a. Open MySqlWorkbench, using AJ Apps.  
    b. Click the little plus sign next to MySQL Connections. This opens the Setup New Connection window.  
    c. For Connection Name, enter whatever you want to call it.  
    d. For hostname, enter cscmysql.lemoyne.edu.  
    e. For username, enter your username from the 2022_MySQL_Instructions.pdf on Canvas. This should be your last name.  
    f. Click "Store In Vault..." next to Password. This opens the Store Password For Connection window. Enter your password. You can find the password next to your username on the pdf. This should be your first name.  
    g. Do not modify any other fields.   
    h. Click OK at the bottom.  
    i. Now you will see your connection under MySQL Connections. Double click it.
  
**2. Set up your database.**
I'm sure this can all be done through Java, telekinesis, x86 assembly, etc. but using MySQLWorkbench is much easier.

    a. After step 1i, you should see a workspace with panels including an Output bar on the bottom and a Navigator on the left side. In the center should be a space to enter text, labeled Query 1. If you do not see Query 1 or have turned it off by accident, click the symbol directly below File in the top left. This will open a new tab.
    b. Creating your database. Enter the code below. Note: do not run it yet.
  ```
  drop database if exists game276[username]; -- replace [username] with your username. This line deletes the DB by that name if one already exists.
  create database game276[username]; -- this creates a new DB.
  use game276[username]; -- makes sure the rest of commands will be executed in this DB.
  ```  
    c. Creating some tables. Enter this code and, afterwards, run the script. To do this, click the lightning bolt icon right above your texts. Note that if part of your script is selected, then only that part will execute.
 ```
 create table Game
    (id INTEGER AUTO_INCREMENT PRIMARY KEY,
    computerName VARCHAR(30),
    humanName VARCHAR(30)
    );
    
 create table Score
    (turnId INTEGER AUTO_INCREMENT,
    player CHAR(1), -- this field stores either 'c' or 'h' to identify whether it is the computer's or human's turn
    gameId INTEGER, -- this references the id field from the Game table
    turnScore INTEGER,
    totalScore INTEGER,
    PRIMARY KEY (turnId, player, gameId), -- this defines the primary key (it is made up of multiple fields)
    FOREIGN KEY (gameId)  -- this defines the foreign key 
      REFERENCES Game(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE);
 
``` If it worked, you will be able to see it in the output tab under your script editor. You should see all the commands you just ran with a green checkmark next to each. You may see the message "Error loading schema content". I'm not sure what it means, but it does not seem to be important. 
    d. Adding some test records. Erase all your previous code from the query. (You just ran it and you don't want to run the same thing twice.) Write:
    ```
    use game276[username];
    insert into Game (computerName, humanName) VALUES ('x', 'y'); -- this will create a record in the Game table. Note that the id is auto generated, so you do not need to specify what it is.
    insert into Score 
        SET player = 'c', 
        turnScore = 7,
        totalScore = 10,
        gameId = (SELECT MAX(id) FROM Game); -- this sets the gameId to the whatever the highest id in Game is.
  ```(These are more or less the same commands you will need to execute through your Java game later on.) Run the script.
    e. Retrieving your new records. If you successfully inserted the records, you should be able to fetch them. Erase your code again.
    ```
    use game276[username];
    select * from Game; -- the * means "all".
    select * from Score;
    ``` If all runs correctly, some tabs will pop up with results. One will contain the Game records, and the other will have the Score records.
    
**3. Set up the SQL driver.**
a. Download mysql-connector-java-8.0.21.jar from Canvas.
b. Connect the jar to your project.
i. Eclipse instructions
ii. JGrasp instructions
iii. Other IDEs
Idk look it up 

(Note: I am not sure if the location of the jar matters, but mine is in the project's src folder.


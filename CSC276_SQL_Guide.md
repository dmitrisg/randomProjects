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
  a. After step 1i, you should see a workspace with panels including an Output bar on the bottom and a Navigator on the left side. In the middle should be a place to enter text, labeled Query 1. If you do not see Query 1 or have turned it off by accident, click the symbol directly under File in the top left. This will open a new tab.
  b. Create your database. Enter this code: 
  drop database if exists game276[username]; -- replace [username] with your username. This line deletes the DB by that name if one already exists.
  create database game276[username]; -- this creates a new DB.
  use game276[username]; -- makes sure the rest of commands will be executed in this DB.
  
  Note: do not run the code yet.
  
  c. Create some tables. Enter this code:
  create table Game

  

**2. Set up the SQL driver. **
a. Download the jar file [name] from Canvas.
b. Connect the jar to your project.
i. Eclipse instructions
ii. JGrasp instructions
iii. Other IDEs
Idk look it up 

(Note: I am not sure if the location of the jar matters, but mine is in the project's src folder.


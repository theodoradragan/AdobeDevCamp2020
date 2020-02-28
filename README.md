# test_app
App to test if Maven, Git, IntelliJ and MySQL are properly configured

After making sure you properly installed all these 4 tools please clone this app and run it inside IDEA IntelliJ.

If the app runs successfully, Company objects are serialized and after that deserialized and the insert is successful, then you may consider the setup is over.

You only need to add the app from a local machine in a new repo on your personal Github profile to finish this verification.

Congrats!

Important:
- Install XAMPP or (MySQL server and  MySQLWorkbench) for example. Start MySQL server
- create a database called `test_db` and the `companies` table

CREATE DATABASE IF NOT EXISTS `test_db`;
USE `test_db`;

-- --------------------------------------------------------

CREATE TABLE `companies` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `number_of_employees` int(11) NOT NULL
);

- by default you connect to the db with the user root who has no password. If you changed anything please make sure you change in Main.java as well.

- run the app. If no error message occurs, please check data in the db.
README

DISCLAIMER:

With enough time during the summer I was able to add A LOT more features to the program and make it work pretty much seamlessly 
with the introduction of some concepts of maven dependencies, MySQL, and some CSS style sheets. Plus, I migrated away from having 
to use any text file parsing, hashmaps were upgraded to observable lists or databases, and I think I may have went a little ahead 
in trying to actually make a useable working app for me and my wife.

In this project I used the resources and tech stack listed below:
	1)	For the Front-End I used JavaFX (specifically the Scene Builder program) and Java CSS
	2)	For the Back-End I used Java
	3)	For the Database MySQL was used
	4)	Maven is the build tool used to compile the application

I organized this projects in packages to make it easier to work and simple for others to understand my code. There are 8 packages: 
	1)	controller (holds the controller classes for each view), 
	2)	css (holds the css files for the front – end design, button colors etc),
	3)	dao (holds the classes that connects the application to the database and performs CRUD (create, read, update, delete) operations), 
	4)	fxml (holds the fxml files for the views), 
	5)	images (holds all the images used for buttons etc), 
	6)	main (holds the main class that lunches the application), 
	7)	model (holds the classes that has the getters, setters and the constructors for the objects) 
	8)	utils (holds some Helper Methods used in the projects to keep the code organized and not repeat itself).

This project is based on MVC design pattern (model – view – controller), and separated accordingly.

I HOPE I DIDN’T RELY TOO MUCH ON THESE MAVEN DEPENDENCIES AND THE DATABASE UTILITIES TO SHOW HOW I WAS ABLE TO IMPLEMENT CONCEPTS FROM CLASS.

DISCLAIMER 2:

Some program to handle the MySQL code/database is needed.

DISCLAIMER 3: 

Throws exception, IOException, and SQL exception is used within the code to handle any IOException or SQLException and are only in the controller classes, specifically
the items controller and the add-edit-items controller.  These controller classes handling items and their characteristics are used within the dashboard
to allow the user to add items and their characteristics into the database and then have the model parts of the code display the information from the database. 
These are thrown if there is an issue in entering the data into the database because of disallowed formatting on the database table for a user's entry 
and potentially issues from accessing the data within the database. Basically, any manipulation of the data in the database via the user's interactions with the dashboard
will manipulate the underlying controllers which will then manipulate the database and require these throws exceptions. 


Instructions for use, describing how the user will interact with your program.

1	So, the first way to begin the program is by running the main.java file
2	This program starts off with showing the dashboard that has previous data entered on it
	a.	There is no login or password needed at this point (I will implement later)
	b.	The dashboard includes a couple of main features
		i.	Title “Inventory Management System”
		ii.	Quit button
		iii.	Add Item button
		iv.	Edit Item button
		v.	Search Bar
		vi.	Name, color, secondary color, brand, size, and 3 pieces of location information are all located in one table 
			(along with a delete button next to the row which can delete the item from inventory)
	c.	If starting with a database that has no entries in it, you are unable to select the edit item or delete button due 
		to the fact that a row entry needs to exist for the delete button to appear and a row needs to be selected for the edit item to work (which if you have 
		none, you will not be able to do)

3	Selecting the add item button
	a.	Another screen will pop up which will prompt you to enter in all the information for the item and either save or cancel the information entered.
	b.	You will enter in item name, item color, item brand, item size, location name, location rack (which can also be a location like right or left corner) and 
		location shelf (or bin number).
		i.	Everything but Location Rack or Location shelf are required in order to be saved
			1.	If the item name fields all the way down the location field aren’t filled out, then the “please fill out the * fields!* “message pops up on 
				the bottom of the window if you try to save it
			2.	If  location rack (or position specified in the room) is entered then the location shelf (or bin number) is required to be entered and vice 
				versa if a location shelf is entered,then a location rack needs to be entered if you’re attempting to save the entered information.
				a.	A message saying “Please fill out the shelf” will pop up on the bottom of the window if only the rack location is entered 
					without a shelf location
				b.	A message saying “Please fill out the rack” will pop up if only the shelf location is entered without a rack location
	c.	These choices were made so that incomplete data on the location of each item isn’t made and everything can be properly stored
	d.	You will be prompted on the bottom to cancel or save the item and the information you just entered
		i.	If you select cancel, the window will close and no information/item will be added to the dashboard’s table
		ii.	If you select save, the window will close and the information/item will be added to the dashboard’s table.
4	If selecting the edit item button 
	a.	When selecting the edit button WITHOUT selecting a row beforehand (specifying an item to be edited) the message “Choose a record from table” and an OK 
		button to exit this message.
		i.	Pressing the OK button will exit the message and return you back to the dashboard.
	b.	When selecting the item row you want to edit and then pressing the edit item button you’re prompted with a window
		i.	This window is just the previous add item window with all the details of the current column already entered into the fields
			1.	If all fields aren’t properly filled out like in the above details for the add item steps (say you delete the item name without replacing it) 
				then the same “please fill out the * fields!*” message appears 
			2.	If the rack location or shelf location fields aren’t both filled out at the same time or both aren’t filled out the messages “Please fill out 
				the shelf” or “Please fill out the rack” will pop up also.
	c.	You have the save or cancel items at the bottom of the window and the save button will save the changes to the table on the dashboard and appear directly 
		after and the cancel button will not save any changes and the information on the dashboard will remain the same.
5	When typing in the search you will have any letters and/or numbers or symbols that are in the items’ characteristics of any column on the table will show up
	a.	So, typing in white will show the results of any item which has the word white in any of the characteristics of the item (each column is searched row by 
		row for those particular search results)
	b.	These results will be filtered and displayed in the table on the dashboard.
6	If selecting the delete button, when clicking on it a message will appear
	a.	The message asks “Are you sure you want to delete?” and offers you to confirm or cancel
		i.	Pressing confirm will delete that row from the table and it will disappear on the dashboard’s table as you’re sent back to the dashboard
		ii.	Pressing cancel will send you back to the dashboard with no changes being made
7	Pressing the quit button will simply quit the program, no prompt appears and it will automatically be saved.


A brief overview of your design (how did you choose to represent the aspects of your program?)
	
	I somewhat pivoted from my initial approach and decided to focus on a database-central design along with focusing more on the aesthetics, 
	and figured I could build more add-ons and details to the program at a later date. I ended up representing everything via a database table
	that could be manipulated within the controllers a user could control that were displayed on a model dashboard. I just wanted everything 
	to be separated into very simple actions of adding, editing, quit, and delete. So, these were put into controller classes which manipulate the
	observable lists and then database extracts this information and updates by adding, editing, or deleting according to the user's actions.
	I utilized maven to structure everything better for ease of use of the graphics and the interactions with the .sql file for the database.
	

An overview of how your program functions, including how all of the pieces fit together.
	
	All in all, it's a simpler program on the backend than initially intended probably but it was WAYYYYYY more complicated in the fact that the interactions 
	between the database, maven, javafx, css, and the java backend interacted a lot more so it was more moving parts to keep track of and that's
	why the model-view-controller pattern became REALLY helpful to represent the aspects of my program. Where the frontend was properly separated 
	into manageable packages for the views (including the graphics, animation, and overall layout of the windows), then the controllers represented by
	the buttons the user could manipulate the underlying data with (so the controller java files and some of the DAO (data access objects) files to 
	manipulate the sql files), and then the backend  (java utils (which can also be for the controllers), model files, some DAO, and the sql files) 
	which stored and held all changes and interactions to be able to change the view files of the frontend, where it ends right back on the view 
	the user sees. The maven dependencies really help smooth out the interactions between them all too.
	
	Then, it's rinse and repeat where these files are able to handle any changes a user makes in that continual cycle.

A description of any possible bugs or problems with your program.

	While I don't think there are really any bugs wth the current program, the issues I may have right now is useability and not being exactly
	everything I wanted originally for the program still.

	Don’t have the titles of the Rack Location and Shelf Location columns to specify that they can also be labeled as position in room/rack location 
	and shelf location/bin #. This could be initially confusing for my wife at least. Also, I didn’t put in number of items column like I originally 
	wanted to. I have to go back and fix that also. 

	Plus, I potentially want to re-implement the itemID that I originally had and include it in the table along with a better search algorithm 
	which will utilize the itemID somewhat instead of just sequentially searching through every row and column. It would just give me so much 
	more control over my search and that's what I want to add again for ease of use. The current search I have is just so rudimentary that 
	it could issue some problems when trying to find very specific items.

A description of any extra features you choose to implement (differences from your proposal)

	Utilized a database for storage, utilized some css for extra GUI asthetic, utilized maven dependencies for more advanced uses of interactions.

	Didn't have a very advanced search function like originally wanted, didn't have much use for text processing or wrapper classes in regards to 
	the search function/user entries.


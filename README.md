README
========

The name of this project is Hangman.

The idea is to make a native app, where the user can play a game to guess a word the computer have picked.

####Features of the app:
* if you open the app, the gameplay must start
* display placeholders for the letters that the user didn't guessed
* the app must inform the user how many chances the user has before losing the game
* should have a on screen keyboard
* only accepts letters and isn't case sensitive
* has two settings, the user can choose how many chances he want to have and how long the word must be
* has highscores of the best 10 results
* you should see the letters the user have typed in.

The app would be a Android App and I write Hangman in Java. 


#### mockups:
<img src=http://imagizer.imageshack.us/v2/800x600q90/827/cxzm.png>

####De java files
#####Main.java:
Here are few buttons to :
* start the game,
* the rules,
* settings,
* scores 
* and quit. 

#####Game.java
has several functions:
* Should load the dictionaries with words and pick random words a word from the                                         dictionary from the database. If a user choose a certain number for the word, the program                               should pick a random a word with that number.  The database is in Sqlite with a column                                  for the words and a column for how long the word is. 
* Input letters, it should not matter if it is a capital of lowercase letter. The user can only type in letters and checks if the letter isn’t  already guessed. 
* Function for how many chances a user has. This depends also on the users input. 
* Function that checks if the input is in the word or not, update the page and checks If all the letters are guessed (than the user has won -> winner page and save the score in the High score database) of if there are enough chances left (if not the user has lost -> loser page). 

#####Settings.java
* Has two seekbars. One for how many letters the user wants the other for how many chances the user wants. And is has a “Start game” button. 

#####Scores.java
* Has a top 10 of the best results (results with the least mistake are at the top). Make use of a Sqlite Database, where the scores are saved. 
 

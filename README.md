# Project-5: Quiz Application w/ GUI

Our program compiles and runs through the GUIApp class. The Application class contains the main method that has the full user interface. 

# Class Outline

FillInTheBlank.java
- The FillInTheBlank class is a subclass of the Question class and allows for a Teacher to make their quizzes with Fill in the Blanks. The testing done to verify that it works properly was established through making numerous Fill in the Blank quizzes and checking if they were created properly. The relationship of this class to other classes is that is a subclass of the Question class and is utilized in the Quiz class if a teacher chooses to make their quiz with Fill in the Blanks.

MultipleChoice.java
- The Multiple choice class is a subclass of the Question class and allows for a Teacher to make their quizzes using a Multiple Choice selection. The testing done to verify that it works properly was established through creating different Multiple Choice quizzes of different question numbers and question types, and checking if they were created properly or debugging accordingly. The relationship of this class to other classes is that it is a subclass of the Question class and it's utilized in the Quiz clas if a teacher wants to make their quiz using Multiple Choice. 

Question.java
- The Question class is a class that allows for a Question object to be created with a question and an answer. The Question class is mainly used through the Quiz class where the Quiz utilizes an ArrayList of Questions as each quiz will be made up of the number of questions that a teacher chooses to create. The testing done to verify that this works properly is done through the Multiple Choice class and the other subclasses of Question that utilize the Question class. The relationship of this class to the other classes is that it is the superclass for the Three types of Questions able to be created: Multiple Choice, Fill in the Blank, and True or False. 

Quiz.java
- The Quiz class is the class that includes the user interface for a teacher to create the type of quiz, the amount of questions in their quiz, and other attributes for each of the quizzes they create. It also includes the functionality that saves the quizzes created by writing the information to a text file, so that even if a user disconnects, all of the information stays present and the quizzes are not lost. The testing done to verify that this class works was done by creating multiple quizzes of all of the types and checking that the information is properly written to the QuizList.txt file and the QuizTitles.txt file. The relationship of this class to the other classes is that it utilizes the Question, Multiple Choice, and Fill in the Blank class and contains the functions for a Teacher to create quizzes. 

User.java
- The User class is the class that allows for a user to be created. It also writes the User information to a userlist.txt file in order to keep a user's information saved even if they end the application. The user class allows a teacher or student user to be created, and correspondingly from there give them the specific functions that they are able to pursue as a teacher or a student. The testing done to verify that this class works is done by creating numerous users, both teachers and students and debugging as necessary if the users are not created properly. The relationship of this class to other classes is that it is the superclass to the Teacher and Student class. 

Teacher.java
- The Teacher class is the class that allows for a Teacher user to be created. This class contains the functionalities of being able to create, edit, and delete quizzes as a teacher would be able to do in the Quiz application. The testing done to verify that this class works was done through making teacher users and having them go through each of the functions: create quizzes, edit quizzes, and delete quizzes. The relationship of this class to other classes is that it is a subclass of the User class and the quizzes created by the teacher in this class are then taken by the students in the Student class. 

Student.java
- The Student class is the class that allows for a Student user to be created. This class contains the functionalities of a student, which is to be able to take the quizzes that are made by a teacher. The testing done to verify that this class works is after the testing for Teacher class was finished to check that the quizzes were correctly made, it was tested to see that the students could take those quizzes properly. The relationship of this class to other classes is that is a subclass of the User class. 

TimeStamp.java
- The TimeStamp class allows for the date and time at which a Student takes the quiz to be seen. The testing done to very that this class works is done by checking whether the current time stamp is correctly printed when running throuhg the program. The relationship of this class is to other classes is just that it allows the static method in TimeStamp to be called to get the time stamp of the quiz submission. 

GUIApp.java
- The GUIApp class creates the entire graphical user interface for the user that allows the user to interact with all of the functionalities of the application. The testing done to verify that this class works is by running the program several times with different choices each time, either choosing different user or choosing different options from the dropdown list. The relationship of this class to other classes is that it implements many of the methods from the teacher and student class called from a teacher ands student object. 




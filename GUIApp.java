import javax.swing.*;
import java.io.IOException;

public class GUiApp {

    public static void main (String[] args) throws IOException {
        //Welcome Message
        JOptionPane.showMessageDialog(null, "Welcome to the Quiz Application!", "Quiz Application",
                JOptionPane.INFORMATION_MESSAGE);

        String[] options = {"Student", "Teacher"};
        int user = JOptionPane.showOptionDialog(null, "Are you a teacher or a student?", "Quiz Application",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);

        //If user is a teacher
       if (user == 1) {
           //Check if their account exists already or not
           int existingUser;
           String username;
           String password;
           String userChoice;


           Teacher teacher = new Teacher();
           teacher.runUser("teacher");

           do {
               //DROPDOWN MENU CHOICES
               String[] choices = {"Create a Quiz", "Edit a Quiz", "Delete a Quiz", "Grade a Quiz", "Logout", "Edit Account", "Delete Account"};
               userChoice = (String) JOptionPane.showInputDialog(null, "Would you like to...",
                       "Choice?", JOptionPane.QUESTION_MESSAGE,
                       null, choices, choices[0]);

               //teacher is going to create a quiz
               if (userChoice.equals("Create a Quiz")) {
                   teacher.createQuiz();
                   JOptionPane.showMessageDialog(null, TimeStamp.printTimeStamp(), "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
               } else if (userChoice.equals("Edit a Quiz")) {                   //teacher is going to edit a quiz
                   JOptionPane.showMessageDialog(null, "2 works", "Quiz Application",
                           JOptionPane.QUESTION_MESSAGE);
               } else if (userChoice.equals("Delete a Quiz")) {                 //teacher is going to delete a quiz

               } else if (userChoice.equals("Grade a Quiz")) {                  //teacher is going to grade a quiz

               } else if (userChoice.equals("Logout")) {                        //teacher is logging out
                   JOptionPane.showMessageDialog(null, "Thank you for using the Quiz Application!"
                           , "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
               } else if (userChoice.equals("Edit Account")) {                  //teacher is editing their account

               } else if (userChoice.equals("Delete Account")) {                //teacher is deleting their account

               }
           } while (!userChoice.equals("Logout"));
           //if user is a student
       } else if (user == 0) {
            Student student = new Student();
            student.runUser("student");
           String[] choices = {"Take a Quiz", "Logout", "Edit Account", "Delete Account"};
           String userChoice;
           userChoice = (String) JOptionPane.showInputDialog(null, "Would you like to...",
                   "Choice?", JOptionPane.QUESTION_MESSAGE,
                   null, choices, choices[0]);
           if (userChoice.equals("Take a Quiz")) {

           } else if (userChoice.equals("Logout")) {

           } else if (userChoice.equals("Edit Account")) {

           } else if (userChoice.equals("Delete Account")) {

           }

        }

    }
}

import javax.swing.*;
import java.io.IOException;

public class GUiApp {

    public static void main (String[] args) throws IOException {
        boolean deleteCheck = false;
        boolean checking = false;
        boolean check4 = false;
        String quizTitleChoice = "";
        int count4 = 0;
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
               userChoice = (String) JOptionPane.showInputDialog(null, "Select which of the following would you like to do",
                       "Quiz Application", JOptionPane.QUESTION_MESSAGE,
                       null, choices, choices[0]);

               //teacher is going to create a quiz
               if (userChoice.equals("Create a Quiz")) {
                   teacher.createQuiz();
                   JOptionPane.showMessageDialog(null, TimeStamp.printTimeStamp(), "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
               } else if (userChoice.equals("Edit a Quiz")) {                   //teacher is going to edit a quiz
                   //JOptionPane.showMessageDialog(null, "2 works", "Quiz Application",
                          // JOptionPane.QUESTION_MESSAGE);
               } else if (userChoice.equals("Delete a Quiz")) {                 //teacher is going to delete a quiz
                   do {
                       teacher.showQuizTitles();
                       JOptionPane.showMessageDialog(null, "These are the quizzes you can delete: \n" +
                               teacher.getTitlesList(), "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                       String quizTitle = JOptionPane.showInputDialog(null, "What is the name of the quiz you would like to delete?",
                               "Quiz Application", JOptionPane.QUESTION_MESSAGE);
                       deleteCheck = teacher.deleteQuiz(quizTitle);
                       teacher.deleteFromQuizList(quizTitle);
                   } while (deleteCheck);
               } else if (userChoice.equals("Grade a Quiz")) {                  //teacher is going to grade a quiz

               } else if (userChoice.equals("Logout")) {                        //teacher is logging out
                   JOptionPane.showMessageDialog(null, "Thank you for using the Quiz Application!"
                           , "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
               } else if (userChoice.equals("Edit Account")) {                  //teacher is editing their account
                   String newUsername = JOptionPane.showInputDialog(null, "Enter your new username: ", "Quiz Application",
                           JOptionPane.QUESTION_MESSAGE);
                   String newPassword = JOptionPane.showInputDialog(null, "Enter your new password: ", "Quiz Application",
                           JOptionPane.QUESTION_MESSAGE);
                   String tempUser = teacher.editUser(teacher.getUsername(), newUsername, newPassword);
                   teacher.setUsername(tempUser);
                   JOptionPane.showMessageDialog(null, "User edited successfully!", "Quiz Application",
                           JOptionPane.INFORMATION_MESSAGE);
               } else if (userChoice.equals("Delete Account")) {                //teacher is deleting their account
                   teacher.deleteUser(teacher.getUsername());
                   JOptionPane.showMessageDialog(null, "User deleted. Thank you for using the quiz application!",
                          "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
               }
           } while (!userChoice.equals("Logout") && !userChoice.equals("Delete Account"));
           //if user is a student
       } else if (user == 0) {
           Student student = new Student();
           student.runUser("student");
           String userChoice;

           do {
               String[] choices = {"Take a Quiz", "Logout", "Edit Account", "Delete Account"};
               userChoice = (String) JOptionPane.showInputDialog(null, "Select which of the following you would like to do",
                       "Quiz Application", JOptionPane.QUESTION_MESSAGE,
                       null, choices, choices[0]);
               if (userChoice.equals("Take a Quiz")) {
                   do {
                       quizTitleChoice = student.chooseQuiz();
                       checking = student.checkMatch(quizTitleChoice);
                       if (!checking) {
                           check4 = false;
                       } else {
                           JOptionPane.showMessageDialog(null, student.answerQuiz(quizTitleChoice), "Quiz Application",
                                   JOptionPane.INFORMATION_MESSAGE);
                           JOptionPane.showMessageDialog(null, student.writeAnswers(quizTitleChoice, student.answerQuiz(quizTitleChoice)),
                                   "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                           check4 = true;
                       }
                       JOptionPane.showMessageDialog(null, "The quiz was turned in at\n" + TimeStamp.printTimeStamp(),
                               "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                       count4++;
                   } while (!check4);

               } else if (userChoice.equals("Logout")) {
                   JOptionPane.showMessageDialog(null, "Thank you for using the Quiz Application!"
                           , "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
               } else if (userChoice.equals("Edit Account")) {
                   String newUsername = JOptionPane.showInputDialog(null, "Enter your new username: ",
                           "Quiz Application", JOptionPane.QUESTION_MESSAGE);
                   String newPassword = JOptionPane.showInputDialog(null, "Enter your new password: ",
                           "Quiz Application", JOptionPane.QUESTION_MESSAGE);
                   String tempUser = student.editUser(student.getUsername(), newUsername, newPassword);
                   student.setUsername(tempUser);
               } else if (userChoice.equals("Delete Account")) {
                   student.deleteUser(student.getUsername());
                   JOptionPane.showMessageDialog(null, "User deleted. Thank you for using the quiz application!",
                           "Quiz Application", JOptionPane.INFORMATION_MESSAGE);

               }

           } while (!userChoice.equals("Logout") && !userChoice.equals("Delete Account"));
       }

    }
}

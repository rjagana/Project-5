import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GUIApp {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4242);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
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
        System.out.println(user);
        writer.write(String.valueOf(user));
        writer.println();
        writer.flush();

        if (user == 1) {
            //Check if their account exists already or not
            int existingUser;
            String username;
            String password;
            String userChoice;
            reader.readLine();

            do {
                //DROPDOWN MENU CHOICES
                String[] choices = {"Create a Quiz", "Edit a Quiz", "Delete a Quiz", "Grade a Quiz", "Logout", "Edit Account", "Delete Account"};
                userChoice = (String) JOptionPane.showInputDialog(null, "Select which of the following would you like to do",
                        "Quiz Application", JOptionPane.QUESTION_MESSAGE,
                        null, choices, choices[0]);
                writer.write(userChoice);
                writer.println();
                writer.flush();

                //teacher is going to create a quiz
                if (userChoice.equals("Create a Quiz")) {
                    reader.readLine();
                } else if (userChoice.equals("Edit a Quiz")) {                   //teacher is going to edit a quiz
                    //JOptionPane.showMessageDialog(null, "2 works", "Quiz Application",
                    // JOptionPane.QUESTION_MESSAGE);
                } else if (userChoice.equals("Delete a Quiz")) {                 //teacher is going to delete a quiz
                    do {
                        String quizTitle = JOptionPane.showInputDialog(null, "What is the name of the quiz you would like to delete?",
                                "Quiz Application", JOptionPane.QUESTION_MESSAGE);
                        writer.write(quizTitle);
                        writer.println();
                        writer.flush();
                        deleteCheck = Boolean.parseBoolean(reader.readLine());
                    } while (deleteCheck);
                } else if (userChoice.equals("Grade a Quiz")) {                  //teacher is going to grade a quiz
                } else if (userChoice.equals("Logout")) {                        //teacher is logging out
                    JOptionPane.showMessageDialog(null, "Thank you for using the Quiz Application!"
                            , "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                } else if (userChoice.equals("Edit Account")) {                  //teacher is editing their account
                    String newUsername = JOptionPane.showInputDialog(null, "Enter your new username: ", "Quiz Application",
                            JOptionPane.QUESTION_MESSAGE);
                    writer.write(newUsername);
                    writer.println();
                    writer.flush();
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password: ", "Quiz Application",
                            JOptionPane.QUESTION_MESSAGE);
                    writer.write(newPassword);
                    writer.println();
                    writer.flush();
                    JOptionPane.showMessageDialog(null, "User edited successfully!", "Quiz Application",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (userChoice.equals("Delete Account")) {                //teacher is deleting their account
                    JOptionPane.showMessageDialog(null, "User deleted. Thank you for using the quiz application!",
                            "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                }
            } while (!userChoice.equals("Logout") && !userChoice.equals("Delete Account"));
            //if user is a student
        } else if (user == 0) {

            String userChoice;

            do {
                String[] choices = {"Take a Quiz", "Logout", "Edit Account", "Delete Account"};
                userChoice = (String) JOptionPane.showInputDialog(null, "Select which of the following you would like to do",
                        "Quiz Application", JOptionPane.QUESTION_MESSAGE,
                        null, choices, choices[0]);
                writer.write(userChoice);
                writer.println();
                writer.flush();
                if (userChoice.equals("Take a Quiz")) {
                  /*
                   do {
                       quizTitleChoice = student.chooseQuiz();
                       checking = student.checkMatch(quizTitleChoice);
                       if (!checking) {
                           check4 = false;
                       } else {
                           JOptionPane.showMessageDialog(null,student.writeAnswers(quizTitleChoice, student.answerQuiz(quizTitleChoice)), "Quiz Application",
                                   JOptionPane.INFORMATION_MESSAGE);
                          // JOptionPane.showMessageDialog(null, student.writeAnswers(quizTitleChoice, student.answerQuiz(quizTitleChoice)),
                             //      "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                          // student.writeAnswers(quizTitleChoice, student.answerQuiz(quizTitleChoice));
                           check4 = true;
                           JOptionPane.showMessageDialog(null, "The quiz was turned in at\n" + TimeStamp.printTimeStamp(),
                                   "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                       }

                       count4++;
                   } while (!check4);

                   */

                } else if (userChoice.equals("Logout")) {
                    JOptionPane.showMessageDialog(null, "Thank you for using the Quiz Application!"
                            , "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    writer.close();
                    reader.close();
                } else if (userChoice.equals("Edit Account")) {
                    String newUsername = JOptionPane.showInputDialog(null, "Enter your new username: ", "Quiz Application",
                            JOptionPane.QUESTION_MESSAGE);
                    writer.write(newUsername);
                    writer.println();
                    writer.flush();
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password: ", "Quiz Application",
                            JOptionPane.QUESTION_MESSAGE);
                    writer.write(newPassword);
                    writer.println();
                    writer.flush();
                    JOptionPane.showMessageDialog(null, "User edited successfully!", "Quiz Application",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (userChoice.equals("Delete Account")) {
                    JOptionPane.showMessageDialog(null, "User deleted. Thank you for using the quiz application!",
                            "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    writer.close();
                    reader.close();
                }

            } while (!userChoice.equals("Logout") && !userChoice.equals("Delete Account"));
        }

    }
}

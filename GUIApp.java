import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;

public class GUIApp {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 4242);
        ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
        writer.flush();
        ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
        boolean deleteCheck = false;
        boolean checking = false;
        boolean check4 = false;
        String quizTitleChoice = "";
        String editedUser;
        String[] quizTitleOptions;
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
        writer.writeObject(String.valueOf(user));
        writer.flush();

        if (user == 1) {
            //Check if their account exists already or not
            int existingUser;
            String username;
            String password;
            String userChoice;
            //reader.readLine();
            Teacher teacher = new Teacher();
            teacher.runUser("teacher");
            do {
                //DROPDOWN MENU CHOICES
                String[] choices = {"Create a Quiz", "Edit a Quiz", "Delete a Quiz", "Grade a Quiz", "Logout", "Edit Account", "Delete Account"};
                userChoice = (String) JOptionPane.showInputDialog(null, "Select which of the following would you like to do",
                        "Quiz Application", JOptionPane.QUESTION_MESSAGE,
                        null, choices, choices[0]);
                writer.writeObject(userChoice);
                writer.flush();

                //teacher is going to create a quiz
                if (userChoice.equals("Create a Quiz")) {
                    Quiz newQuiz = teacher.createQuiz();
                    writer.writeObject(newQuiz);
                    writer.flush();
                    JOptionPane.showMessageDialog(null, TimeStamp.printTimeStamp(), "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                } else if (userChoice.equals("Edit a Quiz")) {                   //teacher is going to edit a quiz
                    //JOptionPane.showMessageDialog(null, "2 works", "Quiz Application",
                    // JOptionPane.QUESTION_MESSAGE);
                } else if (userChoice.equals("Delete a Quiz")) {                 //teacher is going to delete a quiz
                    do {
                        JOptionPane.showMessageDialog(null, "These are the quizzes you can delete: \n" +
                                teacher.getTitlesList(), "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                        String quizTitle = JOptionPane.showInputDialog(null, "What is the name of the quiz you would like to delete?",
                                "Quiz Application", JOptionPane.QUESTION_MESSAGE);
                        writer.writeObject(quizTitle);
                        writer.flush();
                        deleteCheck = reader.readBoolean();
                    } while (deleteCheck);
                } else if (userChoice.equals("Grade a Quiz")) {                  //teacher is going to grade a quiz
                } else if (userChoice.equals("Logout")) {                        //teacher is logging out
                    JOptionPane.showMessageDialog(null, "Thank you for using the Quiz Application!"
                            , "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else if (userChoice.equals("Edit Account")) {                  //teacher is editing their account
                    String newUsername = JOptionPane.showInputDialog(null, "Enter your new username: ", "Quiz Application",
                            JOptionPane.QUESTION_MESSAGE);
                    writer.writeObject(newUsername);
                    writer.flush();
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password: ", "Quiz Application",
                            JOptionPane.QUESTION_MESSAGE);
                    writer.writeObject(newPassword);
                    writer.flush();
                    editedUser = (String) reader.readObject();
                    if(editedUser.equals("")){
                        JOptionPane.showMessageDialog(null, "Error. Username taken.", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "User edited successfully!", "Quiz Application",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (userChoice.equals("Delete Account")) {                //teacher is deleting their account
                    JOptionPane.showMessageDialog(null, "User deleted. Thank you for using the quiz application!",
                            "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } while (!userChoice.equals("Logout") && !userChoice.equals("Delete Account"));
            //if user is a student
        } else if (user == 0) {

            String userChoice;
            Student student = new Student();
            student.runUser("student");

            do {
                String[] choices = {"Take a Quiz", "Logout", "Edit Account", "Delete Account"};
                userChoice = (String) JOptionPane.showInputDialog(null, "Select which of the following you would like to do",
                        "Quiz Application", JOptionPane.QUESTION_MESSAGE,
                        null, choices, choices[0]);
                writer.writeObject(userChoice);
                writer.flush();
                if (userChoice.equals("Take a Quiz")) {
                   do {
                       quizTitleOptions = (String[]) reader.readObject();
                       String choice = (String) JOptionPane.showInputDialog(null, "Select which of the following would you like to do",
                               "Quiz Application", JOptionPane.QUESTION_MESSAGE,
                               null, quizTitleOptions, quizTitleOptions[0]);
                       ArrayList<String> quizTitles = (ArrayList<String>) reader.readObject();
                       writer.writeObject(choice);
                       writer.flush();
                       checking = student.checkMatch(choice, quizTitles);
                       if (!checking) {
                           check4 = false;
                       } else {
                           ArrayList<String> answerList = student.answerQuiz(quizTitleChoice);
                           writer.writeObject(answerList);
                           String answerFile = (String) reader.readObject();
                           JOptionPane.showMessageDialog(null, answerFile, "Quiz Application",
                                   JOptionPane.INFORMATION_MESSAGE);
                           check4 = true;
                           JOptionPane.showMessageDialog(null, "The quiz was turned in at\n" + TimeStamp.printTimeStamp(),
                                   "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                       }
                       count4++;
                   } while (!check4);



                } else if (userChoice.equals("Logout")) {
                    JOptionPane.showMessageDialog(null, "Thank you for using the Quiz Application!"
                            , "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    writer.close();
                    reader.close();
                    return;
                } else if (userChoice.equals("Edit Account")) {
                    String newUsername = JOptionPane.showInputDialog(null, "Enter your new username: ", "Quiz Application",
                            JOptionPane.QUESTION_MESSAGE);
                    writer.writeObject(newUsername);
                    writer.flush();
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password: ", "Quiz Application",
                            JOptionPane.QUESTION_MESSAGE);
                    writer.writeObject(newPassword);
                    writer.flush();
                    editedUser = (String) reader.readObject();
                    if(editedUser.equals("")){
                        JOptionPane.showMessageDialog(null, "Error. Username taken.", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "User edited successfully!", "Quiz Application",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (userChoice.equals("Delete Account")) {
                    JOptionPane.showMessageDialog(null, "User deleted. Thank you for using the quiz application!",
                            "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    writer.close();
                    reader.close();
                    return;
                }

            } while (!userChoice.equals("Logout") && !userChoice.equals("Delete Account"));
        }

    }
}

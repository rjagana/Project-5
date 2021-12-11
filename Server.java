import javax.swing.*;
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean deleteCheck = false;
        String quizTitleChoice = "";
        boolean checking = false;
        boolean check4 = false;
        int count4 = 0;
        ServerSocket serverSocket = new ServerSocket(4242);
        System.out.println("Waiting for the client to connect...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());

        String message = reader.readLine();
        String message2; //user choice

        if (message.equals("1")) {
            System.out.println("Teacher worked");
            Teacher teacher = new Teacher();
            teacher.runUser("teacher");
            do {
                int existingUser;
                String username;
                String password;
                writer.writeObject("1");
                writer.flush();
                message2 = (String) reader.readObject();
                if (message2.equals("Create a Quiz")) {
                    teacher.createQuiz();
                    JOptionPane.showMessageDialog(null, TimeStamp.printTimeStamp(), "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    writer.writeObject("1");
                    writer.flush();
                } else if (message2.equals("Edit a Quiz")) {                   //teacher is going to edit a quiz
                    //JOptionPane.showMessageDialog(null, "2 works", "Quiz Application",
                    // JOptionPane.QUESTION_MESSAGE);
                } else if (message2.equals("Delete a Quiz")) {                 //teacher is going to delete a quiz
                    System.out.println("delet8ing");
                    do {
                        teacher.showQuizTitles();
                        JOptionPane.showMessageDialog(null, "These are the quizzes you can delete: \n" +
                                teacher.getTitlesList(), "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                        message = reader.readLine();
                        deleteCheck = teacher.deleteQuiz(message);
                        teacher.deleteFromQuizList(message);
                        writer.writeObject(String.valueOf(deleteCheck));
                    } while (deleteCheck);
                } else if (message2.equals("Grade a Quiz")) {                  //teacher is going to grade a quiz
                } else if (message2.equals("Edit Account")) {                  //teacher is editing their account
                    //from gui to server
                    message = reader.readLine();
                    String newUsername = message;
                    message = reader.readLine();
                    String newPassword = message;
                    String tempUser = teacher.editUser(teacher.getUsername(), newUsername, newPassword);
                    teacher.setUsername(tempUser);
                } else if (message2.equals("Delete Account")) {                //teacher is deleting their account
                    teacher.deleteUser(teacher.getUsername());
                }
            } while (!message2.equals("Logout") && !message2.equals("Delete Account"));
        } else if (message.equals("0")) {
            System.out.println("Student worked");
            Student student = new Student();
            student.runUser("student");
            message2 = reader.readLine();
            do {
                String[] choices = {"Take a Quiz", "Logout", "Edit Account", "Delete Account"};

                if (message2.equals("Take a Quiz")) {
                    do {
                        quizTitleChoice = student.chooseQuiz();
                        checking = student.checkMatch(quizTitleChoice);
                        if (!checking) {
                            check4 = false;
                        } else {
                            JOptionPane.showMessageDialog(null, student.writeAnswers(quizTitleChoice, student.answerQuiz(quizTitleChoice)), "Quiz Application",
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

                } else if (message2.equals("Logout")) {
                    writer.close();
                    reader.close();
                } else if (message2.equals("Edit Account")) {
                    message = reader.readLine();
                    String newUsername = message;
                    message = reader.readLine();
                    String newPassword = message;
                    String tempUser = student.editUser(student.getUsername(), newUsername, newPassword);
                    student.setUsername(tempUser);
                } else if (message2.equals("Delete Account")) {
                    student.deleteUser(student.getUsername());
                    writer.close();
                    reader.close();
                }
            } while (!message2.equals("Logout") && !message2.equals("Delete Account"));

        }
    }
}

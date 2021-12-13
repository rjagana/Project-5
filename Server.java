import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean deleteCheck = false;
        String[] quizTitleOptions;
        boolean checking = false;
        boolean check4 = false;
        int count4 = 0;
        ServerSocket serverSocket = new ServerSocket(4242);
        System.out.println("Waiting for the client to connect...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");
        ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
        writer.flush();
        ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());

        String message = (String) reader.readObject();
        String message2; //user choice

        if (message.equals("1")) {
            System.out.println("Teacher worked");
            Teacher teacher = new Teacher();
            do {
                int existingUser;
                String username;
                String password;
                writer.writeObject("1");
                writer.flush();
                message2 = (String) reader.readObject();
                if (message2.equals("Create a Quiz")) {
                    Quiz quiz = (Quiz) reader.readObject();
                    quiz.writeFile(quiz.toString());
                    quiz.writeQuizTitles(quiz.getTitle());
                    quiz.writeQuizData(quiz.toDataString());
                    writer.writeObject("1");
                    writer.flush();
                } else if (message2.equals("Edit a Quiz")) {                   //teacher is going to edit a quiz
                    //JOptionPane.showMessageDialog(null, "2 works", "Quiz Application",
                    // JOptionPane.QUESTION_MESSAGE);
                } else if (message2.equals("Delete a Quiz")) {                 //teacher is going to delete a quiz
                    do {
                        String quizTitle = (String) reader.readObject();
                        deleteCheck = teacher.deleteQuiz(quizTitle);
                        if (!deleteCheck) {
                            ArrayList<String> temp = new ArrayList<>(teacher.getTitlesList());
                            ArrayList<Quiz> temp1 = new ArrayList<>(teacher.getQuizList());
                            PrintWriter pw = new PrintWriter(new FileOutputStream("QuizTitles.txt", false));
                            PrintWriter pw2 = new PrintWriter(new FileOutputStream("QuizList.txt", false));
                            PrintWriter pw3 = new PrintWriter(new FileOutputStream("QuizData.txt", false));

                            for (String s : temp) {
                                pw.write(s + "\n");
                            }
                            for (Quiz x : temp1) {
                                pw2.write(x.toString() + "\n");
                                pw3.write(x.toDataString() + "\n");
                            }

                            pw.close();
                            pw2.close();
                            pw3.close();
                        }
                        writer.writeObject(deleteCheck);
                        writer.flush();
                    } while (deleteCheck);
                } else if (message2.equals("Logout")) {
                    //stop server ig?
                } else if (message2.equals("Grade a Quiz")) {                  //teacher is going to grade a quiz
                } else if (message2.equals("Edit Account")) {                  //teacher is editing their account
                    //from gui to server
                    message = (String) reader.readObject();
                    String newUsername = message;
                    message = (String) reader.readObject();
                    String newPassword = message;
                    String tempUser = teacher.editUser(teacher.getUsername(), newUsername, newPassword);
                    if (tempUser.equals("")) {
                        writer.writeObject("1");
                        writer.flush();
                    } else {
                        teacher.setUsername(tempUser);
                    }
                } else if (message2.equals("Delete Account")) {                //teacher is deleting their account
                    teacher.deleteUser(teacher.getUsername());
                }
            } while (!message2.equals("Logout") && !message2.equals("Delete Account"));
        } else if (message.equals("0")) {
            System.out.println("Student worked");
            Student student = new Student();
            message2 = (String) reader.readObject();
            do {
                String[] choices = {"Take a Quiz", "Logout", "Edit Account", "Delete Account"};

                if (message2.equals("Take a Quiz")) {
                    do {
                        quizTitleOptions = student.options();
                        writer.writeObject(quizTitleOptions);
                        writer.flush();
                        writer.writeObject(student.getTitlesList());
                        writer.flush();
                        String quizTitleChoice = (String) reader.readObject();
                        if (!checking) {
                            check4 = false;
                        } else {
                            ArrayList<String> answerList = (ArrayList<String>) reader.readObject();
                            String answerFile = student.writeAnswers(quizTitleChoice, answerList);
                            writer.writeObject(answerFile);
                            writer.flush();
                            check4 = true;
                        }
                        count4++;
                    } while (!check4);

                } else if (message2.equals("Logout")) {
                    writer.close();
                    reader.close();
                } else if (message2.equals("Edit Account")) {
                    message = (String) reader.readObject();
                    String newUsername = message;
                    message = (String) reader.readObject();
                    String newPassword = message;
                    String tempUser = student.editUser(student.getUsername(), newUsername, newPassword);
                    if (tempUser.equals("")) {
                        writer.writeObject("1");
                        writer.flush();
                    } else {
                        student.setUsername(tempUser);
                    }
                } else if (message2.equals("Delete Account")) {
                    student.deleteUser(student.getUsername());
                    writer.close();
                    reader.close();
                }
            } while (!message2.equals("Logout") && !message2.equals("Delete Account"));

        }
    }
}

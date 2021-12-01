import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User {

    private final ArrayList<String> titlesList = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(String username, String password) {
        super(username, password, "student");
    }


    public String chooseQuiz() throws IOException {
        FileReader fr = new FileReader("QuizTitles.txt");
        BufferedReader bufferedReader = new BufferedReader(fr);
        titlesList.clear();
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                titlesList.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] options = new String[titlesList.size()];
        for (int i = 0; i < titlesList.size(); i++) {
            options[i] = titlesList.get(i);
        }
        String choice = String.valueOf(JOptionPane.showOptionDialog(null, "Choose the title of the quiz you would like to take!", "Quiz Application",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]));
        return choice;
    }

    public boolean checkMatch(String quizTitleChoice) {
        boolean check;
        do {
            check = true;
            for (int i = 0; i < titlesList.size(); i++) {
                if (quizTitleChoice.equalsIgnoreCase(titlesList.get(i))) {
                    System.out.println("You are taking the " + titlesList.get(i) + " quiz!");
                    return true;
                }
            }
            JOptionPane.showMessageDialog(null,"The quiz title you have entered does not exist! Please choose again!",
            "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Choose the title of the quiz you would like to take!",
                    "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
        } while (!check);
        return false;
    }

    public void printQuiz() throws FileNotFoundException {
        FileReader fr = new FileReader("QuizList.txt");
        BufferedReader bufferedReader = new BufferedReader(fr);
        String quiz = "";

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                quiz = quiz + "\n" + line;
                line = bufferedReader.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(quiz);
    }

    public int takeQuiz(String quizTitleChoice) throws FileNotFoundException {
        FileReader fr = new FileReader("QuizList.txt");
        BufferedReader bufferedReader = new BufferedReader(fr);
        String quiz = "";
        String x = "";

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                //  line = bufferedReader.readLine();
                if (line.equalsIgnoreCase("Title: " + quizTitleChoice)) {
                    quiz = quiz + "\n" + line;
                    line = bufferedReader.readLine();
                    x = line.substring(21);
                    while (line != null && !line.contains("Title")) {
                        if (!line.contains("Answer")) {
                            quiz = quiz + "\n" + line;
                        }
                        line = bufferedReader.readLine();
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(quiz);
        return Integer.parseInt(x);
    }

    public ArrayList<String> answerQuiz(String quizTitleChoice) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int x = takeQuiz(quizTitleChoice);
        ArrayList<String> answerList = new ArrayList<>();
        String answer;
        for (int i = 0; i < x; i++) {
            int temp = i + 1;
             answer = JOptionPane.showInputDialog(null, "Please enter your answer for Question " + temp + ":",
                    "Quiz Application", JOptionPane.QUESTION_MESSAGE);
            answerList.add(answer);
        }
        JOptionPane.showMessageDialog(null, "Thank you for taking the quiz", "Quiz Application",
                JOptionPane.INFORMATION_MESSAGE);
        return answerList;
    }

    public String writeAnswers(String quizTitleChoice, ArrayList<String> answers) throws FileNotFoundException {
        String temp = userInFile + "_" + quizTitleChoice;
        int attempts = 1;
        File f;
        do {
            f = new File(temp);
            if (f.exists()) {
                temp = getUsername() + "_" + quizTitleChoice + attempts;
                attempts++;
            }
        } while (f.exists());
        FileOutputStream fos = new FileOutputStream(temp + ".txt", true);
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < answers.size(); i++) {
            pw.println(answers.get(i));
        }
        pw.close();
        return temp;
    }
}

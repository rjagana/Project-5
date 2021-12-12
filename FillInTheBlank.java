import javax.swing.*;
import java.util.Scanner;

public class FillInTheBlank extends Question {
    private static String checkAnswer;
    String answerFIB = "";

    public FillInTheBlank() {
        super();
    }

    public FillInTheBlank(String question, String answerFIB) {
        super(question, answerFIB);
    }

    public String getFIBAnswer() {
        return answerFIB;
    }

    //method that sets the answer for the Fill In The Blank question
    public String setAnswer() {
        String choicePrompt = "What is the answer to the blank?";
        String choiceError = "Please enter some text for your choice!";
        String answer = "";
        boolean loop = true;
        Scanner scn = new Scanner(System.in);

        while (loop) {
            System.out.println(choicePrompt);
            answer = scn.nextLine();
            if (answer != null) {
                loop = false;
            } else {
                System.out.println(choiceError);
                loop = true;
            }
        }
        return answer;
    }

    public static String setAnswer2() {
        String choicePrompt = "What is the answer to the blank?";
        String choiceError = "Please enter some text for your choice!";
        String answer = "";
        boolean loop = true;
        Scanner scn = new Scanner(System.in);

        while (loop) {
            answer = JOptionPane.showInputDialog(null, choicePrompt, "Quiz Application", JOptionPane.QUESTION_MESSAGE);
            if (answer != null) {
                loop = false;
            } else {
                System.out.println(choiceError);
                loop = true;
            }
        }
        checkAnswer = answer;
        return answer;
    }
    public String getCheckAnswer() {
        return checkAnswer;
    }

    @Override
    public String toString() {
        return String.format("%s\nAnswer: %s\n", getQuestion(), getCheckAnswer());
    }

    public String toDataString() {
        return String.format("%s %s", getQuestion().replaceAll(" ", "_"), getFIBAnswer().replaceAll(" ", "_"));
    }

}

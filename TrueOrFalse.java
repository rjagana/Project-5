import javax.swing.*;
import java.util.Scanner;

public class TrueOrFalse extends Question {

    String answer = "";

    public TrueOrFalse() {
        super();
    }

    public TrueOrFalse(String question, String answer) {
        super(question, answer);
    }

    public String getAnswer() {
        return answer;
    }

    //method that sets the answer for the T/F question
    public String setAnswer() {
        String choicePrompt = "Please enter the letter answer True (T) or False (F): ";
        String choiceError = "Please enter T or F!";
        String answer;
        boolean loop = true;
        Scanner scn = new Scanner(System.in);
        do {
            System.out.println(choicePrompt);
            answer = scn.nextLine();
            if (answer.equalsIgnoreCase("T") || answer.equalsIgnoreCase("F")) {
                loop = false;
            } else {
                System.out.println(choiceError);
                loop = true;
            }
        } while (loop);

        return answer;
    }

    public String setAnswer2() {
        String choicePrompt = "Please select the letter answer True (T) or False (F): ";
        String choiceError = "Please enter T or F!";
        String answer;
        boolean loop = true;
        Scanner scn = new Scanner(System.in);

            String[] options = {"False", "True"};
            answer = String.valueOf(JOptionPane.showOptionDialog(null, choicePrompt, "Quiz Application",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]));

            if (answer.equals("1")) {
                answer = "True";
            } else {
                answer = "False";
            }

        return answer;
    }

    @Override
    public String toString() {
        return String.format("%s\nAnswer: %s\n", getQuestion(), setAnswer2());
    }

    public String toDataString() {
        return String.format("%s %s", getQuestion().replaceAll(" ", "_"), getAnswer().replaceAll(" ", "_"));
    }
}

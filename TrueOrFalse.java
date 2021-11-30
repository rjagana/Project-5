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

    @Override
    public String toString() {
        return String.format("%s\nAnswer: %s\n", getQuestion(), setAnswer());
    }

    public String toDataString() {
        return String.format("%s %s", getQuestion().replaceAll(" ", "_"), getAnswer().replaceAll(" ", "_"));
    }
}

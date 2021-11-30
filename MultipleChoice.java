import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class MultipleChoice extends Question {

    private ArrayList<String> choices = new ArrayList<String>();

    public MultipleChoice() {
        super();
    }

    public MultipleChoice(String question, String answer, ArrayList<String> choices) {
        super(question, answer);
        this.choices = choices;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    //method that sets ABCD for a given multiple choice question
    public ArrayList setChoices() {
        String choicePrompt = "There will be 4 choices \nWhat do you want this choice to say?";
        String choiceError = "Please enter some text for your choice!";
        String whichOption = "Option ";
        String option = "";
        boolean loop = false;
        ArrayList<String> choices = new ArrayList<String>();
        char choice = 0;
        Scanner scan = new Scanner(System.in);
        option = "";
        for (int i = 1; i <= 4; i++) {
            do {
                System.out.println(choicePrompt);
                option = scan.nextLine();
                if (option.isBlank()) {
                    System.out.println(choiceError);
                    loop = true;
                }
            } while (loop);
            switch (i) {
                case 1:
                    choice = 'A';
                    break;
                case 2:
                    choice = 'B';
                    break;
                case 3:
                    choice = 'C';
                    break;
                case 4:
                    choice = 'D';
                    break;
            }
            System.out.println(whichOption + choice + ": " + option);
            choices.add(option);
        }
        return choices;
    }

    public String toString() {
        String A = "";
        String B = "";
        String C = "";
        String D = "";
        A = choices.get(0);
        B = choices.get(1);
        C = choices.get(2);
        D = choices.get(3);

        return String.format("%s\nAnswer: %s\n" +
                "A)%s\nB)%s\nC)%s\nD)%s", getQuestion(), getAnswer(), A, B, C, D);
    }

    public String toDataString() {
        String A = "";
        String B = "";
        String C = "";
        String D = "";
        A = choices.get(0);
        B = choices.get(1);
        C = choices.get(2);
        D = choices.get(3);
        return String.format("%s %s " +
                        "%s %s %s %s ", getQuestion().replaceAll(" ", "_"), getAnswer().replaceAll(" ", "_"),
                A.replaceAll(" ", "_"), B.replaceAll(" ", "_"), C.replaceAll(" ", "_"), D.replaceAll(" ", "_"));
    }
}

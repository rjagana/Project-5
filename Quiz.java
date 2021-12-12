
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Quiz {
    private String title;
    private int numOfQuestions;
    private String questionType;
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<String> titlesList = new ArrayList<>();
    private String userQuizTitle = "";




    public Quiz(String title, int numOfQuestions, String questionType,
                ArrayList<Question> questions) {
        this.title = title;
        this.numOfQuestions = numOfQuestions;
        this.questionType = questionType;
        this.questions = questions;
    }

    public Quiz() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(int numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    //method that makes a multiple choice quiz given an amount of questions
   public ArrayList<Question> multipleChoiceQuestionMaker(int length) {
        //variables used in method
        Scanner scn = new Scanner(System.in);
        ArrayList<Question> questionList = new ArrayList<>();
        String questionTitle = "";
        ArrayList<String> choices = new ArrayList<>();
        String answer;
        boolean loop = false;

        //allows a user to make a list of multiple choice questions
        int questionNumber = 0;
        for (int i = 0; i < length; i++) {
            questionNumber = i + 1;
            System.out.println("Please enter the question for question " + questionNumber);
            questionTitle = scn.nextLine();
            MultipleChoice temp = new MultipleChoice();
            choices = temp.setChoices();
            do {
                System.out.println("Please enter the letter answer for this question");
                loop = false;
                answer = scn.nextLine();
                if (!Objects.equals(answer, "A") && !Objects.equals(answer, "B") && !Objects.equals(answer, "C") && !Objects.equals(answer, "D")) {
                    System.out.println("Please input correct parameter");
                    loop = true;
                }
            } while (loop);
            Question newQuestion = new MultipleChoice(questionTitle, answer, choices);
            questionList.add(newQuestion);
        }
        return questionList;
    }

    public ArrayList<Question> multipleChoiceQuestionMaker2(int length) {
        //variables used in method
        Scanner scn = new Scanner(System.in);
        ArrayList<Question> questionList = new ArrayList<>();
        String questionTitle = "";
        ArrayList<String> choices = new ArrayList<>();
        boolean loop = false;
        String answer;

        //allows a user to make a list of multiple choice questions
        int questionNumber = 0;
        for (int i = 0; i < length; i++) {
            questionNumber = i + 1;
            questionTitle = JOptionPane.showInputDialog(null, "Please enter the question for question " + questionNumber,
                    "Quiz Application", JOptionPane.QUESTION_MESSAGE);
            MultipleChoice temp = new MultipleChoice();
            choices = temp.setChoices2();
            do {
                String[] options = {"D", "C", "B", "A"};
                 answer = String.valueOf(JOptionPane.showOptionDialog(null, "Please select the letter answer for this question", "Quiz Applciation",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[1]));
                loop = false;
            } while (loop);
            Question newQuestion = new MultipleChoice(questionTitle, answer, choices);
            questionList.add(newQuestion);
        }
        return questionList;
    }

    //method that makes a Fill In The Blank quiz given an amount of questions
    public ArrayList<Question> fillInTheBlankQuestionMaker(int length) {
        //variables used in method
        Scanner scn = new Scanner(System.in);
        ArrayList<Question> questionList = new ArrayList<Question>();
        String questionTitle = "";
        ArrayList<String> choices = new ArrayList<String>();
        String answer = "";
        boolean loop = false;

        //allows a user to make a list of FIB questions
        int questionNumber = 0;
        for (int i = 0; i < length; i++) {
            questionNumber = i + 1;
            System.out.println("Please enter the question for question " + questionNumber);
            questionTitle = scn.nextLine();
            FillInTheBlank temp = new FillInTheBlank();
            answer = temp.setAnswer2();


            Question newQuestion = new FillInTheBlank(questionTitle, answer);
            questionList.add(newQuestion);
        }
        return questionList;
    }

    public ArrayList<Question> fillInTheBlankQuestionMaker2(int length) {
        //variables used in method
        Scanner scn = new Scanner(System.in);
        ArrayList<Question> questionList = new ArrayList<Question>();
        String questionTitle = "";
        ArrayList<String> choices = new ArrayList<String>();
        String answer = "";
        boolean loop = false;

        //allows a user to make a list of FIB questions
        int questionNumber = 0;
        for (int i = 0; i < length; i++) {
            questionNumber = i + 1;
            questionTitle = JOptionPane.showInputDialog(null, "Please enter the question for question " + questionNumber,
                    "Quiz Application", JOptionPane.QUESTION_MESSAGE);



            Question newQuestion = new FillInTheBlank(questionTitle, answer);
            questionList.add(newQuestion);
        }
        return questionList;
    }

    //method that makes a True or False quiz given an amount of questions
    public ArrayList<Question> trueOrFalseQuestionMaker(int length) {
        //variables used in method
        Scanner scn = new Scanner(System.in);
        ArrayList<Question> questionList = new ArrayList<Question>();
        String questionTitle = "";
        String answer = "";
        boolean loop = false;

        //allows a user to make a list of multiple choice questions
        int questionNumber = 0;
        for (int i = 0; i < length; i++) {
            questionNumber = i + 1;
            System.out.println("Please enter the question for question " + questionNumber);
            questionTitle = scn.nextLine();

            Question newQuestion = new TrueOrFalse(questionTitle, answer);
            questionList.add(newQuestion);
        }
        return questionList;
    }

    public ArrayList<Question> trueOrFalseQuestionMaker2(int length) {
        //variables used in method
        Scanner scn = new Scanner(System.in);
        ArrayList<Question> questionList = new ArrayList<Question>();
        String questionTitle = "";
        String answer = "";
        boolean loop = false;

        //allows a user to make a list of multiple choice questions
        int questionNumber = 0;
        for (int i = 0; i < length; i++) {
            questionNumber = i + 1;
            questionTitle = JOptionPane.showInputDialog(null, "Please enter the question for question " + questionNumber,
                    "Quiz Application", JOptionPane.QUESTION_MESSAGE);

            Question newQuestion = new TrueOrFalse(questionTitle, answer);
            questionList.add(newQuestion);
        }
        return questionList;
    }


    public Quiz makeQuiz() throws FileNotFoundException {
        //scanner
        Scanner scn = new Scanner(System.in);

        //variables used in method
        int userQuizType = 0;
        int userQuizLength = 0;
        ArrayList<Question> questionList = new ArrayList<Question>();
        userQuizTitle = "";
        String userQuizRandom = "";
        String userQuizConfirm = "";

        String userQuizTypeMC = "Multiple Choice";
        String userQuizTypeFIB = "Fill In The Blank";
        String userQuizTypeTF = "True Or False";
        String userQuizTypeString = "";

        //variables for the messages displayed to user
        String makeQuizType = "What type of quiz would you like to make?\n" +
                "1. Multiple Choice\n" +
                "2. Fill In The Blank\n" +
                "3. True Or False\n" +
                "Choose the number associated with your choice: ";

        String makeQuizLength = "How many questions would you like this Quiz to be?\n" +
                "Provide the number: ";

        String makeQuizTitle = "What would you like the title of this quiz to be?\n" +
                "Provide the Title: ";

        String makeQuizRandom = "Would you like the question order to be random?\n\n" +
                "YES\n" +
                "or\n" +
                "NO\n\n" +
                "Provide response:";

        String makeQuizConfirm = "Are you happy with your choices?\n\n" +
                "YES\n" +
                "or\n" +
                "NO\n\n" +
                "Provide response:";

        //STARTS HERE
        System.out.println("Welcome to the quiz maker!");
        //Teacher chooses:
        //TITLE
        //QUESTION TYPE (Multiple Choice, Fill in the blank, or Dropdown menu)
        //RANDOM (if they want it to be a randomized quiz or not)
        //# OF QUESTIONS

        //at the end it lists them their choices and asks if they want to change their answers
        //if they say NO then they move on
        //if they YES they are brought back here to this DO WHILE loop
        String makeQuizOverview;
        do {

            //choose question type by input of # from menu
            System.out.println(makeQuizType);
            boolean quizTypeCheck = true;
            do {
                try {
                    userQuizType = scn.nextInt();
                    scn.nextLine();
                    quizTypeCheck = false;
                    if (userQuizType > 3 || userQuizType < 1) {
                        quizTypeCheck = true;
                        System.out.println("Error. Enter valid input!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Enter a valid input!");
                    quizTypeCheck = true;
                    scn.next();
                }
            } while(quizTypeCheck);


            //based on number chosen set question type in String ~userQuizTypeString~
            switch (userQuizType) {
                case 1:
                    userQuizTypeString = userQuizTypeMC;
                    break;
                case 2:
                    userQuizTypeString = userQuizTypeFIB;
                    break;
                case 3:
                    userQuizTypeString = userQuizTypeTF;
                    break;
            }


            //choose quiz title
            do {
                System.out.println(makeQuizTitle);
                userQuizTitle = scn.nextLine();
            } while (checkSameTitle(userQuizTitle));

            //choose quiz length by # input
            System.out.println(makeQuizLength);
            boolean quizLengthCheck = true;
            do {
                try {
                    userQuizLength = scn.nextInt();
                    scn.nextLine();
                    quizLengthCheck = false;
                } catch (InputMismatchException e) {
                    System.out.println("Enter a valid input!");
                    quizLengthCheck = true;
                    scn.next();
                }
            } while(quizLengthCheck);



            //choose to randomize quiz by saying
            //YES or NO
            System.out.println(makeQuizRandom);
            boolean quizRandomCheck = true;
            do {
                userQuizRandom = scn.nextLine();
                if (userQuizRandom.equalsIgnoreCase("yes") ||
                        userQuizRandom.equalsIgnoreCase("no")) {
                    quizRandomCheck = false;
                } else {
                    System.out.println("Enter a valid choice!");
                    quizRandomCheck = true;
                }

            } while (quizRandomCheck);



            //String made based on previous input
            makeQuizOverview = "Here's your quiz overview:\n\n" +
                    "Title: " + userQuizTitle + "\n" +
                    "# Of Questions: " + userQuizLength + "\n" +
                    "Question Type: " + userQuizTypeString + "\n" +
                    "Random: " + userQuizRandom;


            //lists user their choices
            //then asks them if they want to change it
            System.out.println(makeQuizOverview);


            System.out.println("");
            System.out.println("------------------------------");
            System.out.println("");
            System.out.println(makeQuizConfirm);
            boolean quizConfirmCheck = true;
            do{
                userQuizConfirm = scn.nextLine();
                if (userQuizConfirm.equalsIgnoreCase("yes") ||
                        userQuizConfirm.equalsIgnoreCase("no")) {
                    quizConfirmCheck = false;
                } else {
                    System.out.println("Enter a valid choice!");
                    quizConfirmCheck = true;
                }
            } while (quizConfirmCheck);

        } while (userQuizConfirm.equals("NO"));

        //checks if quiz type is multiple choice
        if (userQuizTypeString.equals(userQuizTypeMC)) {
            questionList = multipleChoiceQuestionMaker(userQuizLength);
        }

        if (userQuizTypeString.equals(userQuizTypeFIB)) {
            questionList = fillInTheBlankQuestionMaker(userQuizLength);
        }

        if (userQuizTypeString.equals(userQuizTypeTF)) {
            questionList = trueOrFalseQuestionMaker(userQuizLength);
        }

        boolean quizRandom;
        if (userQuizRandom.equalsIgnoreCase("yes")) {
            Collections.shuffle(questionList);
        }
        Quiz quiz = new Quiz(userQuizTitle, userQuizLength, userQuizTypeString, questionList);
        return quiz;
    }

   public Quiz makeQuiz2() throws FileNotFoundException {
        //scanner
        Scanner scn = new Scanner(System.in);

        //variables used in method
        int userQuizType = 0;
        int userQuizLength = 0;
        ArrayList<Question> questionList = new ArrayList<Question>();
        userQuizTitle = "";
        int rando = 0;
        String userQuizRandom = "";
        String userQuizConfirm = "";

        String userQuizTypeMC = "Multiple Choice";
        String userQuizTypeFIB = "Fill In The Blank";
        String userQuizTypeTF = "True Or False";
        String userQuizTypeString = "";

        //variables for the messages displayed to user
        String makeQuizType = "What type of quiz would you like to make?";

        String makeQuizLength = "How many questions would you like this Quiz to be?";

        String makeQuizTitle = "What would you like the title of this quiz to be?";

        String makeQuizRandom = "Would you like the question order to be random?";

        String makeQuizConfirm = "Are you happy with your choices?";

        //STARTS HERE
        JOptionPane.showMessageDialog(null, "Welcome to the quiz maker!", "Quiz Application",
                JOptionPane.INFORMATION_MESSAGE);
        //Teacher chooses:
        //TITLE
        //QUESTION TYPE (Multiple Choice, Fill in the blank, or Dropdown menu)
        //RANDOM (if they want it to be a randomized quiz or not)
        //# OF QUESTIONS

        //at the end it lists them their choices and asks if they want to change their answers
        //if they say NO then they move on
        //if they YES they are brought back here to this DO WHILE loop
        String makeQuizOverview;
        do {

            //choose question type by input of # from menu
            String[] options = {"True or False", "Fill in the Blank", "Multiple Choice"};
            int quizType = JOptionPane.showOptionDialog(null, "What type of quiz would you like to make?", "Quiz Application",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);

            if (quizType == 2) {
                userQuizTypeString = userQuizTypeMC;
            } else if (quizType == 1) {
                userQuizTypeString = userQuizTypeFIB;
            } else if (quizType == 0) {
                userQuizTypeString = userQuizTypeTF;
            }
            //based on number chosen set question type in String ~userQuizTypeString~
          /*  switch (userQuizType) {
                case 1:
                    userQuizTypeString = userQuizTypeMC;
                    break;
                case 2:
                    userQuizTypeString = userQuizTypeFIB;
                    break;
                case 3:
                    userQuizTypeString = userQuizTypeTF;
                    break;
            }*/


            //choose quiz title
            do {
                userQuizTitle = JOptionPane.showInputDialog(null, makeQuizTitle,"Quiz Application",
                        JOptionPane.QUESTION_MESSAGE);
            } while (checkSameTitle(userQuizTitle));

            //choose quiz length by # input

            boolean quizLengthCheck = true;
            do {
                try {
                    userQuizLength = Integer.parseInt(JOptionPane.showInputDialog(null, makeQuizLength,"Quiz Application",
                            JOptionPane.QUESTION_MESSAGE));
                    quizLengthCheck = false;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Enter a valid number!", "Quiz Application",
                            JOptionPane.INFORMATION_MESSAGE);
                    quizLengthCheck = true;
                }
            } while (quizLengthCheck);

            //choose to randomize quiz by saying
            //YES or NO
            rando = (JOptionPane.showConfirmDialog(null, makeQuizRandom, "Quiz Application", JOptionPane.YES_NO_OPTION));
            boolean quizRandomCheck = true;

            if (rando == 0) {
                userQuizRandom = "YES";
            } else {
                userQuizRandom = "NO";
            }

            //String made based on previous input
            makeQuizOverview = "Here's your quiz overview:\n\n" +
                    "Title: " + userQuizTitle + "\n" +
                    "# Of Questions: " + userQuizLength + "\n" +
                    "Question Type: " + userQuizTypeString + "\n" +
                    "Random: " + userQuizRandom;


            //lists user their choices
            //then asks them if they want to change it
            JOptionPane.showMessageDialog(null, makeQuizOverview, "Quiz Application", JOptionPane.INFORMATION_MESSAGE);


            userQuizConfirm = String.valueOf(JOptionPane.showConfirmDialog(null, makeQuizConfirm, "Quiz Application", JOptionPane.YES_NO_OPTION));
            boolean quizConfirmCheck = true;

        } while (userQuizConfirm.equals("NO"));

        //checks if quiz type is multiple choice
        if (userQuizTypeString.equals(userQuizTypeMC)) {
            questionList = multipleChoiceQuestionMaker2(userQuizLength);
        }

        if (userQuizTypeString.equals(userQuizTypeFIB)) {
            questionList = fillInTheBlankQuestionMaker2(userQuizLength);
        }

        if (userQuizTypeString.equals(userQuizTypeTF)) {
            questionList = trueOrFalseQuestionMaker2(userQuizLength);
        }

        boolean quizRandom;
        if (userQuizRandom.equalsIgnoreCase("YES")) {
            Collections.shuffle(questionList);
        }
        Quiz quiz = new Quiz(userQuizTitle, userQuizLength, userQuizTypeString, questionList);
        writeFile(quiz.toString());
        writeQuizTitles(userQuizTitle);
        writeQuizData(quiz.toDataString());
        return quiz;
    }

    public boolean checkSameTitle(String userQuizTitle) throws FileNotFoundException {
        FileReader fr = new FileReader("QuizTitles.txt");
        BufferedReader bufferedReader = new BufferedReader(fr);
        String titles = "";
        try {
            String line = bufferedReader.readLine();
            while (line!= null) {
                titlesList.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < titlesList.size(); i++) {
            if (userQuizTitle.equalsIgnoreCase(titlesList.get(i))) {
                JOptionPane.showMessageDialog(null, "Sorry, this quiz title is already taken! Please choose another title!",
                        "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }
        return false;
    }


    public static void writeFile(String quiz)
            throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("QuizList.txt", true);
        PrintWriter pw = new PrintWriter(fos);
        pw.print(quiz);
        pw.println();
        pw.close();
    }

    public static void writeQuizData(String quiz)
            throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("QuizData.txt", true);
        PrintWriter pw = new PrintWriter(fos);
        pw.println(quiz);
        pw.close();
    }

    public void writeQuizTitles(String title) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("QuizTitles.txt", true);
        PrintWriter pw = new PrintWriter(fos);
        pw.print(title);
        pw.println();
        pw.close();
    }

    @Override
    public String toString() {
        String x = "";
        for (int i = 0; i < numOfQuestions; i++) {
            x = x + String.format("Question %d:\n", i + 1) + questions.get(i).toString() + "\n";
        }
        return String.format("Title: %s\nNumber of Questions: %d\n%s\n%s", title,
                numOfQuestions, questionType, x);
    }

    public String toDataString(){
        String x = "";
        for (int i = 0; i < numOfQuestions; i++) {
            x = x + questions.get(i).toDataString();
        }
        return String.format("%s %d %s %s", title.replaceAll(" ", "_"),
                numOfQuestions, questionType.replaceAll(" ", "_"), x);
    }
}

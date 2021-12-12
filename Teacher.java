import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Teacher extends User {

    private ArrayList<Quiz> quizList = new ArrayList<Quiz>();
    private ArrayList<String> titlesList = new ArrayList<String>();
    private ArrayList<Student> studentList = new ArrayList<Student>();
    private ArrayList<String> usernameList = new ArrayList<String>();
    private boolean deleteCheck;

    public boolean isDeleteCheck() {
        return deleteCheck;
    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }

    public Teacher() {
        super();
    }

    public ArrayList<String> getUsernameList() {
        return usernameList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public Teacher(String username, String password) {
        super(username, password, "teacher");
    }


    public ArrayList<Quiz> readQuizData() throws IOException {
        FileReader fr = new FileReader("QuizData.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        ArrayList<Question> questionList = new ArrayList<Question>();
        while (line != null) {
            String[] x = line.split(" ");
            for (int i = 0; i < Integer.parseInt(x[1]); i++) {
                if (x[2].replaceAll("_", " ").equals("True or False")) {
                    int times = 2 * i;
                    Question temp = new TrueOrFalse(x[3 + times].replaceAll("_", " "), x[4 + times]);
                    questionList.add(temp);
                } else if (x[2].replaceAll("_", " ").equals("Multiple Choice")) {
                    int times = 6 * i;
                    ArrayList<String> choices = new ArrayList<>();
                    choices.add(x[5 + times].replaceAll("_", " "));
                    choices.add(x[6 + times].replaceAll("_", " "));
                    choices.add(x[7 + times].replaceAll("_", " "));
                    choices.add(x[8 + times].replaceAll("_", " "));
                    Question temp = new MultipleChoice(x[3 + times].replaceAll("_", " "), x[4 + times], choices);
                    questionList.add(temp);
                } else if (x[2].replaceAll("_", " ").equals("Fill In The Blank")) {
                    int times = 2 * i;
                    Question temp = new FillInTheBlank(x[3 + times].replaceAll("_", " "), x[4 + times].replaceAll("_", " "));
                    questionList.add(temp);
                }
            }
            Quiz y = new Quiz(x[0].replaceAll("_", " "), Integer.parseInt(x[1]), x[2].replaceAll("_", " "), questionList);
            quizList.add(y);
            questionList = new ArrayList<>();
            line = br.readLine();
        }
        return quizList;
    }

    public boolean checkIfQuizExists(String userQuizTitle) throws FileNotFoundException {
        FileReader fr = new FileReader("QuizTitles.txt");
        BufferedReader bufferedReader = new BufferedReader(fr);
        String titles = "";
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                titlesList.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < titlesList.size(); i++) {
            if (userQuizTitle.equalsIgnoreCase(titlesList.get(i))) {
                return true;
            }
        }
        System.out.println("Error: That quiz doesn't exist, please enter a valid quiz");
        return false;
    }

    public void gradeQuiz(Quiz quiz, String student) throws IOException {

        int total = quiz.getQuestions().size();
        ArrayList<String> choiceList = new ArrayList<>();
        int correct = 0;
        FileReader fr = new FileReader(student + "_" + quiz.getTitle() + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        String answer = "";
        ArrayList<String> answers = new ArrayList<>();
        int index = 0;
        int indexOfQuiz = 0;
        Quiz quizGraded;
        for (Quiz x : quizList) {
            if (x.getTitle().equals(quiz.getTitle())) {
                quizGraded = x;
                break;
            }
            indexOfQuiz++;
        }

        while (line != null) {
            if (quizList.get(indexOfQuiz).getQuestionType().equals("Multiple Choice")) {
                ArrayList<Question> tempList = quizList.get(indexOfQuiz).getQuestions();
                ArrayList<MultipleChoice> questionList = new ArrayList<>();
                for (Question x : tempList) {
                    MultipleChoice temp = (MultipleChoice) x;
                    questionList.add(temp);
                }

                switch (line) {
                    case "A":
                        answer = questionList.get(index).getChoices().get(0);
                    case "B":
                        answer = questionList.get(index).getChoices().get(1);
                    case "C":
                        answer = questionList.get(index).getChoices().get(2);
                    case "D":
                        answer = questionList.get(index).getChoices().get(3);
                    default:
                        answer = "";
                }
            } else if (quiz.getQuestionType().equals("True Or False")) {
                ArrayList<Question> questionList = quiz.getQuestions();
                answer = questionList.get(index).getAnswer();
            } else if (quiz.getQuestionType().equals("Fill In The Blank")) {
                ArrayList<Question> questionList = quiz.getQuestions();

                System.out.println(questionList);

                answer = questionList.get(index).getAnswer();
            }
            answers.add(answer);
            line = br.readLine();
        }
        int i = 0;
        String answerChoice = "";
        for (Question question : quiz.getQuestions()) {
            if (question.getAnswer().equals(answers.get(i))) {
                correct++;
            }
            i++;
        }
        System.out.println(correct + "/" + total);
    }

    public boolean showQuizTitles() throws IOException {
        FileReader fr1 = new FileReader("QuizTitles.txt");
        BufferedReader bufferedReader = new BufferedReader(fr1);
        String titles = "";
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
        if (!titlesList.isEmpty()) {
            System.out.println(titlesList);
            return true;
        } else {
            return false;
        }
    }

    /*public void createQuiz() throws FileNotFoundException {
        Quiz quiz = new Quiz();
        quiz.makeQuiz();
        for (int i = 0; i < quizList.size(); i++) {
            if (quiz.getTitle().equals(quizList.get(i).getTitle())) {
                System.out.println("This quiz already exists, Please try making a new one!");
                return;
            }
        }
        quizList.add(quiz);
        System.out.println("Your quiz has been made!");
    }*/

    public Quiz createQuiz() throws FileNotFoundException {
        Quiz quiz = new Quiz();
        quiz.makeQuiz2();
        for (int i = 0; i < quizList.size(); i++) {
            if (quiz.getTitle().equals(quizList.get(i).getTitle())) {
                JOptionPane.showMessageDialog(null, "This quiz already exists, Please try making a new one!", "Quiz Application",
                        JOptionPane.INFORMATION_MESSAGE);
                return quiz;
            }
        }
        quizList.add(quiz);
        JOptionPane.showMessageDialog(null, "Your quiz has been made!", "Quiz Application",
                JOptionPane.INFORMATION_MESSAGE);
        return quiz;
    }


    public boolean editQuiz(String title) throws IOException {
        int index;
        boolean check = false;
        //showQuizTitles();
        //createQuiz();
        for (int i = 0; i < titlesList.size(); i++) {
            if (title.equals(titlesList.get(i))) {
                index = i;
                Quiz editedQuiz = new Quiz();
                //editedQuiz.makeQuiz();
                createQuiz();
                titlesList.add(index, editedQuiz.toString());
                check = true;
                return true;
            }
        }
        if (check == false) {
            System.out.println("This quiz does not exist. Choose again!");
        }
        return false;
    }

    public ArrayList<String> getTitlesList() {
        return titlesList;
    }

    public boolean deleteQuiz(String title) throws IOException {
        int index = -1;
        showQuizTitles();
        boolean check = true;

        for (int i = 0; i < titlesList.size(); i++) {
            if (title.equals(titlesList.get(i))) {
                index = i;
                check = false;
            }
        }
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "This quiz does not exist! Choose again!", "Quiz Application",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            titlesList.remove(index);
            readQuizData();
            quizList.remove(index);
            JOptionPane.showMessageDialog(null, "The quiz has been removed!", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public void readUserFile() throws IOException {
        FileReader fr1 = new FileReader("userlist.txt");
        BufferedReader bufferedReader = new BufferedReader(fr1);

        String[] array = new String[3];

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                array = line.split(" ");
                if (array[2].equalsIgnoreCase("student")) {
                    studentList.add(new Student(array[0], array[1]));
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferedReader.close();

        for (int i = 0; i < studentList.size(); i++) {
            String username = studentList.get(i).getUsername();
            usernameList.add(username);
        }
    }

    public static String deleteFromQuizList(String quizTitle) throws FileNotFoundException {
        FileReader fr1 = new FileReader("QuizList.txt");
        BufferedReader bfr1 = new BufferedReader(fr1);
        String quiz = "";

        try {
            String line = bfr1.readLine();
            while (line != null) {
                //  line = bufferedReader.readLine();
                if (line.equalsIgnoreCase("Title: " + quizTitle)) {
                    quiz = quiz + "\n" + line;
                    line = bfr1.readLine();
                    //x = line.substring(21);
                    while (line != null && !line.contains("Title")) {
                        quiz = quiz + "\n" + line;
                        line = bfr1.readLine();
                    }
                }
                line = bfr1.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quiz;
    }

    public static void removeLineFromFile(String lineToRemove) {
        try {
            File inFile = new File("QuizList.txt");
            File tempFile = new File(inFile.getAbsolutePath() + ".txt");

            BufferedReader br = new BufferedReader(new FileReader("QuizList.txt"));
            PrintWriter pw = new PrintWriter(new FileOutputStream(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}

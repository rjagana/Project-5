import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    private final static String menuOptions = "Would you like to \n" +
            "1. Create a Quiz\n" +
            "2. Edit a Quiz\n" +
            "3. Delete a Quiz\n" +
            "4. Grade a Quiz\n" +
            "5. Logout\n" +
            "6. Edit Account\n" +
            "7. Delete Account\n" +
            "Choose the number associated with your choice: ";
    private static final String studentOptions = "Would you like to \n" +
            "1. Take a quiz\n" +
            "2. Logout\n" +
            "3. Edit Account\n" +
            "4. Delete Account\n" +
            "Choose the number associated without your choice: ";
    private static boolean check;
    private static String quizTitleChoice;
    private static String quizTitle;


    public static void main(String[] args) throws IOException {
        //Allows a user to login and identifies whether they are a teacher or a student
        Scanner scanner = new Scanner(System.in);
        boolean check2 = false;
        boolean check3 = false;
        boolean check4 = false;
        boolean check5 = false;
        boolean check6 = false;
        boolean checking = false;
        ArrayList<String> newUsernameList = new ArrayList<String>();
        Student student2 = null;
        int count = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        boolean deleteCheck = false;
        do {
            System.out.println("Welcome to the Quiz Application! Are you a teacher or a student?");
            String userType = scanner.nextLine();


            //User is a Teacher
            if (userType.equalsIgnoreCase("teacher")) {
                check3 = true;
                check2 = false;
                Teacher teacher = new Teacher();
                teacher.runUser("teacher");
                boolean checkTeacherChoice = true;
                do {
                    System.out.println(menuOptions);
                    int teacherChoice = 999;
                    do {
                        try {
                            teacherChoice = scanner.nextInt();
                            scanner.nextLine();
                            checkTeacherChoice = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Error! Please enter valid input!");
                            checkTeacherChoice = true;
                            scanner.next();
                        }
                    } while (checkTeacherChoice);
                    if (teacherChoice == 1) {
                        teacher.createQuiz();
                        //System.out.println(menuOptions);
                        TimeStamp.printTimeStamp();
                        check = true;
                    } else if (teacherChoice == 2) {
                        while (!check6) {
                            System.out.println("What is the name of the quiz you would like to edit?");
                            teacher.showQuizTitles();
                            if (count2 == 0) {
                                scanner.nextLine();
                                //scanner.nextLine();

                                quizTitle = scanner.nextLine();
                                check6 = true;

                                if (!teacher.editQuiz(quizTitle)) {
                                    check6 = false;
                                    //teacher.editQuiz(quizTitle);
                                }
                                count2++;
                            } else {
                                quizTitle = scanner.nextLine();
                                check6 = true;

                                if (!teacher.editQuiz(quizTitle)) {
                                    check6 = false;
                                    //teacher.editQuiz(quizTitle);
                                }
                            }

                        }
                    } else if (teacherChoice == 3) {
                        System.out.println("What is the name of the quiz you would like to delete?");
                        do {
                            teacher.showQuizTitles();
                            teacher.getTitlesList();
                            if (count3 == 0) {
                                scanner.nextLine();
                                String quizTitle = scanner.nextLine();
                                deleteCheck = teacher.deleteQuiz(quizTitle);
                                teacher.deleteFromQuizList(quizTitle);
                                count3++;
                            } else {
                                String quizTitle = scanner.nextLine();
                                deleteCheck = teacher.deleteQuiz(quizTitle);
                                teacher.deleteFromQuizList(quizTitle);
                            }
                        } while (deleteCheck == true);
                        check = true;
                    } else if (teacherChoice == 4) {
                        boolean quizChecker;
                        String quizChosen = "";
                        boolean quizzesExist = teacher.showQuizTitles();
                        if (quizzesExist) {
                            do {
                                System.out.println("Please choose which quiz you would like to grade?");
                                quizChosen = scanner.nextLine();
                                System.out.println(quizChosen);

                                quizChecker = teacher.checkIfQuizExists(quizChosen);
                            } while (!quizChecker);
                            teacher.readUserFile();
                            newUsernameList = teacher.getUsernameList();
                            if (!newUsernameList.isEmpty()) {
                                System.out.println(newUsernameList);
                                String chosenUsername = scanner.nextLine();
                                System.out.println("Which student's quiz would you like to grade?");
                                do {
                                    int i = 0;
                                    for (String x : newUsernameList) {
                                        if (chosenUsername.equals(newUsernameList.get(i))) {
                                            System.out.println(newUsernameList.get(i) + "'s quiz will be graded!");
                                            student2 = teacher.getStudentList().get(i);
                                            System.out.println(student2);
                                            count = 1;
                                            break;
                                        } else
                                            i++;
                                    }
                                    if (count > 0) {
                                        teacher.readQuizData();
                                        for (int j = 0; j < teacher.getQuizList().size(); j++) {
                                            if (teacher.getQuizList().get(j).getTitle().equals(quizChosen)) {
                                                teacher.gradeQuiz(teacher.getQuizList().get(j),
                                                        student2.getUsername());
                                            }
                                        }
                                        check5 = false;
                                    } else {
                                        System.out.println("This student does not exist! Choose again!");
                                        scanner.next();
                                        check5 = true;
                                    }
                                    check = true;
                                } while (check5);
                            } else {
                                System.out.println("Error. No students have taken this quiz.");
                            }
                        } else {
                            System.out.println("Error. No quizzes have been created yet.");
                            check = true;
                        }

                    } else if (teacherChoice == 5) {
                        System.out.println("Thank you for using the quiz application!");
                        check = false;
                    } else if (teacherChoice == 6) {
                        System.out.println("Enter your new username: ");
                        scanner.nextLine();
                        String newUsername = scanner.nextLine();
                        System.out.println("Enter your new password: ");
                        String newPassword = scanner.nextLine();
                        String tempUser = teacher.editUser(teacher.getUsername(), newUsername, newPassword);
                        teacher.setUsername(tempUser);
                        System.out.println("User edited successfully!");
                        check = true;
                    } else if (teacherChoice == 7) {
                        teacher.deleteUser(teacher.getUsername());
                        System.out.println("User deleted. Thank you for using the quiz application!");
                        check = false;
                    } else {
                        System.out.println("Invalid choice! Choose again!");
                        check = true;
                    }
                } while (check);
            } else if (userType.equalsIgnoreCase("student")) {
                check3 = true;
                //check2 = false;
                Student student = new Student();
                student.runUser("student");

                do {
                    System.out.println(studentOptions);
                    int studentChoice = 999;
                    try {
                        studentChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error. Please make a valid input!");
                        scanner.next();
                    }
                    if (studentChoice == 1) {
                        check = true;
                        do {
                            System.out.println("Choose the title of the quiz you would like to take!");
                            student.chooseQuiz();
                            if (count4 == 0) {
                                scanner.nextLine();
                                quizTitleChoice = scanner.nextLine();
                                checking = student.checkMatch(quizTitleChoice);
                                if (!checking) {
                                    check4 = false;
                                } else {
                                    //student.answerQuiz(quizTitleChoice);
                                    student.writeAnswers(quizTitleChoice, student.answerQuiz(quizTitleChoice));
                                    check4 = true;
                                }
                                TimeStamp.printTimeStamp();
                                count4++;
                                check = true;
                            } else {
                                quizTitleChoice = scanner.nextLine();
                                checking = student.checkMatch(quizTitleChoice);
                                if (!checking) {
                                    check4 = false;
                                } else {
                                    //student.answerQuiz(quizTitleChoice);
                                    student.writeAnswers(quizTitleChoice, student.answerQuiz(quizTitleChoice));
                                    check4 = true;
                                }
                                TimeStamp.printTimeStamp();
                                check = true;
                                count4 = 0;
                            }
                        } while (!check4);
                    } else if (studentChoice == 3) {
                        System.out.println("Enter your new username: ");
                        scanner.nextLine();
                        String newUsername = scanner.nextLine();
                        System.out.println("Enter your new password: ");
                        String newPassword = scanner.nextLine();
                        String tempUser = student.editUser(student.getUsername(), newUsername, newPassword);
                        student.setUsername(tempUser);
                        check = true;
                    } else if (studentChoice == 4) {
                        student.deleteUser(student.getUsername());
                        System.out.println("User deleted. Thank you for using the quiz application!");
                        check = false;
                    } else if (studentChoice == 2) {
                        check = false;
                        System.out.println("Thank you for using the Quiz Application!");
                    } else {
                        check = true;
                        System.out.println("Please enter valid input!");
                    }
                } while (check);

            } else {
                System.out.println("Invalid response! Please enter teacher or student!");
                check = true;
            }

        } while (!check3);

    }
}

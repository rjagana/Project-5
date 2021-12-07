import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//Parent class to teacher and student classes
public class User {
    private String username;
    private String password;
    private String type; //teacher or student
    public String passwordInFile;
    public String userInFile;
    boolean login = false;
    boolean check7 = false;
    boolean check8 = false;
    ArrayList<User> userList = new ArrayList<>();

    public User() {
        this.username = "";
        this.password = "";
    }

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }


    //Main method for testing
  /*  public void runUser(String userType) throws InputMismatchException, IOException {
        initializeUserList();
        Scanner s = new Scanner(System.in);
        FileOutputStream fos = new FileOutputStream("userlist.txt", true);
        PrintWriter pw = new PrintWriter(fos);
        int userStatus = 0;
        boolean check;
        do {
            System.out.println("Are you a new or existing user? (Press 1 for new and 2 for existing)");
            try {
                userStatus = s.nextInt();
                s.nextLine();
                if (userStatus == 1 || userStatus == 2) {
                    check = false;
                } else {
                    System.out.println("Error. Please enter the integer 1 or 2.");
                    check = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error. Please enter a correct option value.");
                s.next();
                check = true;
            }
        } while (check);

        while (!login) {//While there has not been a successful login
            System.out.println("Enter username: ");
            String u = s.nextLine();
            System.out.println("Enter password: ");
            String p = s.nextLine();
            if (userStatus == 1) { //If the user is a new user
                if (validEntry(u)) {
                    if (addUser(u, p, userType) == null) {
                        System.out.println("Username taken. Please enter a different username");
                    } else {
                        User test = addUser(u, p, userType);
                        username = u;
                        password = p;
                        type = userType;
                        userList.add(test);
                        writeUserlistToFile();
                        System.out.println("New user has been created!");
                        login = true;
                        pw.close();
                        initializeUserList();
                    }
                } else {
                    System.out.println("Invalid username format");
                }
            } else { //If the user is an existing user
                boolean found = searchForUser(u);
                if (found) {
                    if (p.equals(passwordInFile)) {//If the password is correct
                        if (userType.equals(type)) {//If the user is the correct type
                            System.out.println("Login successful!");
                            username = u;
                            password = p;
                            login = true;
                        } else {
                            if (userType.equalsIgnoreCase("teacher")) {
                                do {
                                    System.out.println("Error. User is not a teacher." +
                                            " Please press 2 to try again or press 1 to create a new user.");
                                    check7 = false;
                                    try {
                                        userStatus = s.nextInt();
                                        check7 = true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Please enter a valid value!");
                                        check7 = false;
                                    }
                                } while (!check7);
                                s.nextLine();
                            } else {
                                do {
                                    System.out.println("Error. User is not a student." +
                                            " Please press 2 to try again or press 1 to create a new user.");
                                    check8 = false;
                                    try {
                                        userStatus = s.nextInt();
                                        check8 = true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Please enter a valid value!");
                                    }
                                } while (!check8);
                            }
                        }
                    } else {
                        System.out.println(password);
                        System.out.println("Error. Incorrect password." +
                                " Please press 2 to try again or press 1 to create a new user.");
                        userStatus = s.nextInt();
                        s.nextLine();
                    }
                } else {
                    System.out.println("Error. Invalid username. Please press 2 to try again or press 1 to create a new user.");
                    boolean invalidUserCheck;
                    do {
                        try {
                            userStatus = s.nextInt();
                            s.nextLine();
                            invalidUserCheck = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Error. Enter a valid input!");
                            invalidUserCheck = true;
                            s.next();
                        }
                    } while (invalidUserCheck);
                }
            }
        }
    }*/
    //example of makeQuiz from the Quiz Class being implemented
    //Quiz.makeQuiz();

    public void runUser(String userType) throws InputMismatchException, IOException {
        initializeUserList();
        Scanner s = new Scanner(System.in);
        FileOutputStream fos = new FileOutputStream("userlist.txt", true);
        PrintWriter pw = new PrintWriter(fos);
        int userStatus = 0;
        boolean check;
        int existingUser;
        String u;
        String p;

            existingUser = JOptionPane.showConfirmDialog(null, "Are you an existing user?", "Quiz Application",
                    JOptionPane.YES_NO_OPTION);

        while (!login) {//While there has not been a successful login
            u = JOptionPane.showInputDialog(null, "Please enter a username for your account", "Quiz Application", JOptionPane.QUESTION_MESSAGE);
            p = JOptionPane.showInputDialog(null, "Please enter a password for your account", "Quiz Application", JOptionPane.QUESTION_MESSAGE);

            if (existingUser == 1) { //If the user is a new user
                if (validEntry(u)) {
                    if (addUser(u, p, userType) == null) {
                        JOptionPane.showMessageDialog(null,"Username taken. Please enter a different username", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        User test = addUser(u, p, userType);
                        username = u;
                        password = p;
                        type = userType;
                        userList.add(test);
                        writeUserlistToFile();
                        JOptionPane.showMessageDialog(null, "New user has been created!", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                        login = true;
                        pw.close();
                        initializeUserList();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid username format", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                }
            } else { //If the user is an existing user
                boolean found = searchForUser(u);
                if (found) {
                    if (p.equals(passwordInFile)) {//If the password is correct
                        if (userType.equals(type)) {//If the user is the correct type
                            JOptionPane.showMessageDialog(null, "Login successful!", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                            username = u;
                            password = p;
                            login = true;
                        } else {
                            if (userType.equalsIgnoreCase("student")) {
                                    JOptionPane.showMessageDialog(null, "Error. User is not a student.", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                                   login = false;
                            } else {
                                    JOptionPane.showMessageDialog(null, "Error. User is not a teacher.", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                                    login = false;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. Incorrect password.", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. Invalid username.", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        }
    }


    public boolean searchForUser(String username) throws IOException {
        FileReader fileReader = new FileReader("userlist.txt");
        BufferedReader breader = new BufferedReader(fileReader);
        boolean found = false;
        String line = breader.readLine();
        String[] userValues = line.split(" ");
        passwordInFile = userValues[1];
        type = userValues[2];
        while (line != null) {
            userValues = line.split(" ");
            userInFile = userValues[0];
            passwordInFile = userValues[1];
            type = userValues[2];
            if (username.equals(userInFile)) {//If the username is found in the list
                found = true;
                break;
            } else
                line = breader.readLine();
        }
        return found;
    }

    //Getters
    // (no setters for now because the instance variables are final and shouldn't change)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Checks to see if string entered only contains letters or numbers
    public static boolean validEntry(String s) {
        return s.matches("[a-zA-Z0-9]*");
    }

    //Checks to see if the username is already in use
    //If the username is already taken it returns false
    //If the username is not taken it returns true
    public User addUser(String u, String p, String t) throws IOException {
        FileReader fr = new FileReader("userlist.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            if (line.substring(0, line.indexOf(" ")).equals(u)) {
                br.close();
                return null;
            }
            line = br.readLine();
        }
        br.close();
        return new User(u, p, t);
    }

    public void writeUserlistToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("userlist.txt", false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (User user : userList) {
            if (user == userList.get(userList.size() - 1))
                printWriter.print(user.toString());
            else
                printWriter.println(user.toString());
        }
        printWriter.close();
    }

    public void deleteUser(String uname) throws IOException {
        User target = new User();
        for (User user : userList) {
            if (user.username.equals(uname)) {
                target = user;
            }
        }
        userList.remove(target);
        writeUserlistToFile();
    }

    public String editUser(String uname, String newUname, String pword) throws IOException {
        if (!searchForUser(newUname)) {
            for (User user : userList) {
                if (uname.equals(user.getUsername())) {
                    user.username = newUname;
                    user.password = pword;
                }
            }
            writeUserlistToFile();
        } else {
            System.out.println("Error. Username is already taken.");
            newUname = uname;
        }
        return newUname;
    }

    public void initializeUserList() throws FileNotFoundException {
        FileReader fr = new FileReader("userlist.txt");
        BufferedReader bufferedReader = new BufferedReader(fr);
        userList.clear();
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                try {
                    String[] userValues = line.split(" ");
                    userList.add(new User(userValues[0], userValues[1], userValues[2]));
                    line = bufferedReader.readLine();
                } catch (Exception exception) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return this.username + " " + this.password + " " + this.type;
    }
}

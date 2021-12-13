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

    public void runUser(String u, String p, String userType, int existingUser) throws InputMismatchException, IOException {
        String output = "";
        initializeUserList();
        Scanner s = new Scanner(System.in);
        FileOutputStream fos = new FileOutputStream("userlist.txt", true);
        PrintWriter pw = new PrintWriter(fos);
        int userStatus = 0;
        boolean check;

        while (!login) {//While there has not been a successful login

            if (existingUser == 1) { //If the user is a new user
                if (validEntry(u)) {
                    if (addUser(u, p, userType) == null) {
                        output = "taken";
                    } else {
                        User test = addUser(u, p, userType);
                        username = u;
                        password = p;
                        type = userType;
                        userList.add(test);
                        writeUserlistToFile();
                        output = "success";
                        login = true;
                        pw.close();
                        initializeUserList();
                    }
                } else {
                    output = "invalid";
                }
            } else { //If the user is an existing user
                boolean found = searchForUser(u);
                if (found) {
                    if (p.equals(passwordInFile)) {//If the password is correct
                        if (userType.equals(type)) {//If the user is the correct type
                            output = "success";
                            username = u;
                            password = p;
                            login = true;
                        } else {
                            if (userType.equalsIgnoreCase("student")) {
                                output = "invalidtype";
                                login = false;
                            } else {
                                output = "invalidtype";
                                login = false;
                            }
                        }
                    } else {
                        output = "wrong";
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

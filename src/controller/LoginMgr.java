package controller;

import entity.User;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LoginMgr {
    private static final String SEPARATOR = "|";
    private static final String studentFile = "student_details.txt";
    private static final String adminFile = "admin_details.txt";
    final static String SALT = "oops_project";
    private static ArrayList<String> studentLoginDetails;

    static {
        try {
            studentLoginDetails = (ArrayList<String>) readCredentials(studentFile);
        } catch (IOException e) {
            System.out.println("Could not retrieve data");
        }
    }

    private static ArrayList<String> adminLoginDetails;

    static {
        try {
            adminLoginDetails = (ArrayList<String>) readCredentials(adminFile);
        } catch (IOException e) {
            System.out.println("Could not retrieve data");
        }
    }

    public static boolean loginCheck(String userName, String password, String userType) throws IOException {
        ArrayList<String> loginDetails;
        if (userType.toLowerCase().equals("student"))
            loginDetails = studentLoginDetails;
        else if (userType.toLowerCase().equals("admin"))
            loginDetails = adminLoginDetails;
        else {
            return false;
        }
        if (loginDetails.size() == 0) {
            System.out.println("System error... " + userType + "s are not stored in the system");
        }
        for (int i = 0; i < loginDetails.size(); i++) {
            String record = loginDetails.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(record, SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","

            String userNameFile = star.nextToken().trim();    // first token
            String passwordFile = star.nextToken().trim();    // second token
            String saltedPassword = SALT + password;
            String hashedPassword = generateHash(saltedPassword);

            if (userName.equals(userNameFile) && hashedPassword.equals(passwordFile)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Read the contents of the given file.
     *
     * @param fileName
     */
    public static List readCredentials(String fileName) throws IOException {
        List data = new ArrayList();
        try {
            Scanner scanner = new Scanner(new FileInputStream(fileName));
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("System error... file " + fileName + " is not present");

        }
        return data;
    }

    public static void saveUser(String fileName) throws IOException {
        ArrayList<String> loginDetails;
        if (fileName.equals(studentFile))
            loginDetails = studentLoginDetails;
        else if (fileName.equals(adminFile))
            loginDetails = adminLoginDetails;
        else {
            System.out.println("File doesn't exist");
            return;
        }
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        try {
            for (int i = 0; i < loginDetails.size(); i++) {
                printWriter.println(loginDetails.get(i));
            }
        } finally {
            printWriter.close();
        }
    }


    //  Saving the User class for username file
    public static void createUser(String networkName, String password, String userType) throws IOException {
        ArrayList<String> loginDetails;
        String fileName;
        if (userType.equals("student")) {
            loginDetails = studentLoginDetails;
            fileName = studentFile;
        } else if (userType.equals("admin")) {
            loginDetails = adminLoginDetails;
            fileName = adminFile;
        } else {
            System.out.println("Invalid type... cannot create");
            return;
        }

        boolean toWrite = true;

        String newRecord = networkName + SEPARATOR + generateHash(SALT + password);

        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        for (int i = 0; i < loginDetails.size(); i++) {
            printWriter.println(loginDetails.get(i));
            StringTokenizer star = new StringTokenizer(loginDetails.get(i), SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
            String userNameFile = star.nextToken().trim();
            if (userNameFile.equals(networkName)){
                toWrite = false;
            }
        }
        if (toWrite)
            printWriter.print(newRecord);
        else System.out.println("Student already exists");
        printWriter.close();
    }

    public static void removeUser(String userName, String userType) throws IOException {
        ArrayList<String> loginDetails;
        String fileName;
        if (userType.toLowerCase().equals("student")) {
            loginDetails = studentLoginDetails;
            fileName = studentFile;
        } else if (userType.toLowerCase().equals("admin")) {
            loginDetails = adminLoginDetails;
            fileName = adminFile;
        } else {
            System.out.println("Invalid type... cannot create");
            return;
        }
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        try {
            for (int i = 0; i < loginDetails.size(); i++) {
                String record = loginDetails.get(i);
                StringTokenizer star = new StringTokenizer(record, SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
                String userNameFile = star.nextToken().trim();
                if (!userNameFile.equals(userName))
                    printWriter.println(loginDetails.get(i));
                else {
                    System.out.println("Deleting: " + userName + " from system");
                }
            }
        } finally {
            printWriter.close();
        }

    }

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; idx++) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (
                NoSuchAlgorithmException e) {
            System.out.println("System error");
        }
        return hash.toString();
    }

}
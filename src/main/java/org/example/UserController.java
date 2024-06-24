package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private static final String FILE_PATH = "users.txt";

    public void saveUser(User user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.toString());
            writer.newLine();
        }
    }

    public List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(User.fromString(line));
            }
        }
        return users;
    }
}

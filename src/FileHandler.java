import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;
import java.io.File;
import java.io.IOException;

public class FileHandler {

    private static final String FILE_NAME = "data.txt";

    // Method to check if the account is loaded in memory, if not, load from file
    public void loadAccountIfNotLoaded(int accountId, Map<Integer, Account> accountMap) {
        if (!accountMap.containsKey(accountId)) {
            // If the account is not loaded, read it from the file
            Account account = findAccountById(accountId);
            if (account != null) {
                accountMap.put(accountId, account);  // Add the loaded account to the HashMap
            }
        }
    }

    // Method to search and retrieve the account by userId
    public Account findAccountById(int userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line by semicolons
                String[] parts = line.split(";");

                // Check if the first part (id) matches the userId
                int id = Integer.parseInt(parts[0]);

                if (id == userId) {
                    String name = parts[1];
                    int balance = Integer.parseInt(parts[2]);
                    return new Account(id, name, balance);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        } catch (NumberFormatException e) {
            System.out.println("Error in converting data types.");
        }

        // Return null if no account is found with the given userId
        return null;
    }

    // New method to update an account balance in the file
    public void updateAccountBalance(int accountId, int newBalance) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp_" + FILE_NAME);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean accountFound = false;

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);

                // If it's the account we're looking for, update the balance
                if (id == accountId) {
                    String name = parts[1];
                    writer.write(id + ";" + name + ";" + newBalance);
                    accountFound = true;
                } else {
                    // Otherwise, write the line as is
                    writer.write(line);
                }
                writer.newLine();  // Move to the next line
            }

            if (!accountFound) {
                System.out.println("Account with ID " + accountId + " not found in the file.");
            }

            // Close the writer and rename the temporary file
            writer.close();

            // Replace the old file with the updated one
            try {
                // Attempt to delete the old file
                if (inputFile.delete()) {
                    // Attempt to rename the temporary file to the original file name
                    if (!tempFile.renameTo(inputFile)) {
                        System.out.println("Failed to rename temporary file.");
                    }
                } else {
                    System.out.println("Failed to delete the original file.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred while replacing the file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while updating the account.");
        }
    }
}

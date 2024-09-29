import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
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
}

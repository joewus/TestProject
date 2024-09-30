import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class fileHandler {

        private String filePath = "data.txt"; // Path to your data file

        // Method to write account data to a file
        public void writeAccounts(List<Account> accounts) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Account account : accounts) {
                    writer.write(account.getId() + ";" + account.getName() + ";" + account.getBalance());
                    writer.newLine(); // Move to the next line
                }
                System.out.println("Accounts written to file successfully.");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

        // Method to read account data from a file
        public List<Account> readAccounts() {
            List<Account> accounts = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";\\s*");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int balance = Integer.parseInt(parts[2]);
                    accounts.add(new Account(id, name, balance));
                }
                System.out.println("Accounts read from file successfully.");
            } catch (IOException e) {
                System.err.println("Error reading from file: " + e.getMessage());
            }
            return accounts;
        }



}

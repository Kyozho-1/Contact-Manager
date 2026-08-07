package Backend;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import javax.swing.JOptionPane;

public class ContactMethods {
    ArrayList<Contact> contacts;

    public ContactMethods() {
        contacts = new ArrayList<>();
    }

    /**
     * Adds a contact to the <code>contacts</code> <code>ArrayList</code>.
     * @param name The name of the individual.
     * @param address The address of the individual.
     * @param phoneNumber The phone number of the individual.
     * @param email the E-mail of the individual.
     * @param dateOfBirth The date of birth of the individual.
     */
    public void addContact(String name, String address, String phoneNumber,
                           String email, String dateOfBirth) {
        contacts.add(new Contact(name, address, phoneNumber,
                                 email, dateOfBirth));
    }

    /**
     * Compares the contacts' names and sorts them alphabetically from A-Z.
     */
    public void sortContacts() {
        contacts.sort(Comparator.comparing(Contact::name));
    }

    /**
     * Searches for an individual's contact information by searching their name and removes it from <code>contacts</code>.
     * @param name The individual's name.
     * @return <code>true</code> if the contact has been successfully removed. <code>false</code> otherwise.
     */
    public boolean deleteContact(String name) {
        return contacts.removeIf(contact -> contact.name().equals(name));
    }

    /**
     * Searches for an individual's contact information by searching for their name.
     * @param name The individual's name.
     * @return <code>true</code> if the contact has been found. <code>false</code> otherwise.
     */
    public String searchContacts(String name) {
        return contacts.stream()
                       .filter(contact -> contact.name().equals(name))
                       .findFirst()
                       .map(Contact::toString)
                       .orElse(null);
    }

    /**
     * @return An <code>ArrayList</code> of type <code>Contact</code> that holds the contacts.
     */
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    /**
     * This is called automatically when the program opens up to get any data from a CSV file from a previous use. <br>
     * But, this can also be explicitly called in the application as well. <br>
     * The data file must be in <i>CSV</i> format.
     * @param source The source/destination of the CSV file.
     * @throws IOException Thrown if CSV is either not found or the file given is not of a CSV type.
     */
    public void importData(String source) throws IOException, IllegalArgumentException {
        if (!source.endsWith(".csv"))
            throw new IllegalArgumentException("Error! Please ensure that the file is a CSV file.");

        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        String name;
        String address;
        String phoneNumber;
        String email;
        String dateOfBirth;

        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                csvData.add(new ArrayList<>(Arrays.asList(fields)));
            }
        }

        csvData.removeFirst(); //* Removes the header row, that's not really needed.

        for (ArrayList<String> data : csvData) {
            name        = data.getFirst();
            address     = data.get(1);
            phoneNumber = data.get(2);
            email       = data.get(3);
            dateOfBirth = data.get(4);

            addContact(name, address, phoneNumber, email, dateOfBirth);

            System.out.printf("%s\n", searchContacts(name));
        }
    }

    /**
     * Exports all the contacts held in <code>contacts</code> to a CSV file to the file destination
     * specified by the user.
     * @param filepath The file path for the CSV file to be exported.
     * @throws IOException Thrown if the file's name does not end in '.csv'.
     */
    public void exportData(File filepath) throws IOException {
        String filename = filepath.getName();

        if (!filename.endsWith(".csv"))
            throw new IOException("Error! Please add '.csv' at the end of the file's name");

        String name;
        String address;
        String phoneNumber;
        String email;
        String dateOfBirth;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getAbsolutePath()))) {
            StringBuilder builder = new StringBuilder();

            //* Appending headers
            builder.append("Name,")
                   .append("Address,")
                   .append("Phone Number,")
                   .append("Email,")
                   .append("Date of Birth,");

            writer.write(builder.toString());
            writer.newLine(); //* This allows the values to appended underneath the headers so the CSV works right

            builder.setLength(0); //* Clears the builder of the headers so the headers aren't appended with the values.

            for (Contact contact : contacts) {
                builder.append(contact.name()).append(",");
                builder.append(contact.address()).append(",");
                builder.append(contact.phoneNumber()).append(",");
                builder.append(contact.email()).append(",");
                builder.append(contact.dateOfBirth()).append(",");

                writer.write(builder.toString());
                writer.newLine();

                builder.setLength(0); //* Clears the builder of the old values so the old values aren't duplicated.
            }

            System.out.println("Exported CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints every contact in the <code>Contact</code> <code>ArrayList</code>.
     * @return A <code>String</code> representation of every contact in the <code>Contact</code> <code>ArrayList</code>.
     */
    @Override
    public String toString() {
        StringBuilder contactsStr = new StringBuilder();

        for (Contact contact : contacts)
            contactsStr.append(contact);

        return contactsStr.toString();
    }
}
package Backend;

import java.util.Objects;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Creates a <code>record</code> <code>Backend.Contact</code> object for an individual.
 * @param name The name of the individual.
 * @param address The address of the individual.
 * @param phoneNumber The phone number of the individual.
 * @param email the E-mail of the individual.
 * @param dateOfBirth The date of birth of the individual.
 */
public record Contact(String name, String address, String phoneNumber,
                      String email, String dateOfBirth) {

    public Contact {
        validateName(name);
        validateAddress(address);
        validatePhoneNumber(phoneNumber);
        validateEmail(email);
        validateDOB(dateOfBirth);
    }

    /**
     * Sees if a name is valid. <br>
     * Checks for these two types of name formats:
     * <ol>First Last</ol>
     * <ol>First (Full Middle or Middle Initial) Last</ol>
     * @param name The name of the individual.
     * @throws IllegalArgumentException Thrown if name doesn't meet the requirements of a name.
     */
    private static void validateName(String name) throws IllegalArgumentException{
        Objects.requireNonNull(name, "Error! Please enter a name.");

        String[] splitName = name.trim().split("\\s+");

        if (splitName.length != 2 && splitName.length != 3)
            throw new IllegalArgumentException(
                    """
                    Error! Please enter a name in either of these two formats:
                        1. First Last
                        2. First (Full Middle or Middle Initial) Last
                        Ex: John Doe, John V. Doe, or John Vikas Doe.
                    """
            );
    }

    /**
     * Sees if an address is valid. <br>
     * Checks for three main components:
     * <ol>1. The street number.</ol>
     * <ol>2. The street name.</ol>
     * <ol>3. The street type.</ol>
     * @param address The address.
     * @throws IllegalArgumentException Thrown if the address doesn't meet one or more of the required components.
     */
    private static void validateAddress(String address) throws IllegalArgumentException {
        Objects.requireNonNull(address, "Error! Please enter an address.");

        String[] splitAddress = address.trim().split("\\s+");

        /*!
         There are 3 main parts to an address.
            1. The street number.
            2. The street name.
            3. The street type.
            This is checking if `address` has these components or not.
        */
        if (splitAddress.length != 3)
            throw new IllegalArgumentException(
                    """
                    Error! Please enter an address in this format:
                        [Street Number] [Street Name] [Street Type]
                        Ex: 123 John Doe St.
                    """
            );
    }

    /**
     * Sees if a phone number is valid or not. <br>
     * Checks if the phone number is at least ten digits long.
     * @param number The phone number.
     * @throws IllegalArgumentException Thrown if the phone number is less than or greater than ten digits.
     */
    private static void validatePhoneNumber(String number) throws IllegalArgumentException {
        Objects.requireNonNull(number, "Error! Please enter a phone number.");

        final int MAX_DIGITS = 10;

        //! This doesn't include country codes or exit codes for now but will be implemented later.
        String withoutHyphens = number.replace("-", "");

        if (withoutHyphens.length() != MAX_DIGITS)
            throw new IllegalArgumentException(
                    """
                    Error! Please enter a phone in this format:
                        [Area Code]-[Telephone prefix]-[Line Number]
                        Ex: 123-456-7890
                    
                    Note: Please ensure that there are ten digits in the phone number.
                    """
            );
    }

    /**
     * Sees if an email is valid. <br>
     * Checks for the following components:
     * <ol>Local Part</ol>
     * <ol>Domain</ol>
     * <ol>Top-level domain</ol>
     * @param email The email.
     * @throws IllegalArgumentException Thrown if the email doesn't have any one or more of the components listed.
     */
    private static void validateEmail(String email) throws IllegalArgumentException {
        Objects.requireNonNull(email, "Error! Please enter an email.");

        if (!email.matches("^[^@]+@[^@]+\\.[^@]+$"))
            throw new IllegalArgumentException(
                    """
                    Error! Please enter an email in the following format:
                        local@domain.tld
                    """
            );
    }

    /**
     * Sees if a date of birth is valid. <br>
     * Checks for the following format: MM/DD/YYYY.
     * @param dateOfBirth The date of birth.
     * @throws IllegalArgumentException Thrown if the date of birth is not in the correct format.
     */
    private static void validateDOB(String dateOfBirth) throws IllegalArgumentException {
        Objects.requireNonNull(dateOfBirth, "Error! Please enter a date of birth.");

        try {
            LocalDate.parse(
                    dateOfBirth,
                    DateTimeFormatter.ofPattern("MM/dd/yyyy")
            );
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Date must be in MM/DD/YYYY format."
            );
        }
    }

    /**
     * Returns a string representation of the <code>Backend.Contact</code> object.
     * @return A String representation of an individual's contact information.
     * */
    @Override
    public String toString() {
        return String.format("""
                             Name:         %s
                             Address:      %s
                             Phone Number: %s
                             email:        %s
                             DOB:          %s
                             """,
            name,address, phoneNumber, email, dateOfBirth);
    }
}
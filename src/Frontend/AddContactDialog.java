package Frontend;

import Backend.ContactMethods;

import javax.swing.*;
import java.awt.*;

public class AddContactDialog extends JDialog {
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField DOBField;

    private JLabel title;

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel fieldsPanel;
    private JPanel bottomPanel;

    private JButton submitButton;

    private final String TITLE = "Adding Contact";

    private final ContactMethods methods;

    public AddContactDialog(JFrame parentFrame, ContactMethods methods) {
        super(parentFrame, "Adding Contact", true);

        this.methods = methods;

        mainPanel   = new JPanel();
        headerPanel = new JPanel();
        fieldsPanel = new JPanel();
        bottomPanel = new JPanel();

        fieldsPanel.setLayout(new GridLayout(5, 2, 5, 5));
        mainPanel.setLayout(new BorderLayout());
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        start(parentFrame);

        add(mainPanel);

        setVisible(true);
    }

    private void start(JFrame parentFrame) {
        createTitle();
        createTextFields();
        createSubmitButton(parentFrame);
    }

    private void createTitle() {
        title = new JLabel(TITLE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator jSeparator = new JSeparator();

        jSeparator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        jSeparator.setBackground(Color.BLACK);
        jSeparator.setForeground(Color.BLACK);

        headerPanel.add(Box.createVerticalStrut(15));
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(jSeparator);
        headerPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }

    private void createTextFields() {
        final String[] FIELDS = {
                "Name",
                "Address",
                "Phone Number",
                "E-mail",
                "Date of Birth"
        };

        nameField    = new JTextField();
        addressField = new JTextField();
        phoneField   = new JTextField();
        emailField   = new JTextField();
        DOBField     = new JTextField();

        fieldsPanel.add(new JLabel(FIELDS[0] + ": "));
        fieldsPanel.add(nameField);

        fieldsPanel.add(new JLabel(FIELDS[1] + ": "));
        fieldsPanel.add(addressField);

        fieldsPanel.add(new JLabel(FIELDS[2] + ": "));
        fieldsPanel.add(phoneField);

        fieldsPanel.add(new JLabel(FIELDS[3] + ": "));
        fieldsPanel.add(emailField);

        fieldsPanel.add(new JLabel(FIELDS[4] + ": "));
        fieldsPanel.add(DOBField);

        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
    }

    private void createSubmitButton(JFrame parentFrame) {
        final Color MEDIUM_BLUE = new Color(0,0,205);

        submitButton = new JButton("Submit");

        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setContentAreaFilled(true);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(true);

        submitButton.setBackground(MEDIUM_BLUE);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submitButton.setPreferredSize(new Dimension(120, 30));

        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        bottomPanel.add(submitButton);

        submitButton.addActionListener(e -> processTextFieldsInput(parentFrame));

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void processTextFieldsInput(JFrame parentFrame) {
        try {
            methods.addContact(
                    nameField.getText().trim(),
                    addressField.getText().trim(),
                    phoneField.getText().trim(),
                    emailField.getText().trim(),
                    DOBField.getText().trim()
            );

            System.out.print(methods.searchContacts(nameField.getText().trim()));

            dispose();
        } catch (IllegalArgumentException e) {
            // Plays to notify user of the open window.
            Toolkit.getDefaultToolkit().beep();

            JOptionPane.showMessageDialog(parentFrame, e.getMessage(),
                                     "Entry error", JOptionPane.ERROR_MESSAGE);

            bottomPanel.revalidate();
            bottomPanel.repaint();
        }
    }
}
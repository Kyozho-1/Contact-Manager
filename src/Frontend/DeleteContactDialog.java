package Frontend;

import Backend.ContactMethods;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DeleteContactDialog extends JDialog {
    private JTextField nameField;

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel fieldPanel;
    private JPanel submitPanel;

    private JLabel title;

    private JButton submitButton;

    private final ContactMethods methods;

    public DeleteContactDialog(JFrame parentFrame, ContactMethods methods) {
        super(parentFrame, "Contact Search", true);

        this.methods = methods;

        mainPanel    = new JPanel();
        headerPanel  = new JPanel();
        fieldPanel   = new JPanel();
        submitPanel  = new JPanel();

        fieldPanel.setLayout(new GridLayout(1, 2, 5, 5));
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
        createTextField();
        createSubmitButton(parentFrame);
    }

    private void createTitle() {
        final String TITLE = "Contact Search";

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

    private void createTextField() {
        fieldPanel.add(new JLabel("Name: "));

        nameField = new JTextField();

        fieldPanel.add(nameField);

        mainPanel.add(fieldPanel, BorderLayout.CENTER);
    }

    private void createSubmitButton(JFrame parentFrame) {
        final Color MULBERRY_PURPLE = new Color(197, 75, 140);

        submitButton = new JButton("Submit");

        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setContentAreaFilled(true);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(true);

        submitButton.setBackground(MULBERRY_PURPLE);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submitButton.setPreferredSize(new Dimension(120, 30));

        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitPanel.add(submitButton);

        submitButton.addActionListener(e -> processTextFieldInput(parentFrame));

        mainPanel.add(submitPanel, BorderLayout.SOUTH);
    }

    private void processTextFieldInput(JFrame parentFrame) {
        boolean contactDeleted = methods.deleteContact(nameField.getText().trim());

        // Plays to notify user of the open window.
        Toolkit.getDefaultToolkit().beep();

        if (contactDeleted)
            JOptionPane.showMessageDialog(parentFrame,
                                 "The contact (" + nameField.getText().trim() + ") was successfully deleted!",
                                     "Contact Deleted", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(parentFrame,
                                 "The contact (" + nameField.getText().trim() + ") could not be found...",
                                     "Contact Could Not Be Deleted", JOptionPane.ERROR_MESSAGE);
    }
}

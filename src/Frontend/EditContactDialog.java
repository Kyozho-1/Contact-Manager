package Frontend;

import Backend.Contact;
import Backend.ContactMethods;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EditContactDialog extends JDialog {
    private final ContactMethods methods;

    private JLabel title;

    private JPanel headerPanel;
    private JPanel contactsPanel;
    private JPanel mainPanel;

    private JList<String> contactList;
    private DefaultListModel<String> defaultListModel;

    public EditContactDialog(JFrame parentFrame, ContactMethods methods) {
        super(parentFrame, "Editing Contact", true);

        this.methods = methods;

        headerPanel   = new JPanel();
        contactsPanel = new JPanel();
        mainPanel     = new JPanel();

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
        createContactsList(parentFrame);
    }

    private void createTitle() {
        final String TITLE = "Editing Contact";

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

    private void createContactsList(JFrame parentFrame) {
        contactList = new JList<>();

        defaultListModel = new DefaultListModel<>();

        methods.sortContacts();

        for (Contact contact : methods.getContacts())
            defaultListModel.addElement(contact.name());

        contactList.setBackground(Color.BLUE);
        contactList.setForeground(Color.WHITE);

        contactList.setBorder(new LineBorder(Color.BLACK, 2));

        contactList.setModel(defaultListModel);

        contactList.setVisible(true);

        contactsPanel.add(contactList);

        contactList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JList source = (JList) e.getSource();

                String selectedName = source.getSelectedValue().toString();

                methods.deleteContact(selectedName);

                new AddContactDialog(parentFrame, methods);
            }
        });

        mainPanel.add(contactsPanel, BorderLayout.CENTER);
    }
}
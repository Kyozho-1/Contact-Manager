package Frontend;

import Backend.ContactMethods;
import Backend.Option;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;

public class Menu {
    JFrame jFrame;

    JPanel buttonsPanel;
    JPanel mainPanel;
    JPanel headerPanel;

    JLabel title;

    ActionListenerHelper actionListenerHelper;

    private final ContactMethods methods = new ContactMethods();

    public Menu() throws IOException {
        jFrame = new JFrame("Chase's Contact Manager");

        actionListenerHelper = new ActionListenerHelper(jFrame, methods);

        buttonsPanel = new JPanel();
        mainPanel    = new JPanel();
        headerPanel  = new JPanel();

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BorderLayout());
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        jFrame.setSize(500, 500);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start() {
        //! This must be set here before creating any elements!
        setApplicationFont();

        createTitle();
        createButtons();

        jFrame.add(mainPanel);
        jFrame.setVisible(true);
    }

    /**
     * Creates radios buttons for the options for the menu. <br>
     * It also aligns the radios buttons vertically and puts them in the center.
     */
    private void createButtons() {
        final Color INTERNATIONAL_ORANGE = new Color(255,79,0);

        buttonsPanel.add(Box.createVerticalGlue());

        for (Option option : Option.values()) {
            JButton jButton = new JButton(option.getLabel());

            jButton.setFocusPainted(false);
            jButton.setBorderPainted(false);
            jButton.setContentAreaFilled(true);
            jButton.setOpaque(true);
            jButton.setBorderPainted(true);

            jButton.setBackground(INTERNATIONAL_ORANGE);
            jButton.setForeground(Color.WHITE);
            jButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jButton.setPreferredSize(new Dimension(0, 30));
            jButton.setActionCommand(option.name());

            jButton.addActionListener(actionListenerHelper);

            jButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            buttonsPanel.add(jButton);
            buttonsPanel.add(Box.createVerticalStrut(15));
        }

        buttonsPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the program's title as a <code>JLabel</code>.
     */
    private void createTitle() {
        final String TITLE = "Chase's Contact Manager";

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

    /**
     * Sets the application's font to Times New Roman.
     */
    private void setApplicationFont() {
        Font font = new Font("Times New Roman", Font.PLAIN, 18);

        Enumeration<Object> keys = UIManager.getDefaults().keys();

        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);

            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, new javax.swing.plaf.FontUIResource(font));
        }
    }
}
package Frontend;

import Backend.ContactMethods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionListenerHelper extends JDialog implements ActionListener {
    private final JFrame parentFrame;
    private final ContactMethods methods;

    public ActionListenerHelper(JFrame parentFrame, ContactMethods methods) {
        this.parentFrame = parentFrame;
        this.methods = methods;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "ADD"    -> new AddContactDialog(parentFrame, methods);
            case "DELETE" -> new DeleteContactDialog(parentFrame, methods);
            case "EDIT"   -> new EditContactDialog(parentFrame, methods);
            case "SEARCH" -> new SearchContactDialog(parentFrame, methods);
            case "IMPORT" -> {
                try {
                    new ImportContactData(parentFrame, methods);
                } catch (IOException ex) {
                    Toolkit.getDefaultToolkit().beep();

                    JOptionPane.showMessageDialog(parentFrame, ex.getMessage(),
                                             "Importation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "EXPORT" -> {
                try {
                    new ExportContactData(parentFrame, methods);
                } catch (IOException ex) {
                    Toolkit.getDefaultToolkit().beep();

                    JOptionPane.showMessageDialog(parentFrame, ex.getMessage(),
                                             "Exportation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
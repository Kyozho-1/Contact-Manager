package Frontend;

import Backend.ContactMethods;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExportContactData {
    private final ContactMethods methods;

    ExportContactData(JFrame parentFrame, ContactMethods methods) throws IOException {
        this.methods = new ContactMethods();

        File filepath = getFilePath();

        try {
            methods.exportData(filepath);
        } catch (IllegalArgumentException e) {
            Toolkit.getDefaultToolkit().beep();

            JOptionPane.showMessageDialog(parentFrame, e.getMessage(),
                                     "Exportation Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private File getFilePath() throws IOException {
        final File STARTING_POINT = new File(".");

        File filepath;

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Exporting CSV Contact Information");

        fileChooser.setCurrentDirectory(STARTING_POINT);

        int result = fileChooser.showSaveDialog(fileChooser);

        if (result == JFileChooser.APPROVE_OPTION)
            filepath = fileChooser.getSelectedFile();
        else
            throw new IOException("Error! Please select a file path to save your CSV file.");

        return filepath;
    }
}
package Frontend;

import Backend.ContactMethods;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImportContactData {
    private final ContactMethods methods;

    ImportContactData(JFrame parentFrame, ContactMethods methods) throws IOException {
        this.methods = new ContactMethods();

        String filepath = getFilePath();

        if (filepath.equals("No valid path.")) {
            throw new IOException("Error! Please select a CSV file.");
        }

        try {
            methods.importData(filepath);
        } catch (IllegalArgumentException e) {
            Toolkit.getDefaultToolkit().beep();

            JOptionPane.showMessageDialog(parentFrame, e.getMessage(),
                    "Importation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getFilePath() {
        String filepath = "No valid path."; //* Default string value if APPROVE_OPTION fails

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Importing CSV Contact Information");

        fileChooser.setCurrentDirectory(new File("."));

        int result = fileChooser.showOpenDialog(fileChooser);

        if (result == JFileChooser.APPROVE_OPTION)
            filepath = fileChooser.getSelectedFile().getAbsolutePath();

        return filepath;
    }
}
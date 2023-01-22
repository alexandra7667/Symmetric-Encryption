/**
 * This is the Controller class. It handles interaction between the GUI for the user
 * and the logic of the program.
 * Its purpose is to get user input and transfer it to the EncryptHandler class.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption_4_1_1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {
    private EncryptHandler encryptHandler;
    private FileChooser fileChooser;
    private File txtFile;
    private File keyFile;
    private File outputFile;

    public Controller() {
        encryptHandler = new EncryptHandler();
        fileChooser = new FileChooser();
    }
    @FXML
    private Label labelFileEncrypted;
    @FXML
    private Label labelShowTxtFile;
    @FXML
    private Label labelShowKeyFile;
    @FXML
    private TextField textFieldOutputFile;

    /**
     * This method opens the FileChooser and the user can pick a text file
     * they want to encrypt.
     * The URL of the file is shown in the GUI.
     */
    @FXML
    protected void getTxtFile() {
        txtFile = fileChooser.showOpenDialog(new Stage());
        labelShowTxtFile.setText(String.valueOf(txtFile));
    }

    /**
     * This method opens the FileChooser and the user can pick a key file
     * containing a serialized SecretKey to use for the encryption.
     * The URL of the file is shown in the GUI.
     */
    @FXML
    protected void getKeyFile() {
        keyFile = fileChooser.showOpenDialog(new Stage());
        labelShowKeyFile.setText(String.valueOf(keyFile));
    }

    /**
     * This method retrieves the user input of chosen name for the encrypted output file.
     * It opens the FileChooser and the user can rename the file if they wish.
     * The URL of the file is shown in the GUI.
     */
    @FXML
    protected void getOutputFile() {
        String outputFileName = textFieldOutputFile.getText();

        fileChooser.setInitialFileName(outputFileName + ".txt");
        outputFile = fileChooser.showSaveDialog(new Stage());

        textFieldOutputFile.setText(String.valueOf(outputFile));
    }

    /**
     * This method sends the text file to be encrypted, the file containing the SecretKey,
     * and the name and location of the output file, to the EncryptHandler.
     * When the encrypted file has been created, a confirmation appears on the GUI.
     */
    @FXML
    protected void encrypt() {
        encryptHandler.setUpEncryption(txtFile, keyFile, outputFile);

        labelFileEncrypted.setText("File encrypted");
    }
}
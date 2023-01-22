/**
 * This is the Controller class which handles all interaction between user (GUI)
 * and the logic of the program.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {
    private DecryptHandler decryptHandler;
    private FileChooser fileChooser;
    private File encryptedFile;
    private File keyFile;
    private File outputFile;

    public Controller() {
        decryptHandler = new DecryptHandler();
        fileChooser = new FileChooser();
    }
    @FXML
    private Label labelFileDecrypted;
    @FXML
    private Label labelShowEncryptedFile;
    @FXML
    private Label labelShowKeyFile;
    @FXML
    private TextField textFieldOutputFile;

    /**
     * This method opens the FileChooser and the user can pick a text file
     * they want to decrypt.
     * The URL of the file is shown in the GUI.
     */
    @FXML
    protected void getEncryptedFile() {
        encryptedFile = fileChooser.showOpenDialog(new Stage());
        labelShowEncryptedFile.setText(String.valueOf(encryptedFile));
    }

    /**
     * This method opens the FileChooser and the user can pick a key file
     * containing a serialized SecretKey to use for the decryption.
     * The URL of the file is shown in the GUI.
     */
    @FXML
    protected void getKeyFile() {
        keyFile = fileChooser.showOpenDialog(new Stage());
        labelShowKeyFile.setText(String.valueOf(keyFile));
    }

    /**
     * This method retrieves the user input of chosen name for the decrypted output file.
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
     * This method sends the encrypted file, the file containing the SecretKey,
     * and the name and location of the output file, to the DecryptHandler.
     * When the decrypted file has been created, a confirmation appears on the GUI.
     */
    @FXML
    protected void decrypt() {
        decryptHandler.setUpDecryption(encryptedFile, keyFile, outputFile);

        labelFileDecrypted.setText("File decrypted");
    }
}
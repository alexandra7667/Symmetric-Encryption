/**
 * This is the Controller class which handles interaction between the user (GUI)
 * and the logic of the program.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    private KeyHandler keyHandler;

    public Controller() {
        keyHandler = new KeyHandler();
    }
    @FXML
    private TextField textFieldName;
    @FXML
    private Label labelKeyCreated;

    /**
     * This method retrieves user input and sends it to the logic class KeyHandler.
     * When a key has been created, it is displayed on the GUI.
     */
    public void getValue() {
        String name = textFieldName.getText();
        keyHandler.createKey(name);
        labelKeyCreated.setText("Key created");
    }
}
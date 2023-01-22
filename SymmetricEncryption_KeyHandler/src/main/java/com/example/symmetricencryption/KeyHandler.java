/**
 * This is the logic class of the program which creates a new SecretKey,
 * serializes it and stores it locally.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.io.*;

public class KeyHandler {

    /**
     * This method generates a new SecretKey and stores it locally as a .ser file
     * @param name - The user chosen name of the key file
     */
    public void createKey(String name) {

        KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        int keySize = 256;
        keyGenerator.init(keySize);

        SecretKey secretKey = keyGenerator.generateKey();

        TheKey theKey = new TheKey(secretKey);

        File file = chooseLocation(name);

        writeKeyToFile(file, theKey);
    }

    /**
     * This method lets the user pick a location for the key file locally.
     * The user can also change the name of the file is they wish.
     * @param name - The original user defined key file name
     * @return - The file path and file name of the key file
     */
    private File chooseLocation(String name) {
        String fileName = name + ".ser";

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(fileName);
        File file = fileChooser.showSaveDialog(new Stage());

        return file;
    }

    /**
     * This method writes the serialized key to file.
     * @param file - The file path and file name of the key file
     * @param theKey - The serialized key
     */
    private void writeKeyToFile(File file, TheKey theKey) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(theKey);
            objectOutputStream.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}

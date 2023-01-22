/**
 * This class sets up for decryption by
 * 1) reading the key file and creating a new SecretKey object from it.
 * 2) reading the encypted and encoded text file and storing the contents in Strings.
 * It sends the objects to the Decrypter class for decryption.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption;

import javax.crypto.SecretKey;
import java.io.*;
import java.util.Scanner;

public class DecryptHandler {
    private Decrypter decrypter;

    public DecryptHandler() {
        decrypter = new Decrypter();
    }

    /**
     * This method sets up the decryption.
     * @param keyFile
     * @param encryptedFile
     * @param outputFile
     */
    public void setUpDecryption(File encryptedFile, File keyFile, File outputFile) {

        SecretKey secretKey = getKey(keyFile);

        String[] data = getDataToDecrypt(encryptedFile);
        String iv = data[0];
        String dataToDecrypt = data[1];

        decrypter.decrypt(iv, dataToDecrypt, secretKey, outputFile);
    }

    /**
     * This method reads the text file containing the serialized SecretKey,
     * retrieves the key from the class TheKey and returns a de-serialized SecretKey.
     * @param keyFile - The file containing the serialized key
     * @return - The SecretKey object
     */
    private SecretKey getKey(File keyFile) {
        TheKey theKey = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(keyFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            theKey = (TheKey) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return theKey.secretKey();
    }

    /**
     * This class reads the encrypted text file line by line and stores the IV (IVParameterSpec)
     * in index 0 of the String array 'data'.
     * The encrypted text is stored in index 1.
     * @param encryptedFile - The encrypted text file
     * @return - A String containing the text from the file
     */
    private String[] getDataToDecrypt(File encryptedFile) {

        String[] data = new String[2];

        Scanner scanner = null;

        try {
            scanner = new Scanner(encryptedFile);

            String fileText = "";

            while(scanner.hasNextLine()) {
                fileText += scanner.nextLine();
            }

            data = fileText.split("delimiter");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}

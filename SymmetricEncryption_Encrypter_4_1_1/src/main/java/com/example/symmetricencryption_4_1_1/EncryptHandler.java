/**
 * This class handles the set-up of the encryption.
 * It retrieves the SecretKey, a String from the unencrypted input file
 * and sends it to the Encrypter.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption_4_1_1;

import javax.crypto.SecretKey;
import java.io.*;
import java.util.Scanner;

public class EncryptHandler {
    private Encrypter encrypter;

    public EncryptHandler() {
        encrypter = new Encrypter();
    }

    /**
     * This method sets up the encryption.
     * @param txtFile - The unencrypted text
     * @param keyFile - The file containing the serialized key
     * @param outputFile - The name and location of the encrypted output file
     */
    public void setUpEncryption(File txtFile, File keyFile, File outputFile) {

        SecretKey secretKey = getKey(keyFile);

        String dataToEncrypt = getDataToEncrypt(txtFile);

        encrypter.encrypt(dataToEncrypt, secretKey, outputFile);
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
            fileInputStream.close();
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
     * This class reads the unencrypted text file line by line and
     * store the contents in a String.
     * @param txtFile - The unencrypted text file
     * @return - A String containing the text from the file
     */
    private String getDataToEncrypt(File txtFile) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(txtFile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        StringBuilder dataToEncrypt = new StringBuilder();
        while(scanner.hasNextLine()) {
            dataToEncrypt.append(scanner.nextLine() + "\n");
        }

        return String.valueOf(dataToEncrypt);
    }
}

/**
 * This class handles the logic of the program.
 * It takes an unencrypted file, a text file with a serialized SecretKey,
 * and a destined output file.
 * The unencrypted text file is encrypted with AES encryption using the SecretKey and outputted locally.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileWriter;
import java.security.SecureRandom;
import java.util.Base64;

public class Encrypter {

    /**
     * This method encrypts a String with a SecretKey (symmetric key) using a Cipher with an AES algorithm.
     * Before printing the file, it's encoded in Base64 (no special characters/control characters)
     * so the data will not be changed during transport.
     * @param dataToEncrypt - The unencrypted String
     * @param secretKey - The SecretKey to be used in encryption
     * @param outputFile - The name and location of the encrypted output file
     */
    public void encrypt(String dataToEncrypt, SecretKey secretKey, File outputFile) {
        byte[] iv = generateIV();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        byte[] encryptedBytes = null;
        try {
            Cipher cipherEncrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipherEncrypt.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

            byte[] bytesToEncrypt = String.valueOf(dataToEncrypt).getBytes("UTF-8");
            encryptedBytes = cipherEncrypt.doFinal(bytesToEncrypt);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("encrypted bytes= " + encryptedBytes.toString());

        String encodedString = Base64.getEncoder().encodeToString(encryptedBytes);
        String encodedIV = Base64.getEncoder().encodeToString(iv);

        System.out.println("Encoded string= " + encodedString);

        print(encodedIV, encodedString, outputFile);
    }

    /**
     * This method creates an IV that has the same size as the block that is encrypted.
     * The SecureRandom class is used to generate a random IV.
     * @return - The IV in form of a byte array
     */
    private byte[] generateIV() {
        byte[] iv = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        return iv;
    }

    /**
     * This method prints the encrypted file to disk.
     * @param encodedString - The encrypted file content
     * @param encodedIV - The encrypted IV
     * @param outputFile - The name and location of user chosen output file
     */
    private void print(String encodedIV, String encodedString, File outputFile) {
        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write(encodedIV + "delimiter");
            fileWriter.write(encodedString);
            fileWriter.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

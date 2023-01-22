/**
 * This class decrypts a file.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption_4_1_1;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileWriter;
import java.util.Base64;

public class Decrypter {

    /**
     * This method decodes and decrypts Strings using the SecretKey object.
     * @param iv - The IV used in the encrypted file
     * @param dataToDecrypt - The encrypted text
     * @param secretKey - The SecretKey object
     * @param outputFile - The file path and file name of the decrypted output file
     */
    public void decrypt(String iv, String dataToDecrypt, SecretKey secretKey, File outputFile) {

        byte[] decodedIV = decode(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(decodedIV);

        byte[] decodedBytes = decode(dataToDecrypt);

        String decryptedString = "";
        try {
            Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

            byte[] decryptedBytes = cipherDecrypt.doFinal(decodedBytes);
            decryptedString = new String(decryptedBytes, "UTF-8");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        print(decryptedString, outputFile);
    }

    /**
     * This method decodes a String with a Base64 decoder.
     * @param data
     * @return
     */
    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    /**
     * This method writes the decrypted text file to disk.
     * @param decryptedString - The decrypted text
     * @param outputFile - The file path and file name of the file
     */
    private void print(String decryptedString, File outputFile) {
        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write(decryptedString);
            fileWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

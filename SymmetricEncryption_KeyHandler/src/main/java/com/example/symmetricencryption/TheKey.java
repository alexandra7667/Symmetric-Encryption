/**
 * This is the serializable key class which holds a SecretKey.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption;

import java.io.Serializable;
import javax.crypto.SecretKey;

public record TheKey(SecretKey secretKey) implements Serializable {

    public TheKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * This class returns the SecretKey object
     * @return - A SecretKey
     */
    public SecretKey secretKey() {
        return this.secretKey;
    }
}


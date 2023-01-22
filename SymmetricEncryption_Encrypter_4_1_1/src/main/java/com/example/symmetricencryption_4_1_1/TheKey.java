/**
 * This is the serializable class that can de-serialize a SecretKey.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.symmetricencryption_4_1_1;

import java.io.Serializable;
import javax.crypto.SecretKey;

public record TheKey(SecretKey secretKey) implements Serializable {
    public TheKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public SecretKey secretKey() {
        return this.secretKey;
    }
}


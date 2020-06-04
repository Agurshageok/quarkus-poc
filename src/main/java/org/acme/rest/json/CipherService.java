package org.acme.rest.json;

import org.apache.commons.codec.binary.Base32;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@ApplicationScoped
public class CipherService {

    public CipherService() {
    }

    private static final String KEY_SPEC_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES";

    private Key generateKeyFromString(final String secKey) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        byte[] keyVal = secKey.getBytes();
        keyVal = sha.digest(keyVal);
        keyVal = Arrays.copyOf(keyVal, 16);
        return new SecretKeySpec(keyVal, KEY_SPEC_ALGORITHM);
    }

    public String encrypt(final String valueEnc, final String secKey) throws Exception {
        Base32 base32 = new Base32();
        String encryptedVal = null;
        try {
            final Key key = generateKeyFromString(secKey);
            final Cipher c = Cipher.getInstance(CIPHER_ALGORITHM);
           c.init(Cipher.ENCRYPT_MODE, key);
            final byte[] encValue = c.doFinal(valueEnc.getBytes());
            encryptedVal = base32.encodeAsString(encValue);
        } catch(Exception ex) {
            throw new Exception("Error encriptando valor ("+valueEnc+")");
        }

        return encryptedVal;
    }

    public String decrypt(final String encryptedValue, final String secretKey) throws Exception {
        Base32 base32 = new Base32();
        String decryptedValue = null;
        try {
            final Key key = generateKeyFromString(secretKey);
            final Cipher c = Cipher.getInstance(CIPHER_ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            final byte[] decorVal = base32.decode(encryptedValue);
            final byte[] decValue = c.doFinal(decorVal);
            decryptedValue = new String(decValue);
        } catch(Exception ex) {
            throw new Exception("Invalid hash ("+encryptedValue+")", ex);
        }

        return decryptedValue;
    }

}

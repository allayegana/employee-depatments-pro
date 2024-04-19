//package com.employee.Security;
//
//import com.thoughtworks.xstream.core.util.Base64Encoder;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.Key;
//
//public class CryptPassord {
//
//    private static final String ALGORITHM = "AES";
//    private static final String myEncryptionKey = "ThisIsFoundation";
//    private static final String UNICODE_FORMAT = "UTF8";
//
//    public String encrypt(String valueToEnc) throws Exception {
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGORITHM);
//        c.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encValue = c.doFinal(valueToEnc.getBytes(UNICODE_FORMAT));
//        //String encryptedValue = new Base64Encoder().encode(encValue);
//        return new Base64Encoder().encode(encValue);
//    }
//
//    public String Decrypt(String encryptedValue) throws Exception {
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGORITHM);
//        c.init(Cipher.DECRYPT_MODE, key);
//        byte[] decordedValue = new Base64Encoder().decode(encryptedValue);
//        byte[] decValue = c.doFinal(decordedValue);
//        return new String(decValue);
//    }
//
//    private static Key generateKey() throws Exception {
//        byte[] keyAsBytes;
//        keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
//        return new SecretKeySpec(keyAsBytes, ALGORITHM);
//    }
//}

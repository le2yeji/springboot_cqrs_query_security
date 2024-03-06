package com.kt.edu.thirdproject.common.config;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class RsaUtil {

    private static String base64PublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCg6NuryAtQG6nBfSGXaHrsRINUlOPPfFd1iVyjuHy80vrc6b/0vR3pSPF6U3OgBQ6pmUlWmvjTmxSFPH/vZ1BW4RAHzgOLBLF+VnZunM1GeoksCx7CbVVQ3ejGV6uWRStnmkplps8ITb/N2f1mOG1+dUltHmWyU0rY07+kQQajwIDAQAB";
    private static String base64PrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMKDo26vIC1AbqcF9IZdoeuxEg1SU4898V3WJXKO4fLzS+tzpv/S9HelI8XpTc6AFDqmZSVaa+NObFIU8f+9nUFbhEAfOA4sEsX5Wdm6czUZ6iSwLHsJtVVDd6MZXq5ZFK2eaSmWmzwhNv83Z/WY4bX51SW0eZbJTStjTv6RBBqPAgMBAAECgYEAt3MRg0U1UphPA8VRDhxmpvQUM24ukoz4A+kA3l81aD3fn0su+F2L5P9hkrlrgVM4QOxHvofd8r422N8aMXkyQhYxi6Tpe7HFoQcgmM6cwYSCXQLbRx/9sLX5GGoEIo3//+mF1a23bzibJWx+qWMbhtSYXK1r4EHAeH6Bg3AsXmkCQQD+dhkpAHaWVt8Fwkmo/u0gn/Eofxd94FAwByeSBdLCPKIq0PO1dImHN9jLSOAbfxt7kYCNqc6UXJoUqSRQ9MpTAkEAw7C+N9bUPpj+93ZHk+KcEl9/CLBTUUwDxc1jvN+TUYypDHcKy1cLiK/FxE/uxwSvOf16V8tY99U1xhJ/APO/VQJASt3QVUHvohf5EiaxqWknt1uXhoSuErj2nsrcF5hjBAb254YGzjJ1bDVAMb9FQJHLMjYDDqsRPpFoO//v0WnURQJAE7B60x94Z3v5W2i5NvX8AhU9sOAc4fcmAgbyXBSau//NGoX3vhHaBTy6R1BLlBayksEgSCkboIQBTxaZVL0xqQJBALeOAwrk+/y3N1OINWlfm5r4om3n4II70QTJE7hCfm4+1Smz4+ApZPd5Zs8lQWWAObDF4GvfBWVaIBzXEYnlEBM=";
    private static final int KEY_SIZE = 2048;

    /**
     * Public Key로 RSA 암호화를 수행합니다.
     * @param plainText 암호화할 평문입니다.
     * @param publicKey 공개키 입니다.
     * @return
     */

    public static String encryptRSA(String plainText, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytePlain = cipher.doFinal(plainText.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(bytePlain);

        return encrypted;
    }



    /**
     * Private Key로 RAS 복호화를 수행합니다.
     *
     * @param encrypted 암호화된 이진데이터를 base64 인코딩한 문자열 입니다.
     * @param privateKey 복호화를 위한 개인키 입니다.
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     */

    public static String decryptRSA(String encrypted, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] byteEncrypted = Base64.getDecoder().decode(encrypted.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] bytePlain = cipher.doFinal(byteEncrypted);

        String decrypted = new String(bytePlain, StandardCharsets.UTF_8);

        return decrypted;
    }



    /**
     *
     * @param base64PublicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */

    public static PublicKey getPublicKeyFromBase64Encrypted(String base64PublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] decodedBase64PubKey = Base64.getDecoder().decode(base64PublicKey);

        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decodedBase64PubKey));
    }

    /**
     *
     * @param base64PrivateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */

    public static PrivateKey getPrivateKeyFromBase64Encrypted(String base64PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] decodedBase64PrivateKey = Base64.getDecoder().decode(base64PrivateKey);

        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decodedBase64PrivateKey));
    }

    /**
     * Password RSA 복호화
     * @param encrypted
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     */

    public static String passwordDescryptRSA(String encrypted) throws Exception {

        try {
            PrivateKey privateKey = getPrivateKeyFromBase64Encrypted(base64PrivateKey);
            return decryptRSA(encrypted, privateKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException  e) {
            log.error("알 수 없는 암호화 알고리즘입니다. 암호화 하지 않습니다.", e);
            throw new Exception("알 수 없는 암호화 알고리즘입니다.");
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            log.error("Key 초기화 오류 입니다. 암호화 하지 않습니다.", e);
            throw new Exception("알 수 없는 암호화 알고리즘입니다.");
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            log.error("암호화 오류 입니다. 암호화 하지 않습니다.", e);
            throw new Exception("알 수 없는 암호화 알고리즘입니다.");
        }
    }

    /**
     * RSA Key generate
     * @return
     */

    public static String generateRSAKey() {
        log.debug("@generating RSA keys");

        try {Security.addProvider(new BouncyCastleProvider());

            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
            generator.initialize(KEY_SIZE);
            KeyPair keyPair = generator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            base64PublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());

            base64PrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return base64PublicKey;
    }

}


package jca.crypt.symmetric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class AESDemo {
   public static void main(String args[]) throws Exception{
      //Creating a KeyGenerator object
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      
      //Creating a SecureRandom object
      SecureRandom secRandom = new SecureRandom();
      
      //Initializing the KeyGenerator
      keyGen.init(secRandom);
      
      //Creating/Generating a key
      Key key = keyGen.generateKey();
      
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");      
      cipher.init(Cipher.ENCRYPT_MODE, key);      

      String msg = new String("Hi how are you");
      byte[] bytes = cipher.doFinal(msg.getBytes());      
      System.out.println(Base64.getEncoder().encodeToString(bytes));

      cipher.init(Cipher.DECRYPT_MODE, key); 
      bytes = cipher.doFinal(bytes);      
      System.out.println(new String(bytes));
   }
}
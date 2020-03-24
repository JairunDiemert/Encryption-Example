/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jca.hash.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

/**
 *
 * @author Bernd OK
 */
public class SHADemo {

    private final static int MAX_PW = 5;
    
    public static void main(String[] args) throws NoSuchAlgorithmException {

        String[] salts = new String[MAX_PW];
        String[] hashes = new String[MAX_PW];

        String[] passwords = {"s3cr3t", "pass", "foobar", "admin", "whereintheworldiscarmensandiego"};
        int numHashes = 0;

        for (int i = 0; i < MAX_PW; i++) {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] digest = md.digest(passwords[i].getBytes());

            salts[numHashes] = Base64.getEncoder().encodeToString(salt);
            hashes[numHashes] = Base64.getEncoder().encodeToString(digest);
            numHashes++;

            System.out.println(passwords[i] + " " + salts[i] + " (" + salts[i].length() + ") " +
                    hashes[i] + " (" + hashes[i].length() + ")");
        }
        Scanner in = new Scanner(System.in);
        String test = in.nextLine();
        for (int i = 0; i < MAX_PW; i++) {
            byte[] salt = Base64.getDecoder().decode(salts[i]);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] digest = md.digest(test.getBytes());
            // String compare = HexUtils.hexify(digest);
            String compare = Base64.getEncoder().encodeToString(digest);
            if (compare.equals(hashes[i])) {
                System.out.println("Found: " + passwords[i]);
            }
        }
        in.close();
    }
}

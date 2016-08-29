package burp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Util {
    //
    // A static class for miscellaneous utility methods
    //
    private Util() {
    }


    //This method takes a string "message"  and returns its md5 hash in a hexadecimal string form
    static String MD5(String message) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(message.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hash = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hash.length() < 32) {
                hash = "0" + hash;
            }
            return hash;
        } catch (NoSuchAlgorithmException nsae) {
            return "";
        }
    }

    static String testDigest(String message) {
        return message;
    }

    //This method takes an array of lines and concatenates them together into one multiline string, adding linebreaks inbetween the lines.
    static String arrayToString(String[] lines, String separator) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";
        for (String s : lines) {
            builder.append(prefix).append(s);
            prefix = separator;
        }
        return builder.toString();
    }

    static String arrayToString(String[] lines) {
        return arrayToString(lines, "\n");
    }

    // This method copies an InputStream to an OutputStream
    static void copyStream(InputStream inStream, OutputStream outStream) {
        byte[] buffer = new byte[1024];
        int length;
        try {
            //Read from inStream and write to outStream
            while ((length = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, length);
            }
        } catch (IOException ioe) {
            BurpExtender.stderr.println(ioe.toString());
        }
    }

    static String[] splitFileName(String fileName) {
        //Try to get the filename and extension
        String pattern = "^(.*)(\\..*)$";
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        // Now create matcher object.
        Matcher m = r.matcher(fileName);
        String[] nameParts = new String[2];
        boolean found = m.find();
        nameParts[0] = found ? m.group(1) : "script";
        nameParts[1] = found ? m.group(2) : ".tmp";
        return nameParts;
    }

}

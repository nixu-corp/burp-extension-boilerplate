package burp;

import java.io.*;

//
// Resource = A file in the /res directory of the jar file.
// You can include your own resources into the jar by putting them in the res directory and building this extension.
//
public class Resource {
    private InputStream inStream;
    private String fileName;

    //Read the resource to a stream
    public Resource(String res) {
        fileName = res;
        inStream = Util.class.getResourceAsStream("/res/" + res);
    }

    //Return the contents of the resource in a string
    @Override
    public String toString() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Util.copyStream(inStream, result);
        try {
            return result.toString("UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    public File extract() {
        String[] fix = Util.splitFileName(fileName);
        try {
            File tempFile = File.createTempFile(fix[0], fix[1]);
            Util.copyStream(inStream, new FileOutputStream(tempFile));
            return tempFile;
        } catch (IOException ioe) {
            return new File("");
        }
    }
}

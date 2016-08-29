package burp;

import java.io.File;

public class Configuration {
    //
    // Helper functions for Burp configuration
    //
    private IBurpExtenderCallbacks callbacks;

    public Configuration(IBurpExtenderCallbacks callbacks) {
        //Store the Burp extender callbacks
        this.callbacks = callbacks;
    }

    //This method extracts the given script and executes it
    boolean runScript(String scriptName, boolean needSudo) {
        Resource r = new Resource(scriptName);
        File script = r.extract();
        String scriptPath = script.getAbsolutePath();
        try {
            String cmd;
            //If super user privileges are needed, use pkexec
            if (needSudo) {
                cmd = "pkexec sh " + scriptPath;
            } else {
                cmd = "sh " + scriptPath;
            }
            ProcessBuilder pb = new ProcessBuilder(cmd);
            Process p = pb.start(); // Start the process.
            //Wait for process execution to end
            p.waitFor();
            //If something went wrong, abort the function
            if (p.exitValue() != 0) {
                this.callbacks.issueAlert("Configuration returned a non-zero exit value. Aborting.");
                return false;
            }
        } catch (Exception e) {
            this.callbacks.issueAlert("Process execution failed!");
            return false;
        }
        //Print a message about script deletion. Using conditional expression for brevity.
        BurpExtender.stdout.println(script.delete() ? "Script deleted after execution" : "Script deletion failed");
        return true;
    }

    //Overload above method to make needSudo optional
    boolean runScript(String scriptName){
        return runScript(scriptName, false);
    }

    boolean addConfiguration(String configFile) {
        Resource r = new Resource(configFile);
        this.callbacks.loadConfigFromJson(r.toString());
        return true;
    }

}

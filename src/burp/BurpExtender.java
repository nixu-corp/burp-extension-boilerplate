package burp;

import java.awt.*;
import java.io.PrintWriter;

public class BurpExtender implements IBurpExtender, ITab {

    //Initialize the internal callbacks object
    private IBurpExtenderCallbacks callbacks;
    static PrintWriter stdout, stderr;

    //
    //Implement IBurpExtender
    //
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        //Keep a copy of the callbacks object
        this.callbacks = callbacks;
        //Set our extension name
        callbacks.setExtensionName("Burp Extension Boilerplate");
        //Add the custom UI tab
        callbacks.addSuiteTab(BurpExtender.this);

        //Obtain the standard output and error streams
        stdout = new PrintWriter(callbacks.getStdout(), true);
        stderr = new PrintWriter(callbacks.getStderr(), true);
    }

    //
    //Implement ITab
    //
    @Override
    public String getTabCaption() {
        return "Nixu";
    }

    @Override
    public Component getUiComponent() {

        //Initialize configuration object
        Configuration config = new Configuration(this.callbacks);

        UI ui = new UI();

        ui.addHeading("Hello, world!");
        ui.addText("Below is a button that does absolutely nothing.");
        ui.addButton("Press me!", false, () -> {});

        /* Below are useful, but disabled because this is a boilerplate
        ui.addHeading("Mobile device scanning");
        ui.addText("Configure iptables for mobile device scanning. (Requires superuser privileges)");

        ui.addButton("Configure iptables", true, () -> config.runScript("iptables.sh", true));
        ui.addText("Set up proxy listeners for mobile device scanning.");
        ui.addButton("Configure proxies", false, () -> config.addConfiguration("proxy-config.json"));

        ui.addHeading("Configuration");
        ui.addText("Load 'Normal Scan' template. This includes various settings for the Burp Scanner as well as a private collaborator server.");
        ui.addButton("Load template", false, () -> config.addConfiguration("normal-scan.json"));
        */

        return ui.getUI();
    }

}

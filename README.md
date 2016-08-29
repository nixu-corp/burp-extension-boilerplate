# Burp Extension Boilerplate

## Table of contents ##
* [Description](#description)
* [How to build](#how-to-build)
* [Basic customization](#basic-customization)
* [Contact information](#contact-information)

---

## Description
Burp Extension Boilerplate is a Burp plugin template that can be easily customized to fit all needs. It includes a handy framework that makes it easier to construct a custom user interface for your plugin. The template also includes some miscellaneous methods for creating message digests and inserting them to Burp's message editor.

---

## How to build
The repository includes a script for automatic building:</br>
`build.bat` on Windows </br>
`build.sh` on Linux and OS X

> **NOTE! This plugin requires Java 1.8 or newer.**

Once the build is finished, the extension can be found in `bin\burpextender.jar`

---

## Basic customization
Most of the customization happens in the file `BurpExtender.jar` here is an example on how to add a custom button to the tab (titled 'Nixu' by default) this extension creates.

Go to the `getUiComponent` method in `BurpExtender.jar` and add the following lines and then build the extension:
```
// Add some text above the button
ui.addText("This is my button");
// Create the button
ui.addButton("I'm a button", false, () -> {});
```
After building, import the extension to Burp and you should see a new button in the 'Nixu' tab!

So far, the button does nothing. Fortunately it is super easy to add functionality to our new button. For example you can make the button print a text in the extensions output tab (which is located in the extensions tab), replace the last line in the previous code with this:
```
ui.addButton("I'm a button", false, () -> { stdout.println("Hello, world!!!"); });
```
Notice, that the only thing that is changed, is the `stdout.println("Hello, world!!!");` that is added inside the curly braces. This method just prints the text *"Hello, world!!!"*, you can put any method in there instead.

Go ahead and build it. After that, reload the extension in Burp and when you press your custom button, a text should appear in the output tab of the extension. Simple stuff.

For more information and documentation on customizing the extension further, please head over to the [wiki](https://github.com/nixu-corp/burp-extension-boilerplate/wiki).

---

## Contact information
Author: Timo Järventausta</br>
Emai: timo.jaerv@gmail.com

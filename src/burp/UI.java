package burp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI {
//
//Class for building the Burp tab UI in a modular fashion that's easy to modify
//

    private EmptyBorder border, leftBorder;
    private Box mainContainer;

    //Primary constructor
    public UI(int gap) {
        //Initialize the needed borders with the specified gap
        this.border = new EmptyBorder(gap / 2, gap / 2, gap / 2, gap / 2);
        this.leftBorder = new EmptyBorder(0, gap, 0, 0);
        this.mainContainer = new Box(BoxLayout.Y_AXIS);
        this.mainContainer.setBorder(this.border);
    }

    //Make the gap parameter optional by overloading the constructor
    public UI() {
        this(20);
    }

    void addButton(String buttonText, boolean includeLabel, Callback callback) {
        //Create a container for the button and set a margin for it
        Box subContainer = new Box(BoxLayout.X_AXIS);
        subContainer.setBorder(this.border);
        subContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton button = new JButton(buttonText);
        subContainer.add(button);

        //If label is needed, create it and add it to the container
        if (includeLabel) {
            JLabel label = new JLabel("");
            label.setBorder(leftBorder);
            subContainer.add(label);
            //Add the listener with the label changing (uses lambda expression)
            button.addActionListener( e -> {
                if (callback.call()) { label.setText("Success"); } else { label.setText("Failed"); }
            });
        } else {
            //Add the listener without the label (uses
            button.addActionListener( e -> callback.call());
        }

        this.mainContainer.add(subContainer);
    }

    void addHeading(String text) {
        JLabel heading = new JLabel(text);
        heading.setBorder(border);
        heading.setFont(new Font("SansSerif", Font.BOLD, 14));
        heading.setForeground(new Color(229, 137, 0));
        this.mainContainer.add(heading);
    }

    void addText(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(border);
        this.mainContainer.add(label);
    }

    public Component getUI() {
        return this.mainContainer;
    }

}

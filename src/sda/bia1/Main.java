package sda.bia1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

public class Main {

    public static void main(String[] args) {

        JFrame calcFrame = new JFrame("Calculator");

        calcFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        calcFrame.add(new CalculatorPanel());

        Dimension minDimension = new Dimension(200,200);
        calcFrame.setMaximumSize(minDimension);
        calcFrame.pack();
        calcFrame.setVisible(true);




    }
}

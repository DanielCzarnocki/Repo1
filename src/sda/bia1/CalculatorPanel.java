package sda.bia1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculatorPanel extends JPanel implements ActionListener {

    private JTextField display;
    private final String CLEAR = "Clear";
    private String operation="";
    private Logger logger = Logger.getLogger(CalculatorPanel.class.getSimpleName());

    public CalculatorPanel() {
        initLayout();
        initComponents();
    }

    private void initLayout() {
        setLayout(new BorderLayout(2,2));
    }

    private void initComponents() {
        display = new JTextField();
        display.setEditable(false);
        display.setBorder(new LineBorder(Color.BLUE,1,true));
        display.setPreferredSize(new Dimension(20, 40));
        display.setHorizontalAlignment(JTextField.CENTER);
        add(display, BorderLayout.NORTH);

        JPanel panelForButtons = new JPanel();
        panelForButtons.setLayout(new GridLayout(4, 4,5,5));

        String operations = "789/"
                + "456*"
                + "123-"
                + "=0.+";

        for (int i = 0; i < operations.length(); i++) {
            JButton button = new JButton(String.valueOf(operations.charAt(i)));
            button.setPreferredSize(new Dimension(50, 50));
            button.addActionListener(this);
            button.setBorder(new LineBorder(Color.BLUE,1,true));
            panelForButtons.add(button);
        }
        add(panelForButtons, BorderLayout.CENTER);

        JButton clarButton = new JButton(CLEAR);
        clarButton.addActionListener(this);
        add(clarButton, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String prev = display.getText();
        String sign = e.getActionCommand();

        logger.log(Level.INFO,String.format("PREV: %s, SIGN: %s,OPER: ", prev, sign, operation));

        if (CLEAR.equals(sign)) {
            display.setText("");
            prev = "";
            operation = "";
        } else if (Character.isDigit(sign.charAt(0)) || ".".equals(sign)) {
            display.setText(prev + sign);
        } else if ("=".equals(sign)) {
            double result = getResultForCurrentArguments(prev);
            display.setText(String.format("%.10f",result));
            prev="";
            operation = "";
        } else {
            if ("".equals(operation)){
                operation = sign;
                display.setText(prev + " " + sign + " ");
            }else {
                double result = getResultForCurrentArguments(prev);
                operation = sign;
                display.setText(String.format("%.10f %s ",result,operation));
            }
        }


    }

    private double getResultForCurrentArguments(String prev) {
        String regx = "\\"+operation;
        String[] args = prev.split(regx);
        Double arg1 = Double.parseDouble(args[0]);
        Double arg2 = Double.parseDouble(args[1]);
        return performOperation(arg1,arg2,operation);
    }

    private double performOperation(double arg1, double arg2, String operation){

        switch (operation){
            case "+" :return arg1 + arg2;
            case "-" :return arg1 - arg2;
            case "*" :return arg1 * arg2;
            case "/" :return arg1 / arg2;
        }
        return 0.0;
    }

}

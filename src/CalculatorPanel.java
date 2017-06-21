import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by Ania on 21.06.2017.
 */

public class CalculatorPanel extends JPanel
{
    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;

    public CalculatorPanel()
    {
        setLayout(new BorderLayout());

        result = 0;
        lastCommand = "=";
        start = true;

        // Calculator LCD display
        display = new JButton("0");
        display.setEnabled(false);
        display.setFont(new Font("Serif", Font.BOLD, 20));
        add(display, BorderLayout.NORTH);

        ActionListener edit = new editAction();
        ActionListener command = new CommandAction();

        // Calculator buttons
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        addButton("<--", edit);    // backspace
        addButton("C", command);   // Clear
        addButton("+/-", command);
        addButton("1/x", command);

        addButton("7", edit);
        addButton("8", edit);
        addButton("9", edit);
        addButton("/", command);

        addButton("4", edit);
        addButton("5", edit);
        addButton("6", edit);
        addButton("*", command);

        addButton("1", edit);
        addButton("2", edit);
        addButton("3", edit);
        addButton("-", command);

        addButton("0", edit);
        addButton(".", edit);
        addButton("=", command);
        addButton("+", command);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Format result in LCD display
     * not perfect but too many exams ...
     * @param x - double, number to format
     */
    private String formatNbr(double x)
    {
        String aux = "" + x;
        if (aux.endsWith(".0") )
        {  aux = aux.substring(0,aux.length()-2);
        }
        return aux;
    }

    /**
     * Add one button to panel.
     * @param label
     * @param listener - button action
     */

    private void addButton(String label, ActionListener listener)
    {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

    /**
     * Modify LCD display.
     */

    private class editAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String input = event.getActionCommand();
            if ( input.equals("<--") )
            {
                if (!start)
                {
                    if (display.getText().length()==1)
                    {
                        start = true;
                        display.setText("0");
                    }
                    else
                    {
                        int len = display.getText().length();
                        display.setText(display.getText().substring(0,len-1));
                    }
                }
            }
            else
            {
                if (start)
                {
                    display.setText("");
                    start = false;
                }
                display.setText(display.getText() + input);
            }
        }
    }

    /**
     * Action button.
     */

    private class CommandAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String command = event.getActionCommand();

            if (command.equals("C"))
            {
                display.setText("0");
                start = true;
                result = 0;
                lastCommand = "=";
                return;
            }

            if (command.equals("+/-"))
            {
                if (!start)
                {  double lineValue = Double.parseDouble(display.getText());
                    lineValue *= -1.0;
                    display.setText(formatNbr(lineValue));
                }
                return;
            }

            if (command.equals("1/x"))
            {
                if (!start)
                {
                    double lineValue = Double.parseDouble(display.getText());
                    if ( lineValue != 0.0 );
                    {  lineValue = 1/lineValue;
                        display.setText(formatNbr(lineValue));
                    }
                }
                return;
            }

            if (start)
            {
                if (command.equals("-"))
                {
                    display.setText(command);
                    start = false;
                }
                else lastCommand = command;
            }
            else
            {
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start = true;
            }
        }
    }

    /**
     * Make calculation.
     * @param x value to process with previous result.
     */

    private void calculate(double x)
    {
        if (lastCommand.equals("+")) result += x;
        else if (lastCommand.equals("-")) result -= x;
        else if (lastCommand.equals("*")) result *= x;
        else if (lastCommand.equals("/")) result /= x;
        else if (lastCommand.equals("=")) result = x;
        //display.setText("" + result);
        display.setText(formatNbr(result));
    }
}

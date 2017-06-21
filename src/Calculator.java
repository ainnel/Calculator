import java.awt.*;
import javax.swing.*;

/**
 * Created by Ania on 21.06.2017.
 */

public class Calculator
{
    public static void main( String[] args)
    {
        EventQueue.invokeLater( new Runnable()
                                {  public void run()
                                {
                                    JFrame frame = new JFrame();
                                    frame.setTitle("Calculator");
                                    frame.setSize(400,500);
                                    frame.setResizable(false);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setVisible(true);
                                    frame.add(new CalculatorPanel());
                                }
                                }
        );
    }
}
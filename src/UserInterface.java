import exceptions.BracketException;
import exceptions.ExpressionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {
    private JPanel panel1;
    private JTextField expression;
    private JButton a7Button;
    private JButton pointButton;
    private JButton a4Button;
    private JButton minusButton;
    private JButton a1Button;
    private JButton plusButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a0Button;
    private JButton cButton;
    private JButton enterButton;
    private JButton button1;
    private JButton button2;
    private JButton backspaceButton;
    private JTextField resultField;
    private Calculator calculator;
    private static int x, y;

    private UserInterface() {
        setContentPane(panel1);
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Калькулятор");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        x = 630;
        y = 800;
        setBounds(dimension.width / 2 - x / 2, dimension.height / 2 - y / 2, x, y);

        calculator = Calculator.getInstance();

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expression.getText().equals("0"))
                    calculator.setExpression("");
                else
                    calculator.setExpression(expression.getText());
                try {
                    System.out.println(calculator.getResult());
                    resultField.setText(String.valueOf(calculator.getResult()));
                }
                catch (ExpressionException ex){
                    resultField.setText("Неверный формат выражения");
                }
                catch (BracketException ex) {
                    resultField.setText("Скобки не согласованы");
                }
                catch (ArithmeticException ex){
                    resultField.setText("Деление на ноль");
                }
            }
        });

        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "1");
                expression.setText(calculator.getExpression());
            }
        });

        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "2");
                expression.setText(calculator.getExpression());
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "3");
                expression.setText(calculator.getExpression());
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "4");
                expression.setText(calculator.getExpression());
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "5");
                expression.setText(calculator.getExpression());
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "6");
                expression.setText(calculator.getExpression());
            }
        });
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "7");
                expression.setText(calculator.getExpression());
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "8");
                expression.setText(calculator.getExpression());
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "9");
                expression.setText(calculator.getExpression());
            }
        });
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "+");
                expression.setText(calculator.getExpression());
            }
        });
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "-");
                expression.setText(calculator.getExpression());
            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "*");
                expression.setText(calculator.getExpression());
            }
        });
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "/");
                expression.setText(calculator.getExpression());
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "(");
                expression.setText(calculator.getExpression());
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + ")");
                expression.setText(calculator.getExpression());
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + "0");
                expression.setText(calculator.getExpression());
            }
        });
        pointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression(calculator.getExpression() + ".");
                expression.setText(calculator.getExpression());
            }
        });
        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setExpression("");
                expression.setText(calculator.getExpression());
            }
        });
        backspaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expression.getText().length() != 0) {
                    calculator.setExpression(calculator.getExpression().substring(0, calculator.getExpression().length() - 1));
                    expression.setText(calculator.getExpression());
                }
            }
        });
    }

    public static void drawUI() {
        new UserInterface();
    }
}
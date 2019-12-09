import exceptions.BracketException;
import exceptions.ExpressionException;

import java.util.Stack;

public class Calculator implements SymbolChecker {
    private Stack stack;
    private static Calculator instance;
    private ExpressionParser parser;
    private String polishNotation;
    private String expression;  //Введённое выражение
    private double result;  //Результат вычисления
    private String temp;


    private Calculator() {
        stack = new Stack();
        expression = "";
        result = 0;
    }

    public static Calculator getInstance() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    private double calculation(char sign) throws ExpressionException, ArithmeticException {                                           //Применение операции к числам
        double num1, num2;

        if (!stack.empty())
            num2 = Double.parseDouble(String.valueOf(stack.pop()));
        else
            throw new ExpressionException();

        if (!stack.empty()) {
            num1 = Double.parseDouble(String.valueOf(stack.pop()));
        }
        else
            throw new ExpressionException();

        if (num2 == 0 || num1 == 0)
            throw new ArithmeticException();

        switch (sign) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/': {
                return num1 / num2;
            }
        }
        return 0;
    }

    public double getResult() throws ExpressionException, BracketException {                                                     //Вычисление результата
        while (!stack.empty())
            stack.pop();

        parser = new ExpressionParser(expression);
        polishNotation = parser.getPolishNotation();

        for (int i = 0; i < polishNotation.length(); i++) {
            temp = "";

            if (Character.isDigit(polishNotation.charAt(i)) || (i + 1 < polishNotation.length() && polishNotation.charAt(i) == '-' && Character.isDigit(polishNotation.charAt(i+1)))) {
                while (polishNotation.charAt(i) != ' ') {
                    temp += polishNotation.charAt(i);
                    i++;
                }
                stack.push(temp);
                continue;
            }

            if (isOperation(polishNotation.charAt(i))) {
                stack.push(String.valueOf(calculation(polishNotation.charAt(i))));
            }
        }

        if (!stack.empty())
            result = Double.parseDouble((String) stack.pop());
        else
            throw new ExpressionException();

        return result;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public static void main(String[] args) {
        UserInterface.drawUI();
    }
}
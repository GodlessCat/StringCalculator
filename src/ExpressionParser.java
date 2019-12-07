import exceptions.BracketException;
import exceptions.ExpressionException;

import java.util.Stack;

public class ExpressionParser implements SymbolChecker {
    private String polishNotation;
    private String expression;
    private Stack stack;

    public ExpressionParser(String expression){
        this.expression = expression;
        stack = new Stack();
    }

    public String getPolishNotation() throws ExpressionException, BracketException {
        parseExpression(expression);
        return polishNotation;
    }

    private void parseExpression(String expression) throws BracketException, ExpressionException {                                           //Преобразование выражения в обратную Польскую нотацию
        boolean firstPoint = true;
        polishNotation = "";

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-' && (i == 0 || (!Character.isDigit(expression.charAt(i - 1)) && expression.charAt(i - 1) != ')'))) {      //Обработка унарного минуса
                polishNotation += expression.charAt(i);
                continue;
            }

            if (expression.charAt(i) == '.') {                                                      //Обработка точки
                if (!firstPoint)
                    throw new ExpressionException();
                if (i + 1 == expression.length() || i == 0)
                    throw new ExpressionException();
                if (!Character.isDigit(expression.charAt(i - 1)) || !Character.isDigit(expression.charAt(i + 1))) {
                    throw new ExpressionException();
                } else {
                    polishNotation += expression.charAt(i);
                    firstPoint = false;
                    continue;
                }
            }

            if (Character.isDigit(expression.charAt(i))) {                                         //Обработка чисел
                polishNotation += expression.charAt(i);
                if ((i + 1 < expression.length() && (!Character.isDigit(expression.charAt(i + 1))) && expression.charAt(i + 1) != '.') || i + 1 == expression.length()) {
                    polishNotation += ' ';
                    firstPoint = true;
                }
                continue;
            }

            if (isOperation(expression.charAt(i))) {                                                //Обработка операций
                if (i == 0 || i + 1 == expression.length() || isOperation(expression.charAt(i - 1))) {
                    throw new ExpressionException();
                }

                if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                    while (!stack.empty() && priority(expression.charAt(i)) <= priority((char) stack.peek()))
                        polishNotation += stack.pop() + " ";
                    stack.push(expression.charAt(i));
                    continue;
                }
            }

            if (isBracket(expression.charAt(i))) {                                                  //Обработка скобок
                if (expression.charAt(i) == '(') {
                    stack.push(expression.charAt(i));
                    continue;
                }

                if (expression.charAt(i) == ')') {
                    if (isOperation(expression.charAt(i - 1)))
                        throw new ExpressionException();

                    if (i + 1 < expression.length() && !isOperation(expression.charAt(i + 1)))
                        throw new ExpressionException();

                    while (!stack.empty() && !stack.peek().equals('('))
                        polishNotation += stack.pop() + " ";

                    if (stack.empty())
                        throw new BracketException();
                    stack.pop();
                }
            }
        }

        while (!stack.empty()) {
            if (stack.peek().equals('('))
                throw new BracketException();
            polishNotation += stack.pop();
        }

    }
}

public interface SymbolChecker {

    default boolean isOperation(char c) {                                              //Проверка операций
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    default boolean isBracket(char c) {                                                //Проверка скобок
        return c == '(' || c == ')';
    }

    default int priority(char c) {                                                     //Рассчёт приорета операции
        if (c == '*' || c == '/') return 2;
        if (c == '+' || c == '-') return 1;
        if (c == '(') return 0;
        return 3;
    }
}

package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishCalculator {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        var ls = strToList(expression);
        var rpn = toRPNList(ls);
        int result = calculate(rpn);
        System.out.println(result);
    }

    public static List<String> toRPNList(List<String> ls) {
        var operatorStack = new Stack<String>();
        var result = new ArrayList<String>();

        for (var item : ls) {
            if (item.matches("\\d+")) {
                result.add(item);
            } else if (item.equals("(")) {
                operatorStack.push(item);
            } else if (item.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    result.add(operatorStack.pop());
                }
                operatorStack.pop(); // 彈出左括號 (
            } else {
                while (operatorStack.size() != 0
                        && Operator.getPriority(operatorStack.peek()) >= Operator.getPriority(item)) {
                    result.add(operatorStack.pop());
                }
                operatorStack.push(item);
            }

        }

        while (operatorStack.size() != 0) {
            result.add(operatorStack.pop());
        }

        return result;
    }

    public static List<String> strToList(String expression) {
        List<String> ls = new ArrayList<String>();
        int i = 0;
        String str; // 用來拼接多位數
        char c;

        do {
            c = expression.charAt(i);
            if (c < 48 || c > 57) {
                ls.add("" + c);
                i++;
            } else {
                str = "";
                while (c >= 48 && c <= 57) {
                    str += c;
                    i++;
                    if (i >= expression.length()) {
                        break;
                    }
                    c = expression.charAt(i);
                }
                ls.add(str);
            }
        } while (i < expression.length());

        return ls;
    }

    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<String>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;

                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                    case "x":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("運算符號有誤");
                }
                stack.push(Integer.toString(res));
            }
        }

        return Integer.parseInt(stack.pop());
    }
}

class Operator {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getPriority(String operator) {
        switch (operator) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "x":
            case "*":
                return MUL;
            case "/":
                return DIV;
            case "(":
                return 0;
        }

        throw new IllegalArgumentException("未知的計算符號 \"" + operator + "\"");
    }
}
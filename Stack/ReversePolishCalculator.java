package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishCalculator {
    public static void main(String[] args) {
        String suffixExpression = "3 4 + 5 * 6 - ";
        List<String> rpn = getListString(suffixExpression);
        int result = calculate(rpn);
        System.out.println(result);
    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String element : split) {
            list.add(element);
        }
        return list;
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

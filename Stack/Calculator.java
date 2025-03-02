package Stack;

import java.util.Arrays;

public class Calculator {
    public static void main(String[] args) {
        String expression = "7*2*2-5+1-5+3-4";
        Calculator caculator = new Calculator();
        int result = caculator.execute(expression);
        System.out.println(expression + ": " + result);
    }

    private ArrayStack numStack = new ArrayStack(10);
    private ArrayStack operStack = new ArrayStack(10);

    public int execute(String expression) {
        int index = 0;
        char ch;
        String keepNum = "";

        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (isOper(ch)) {
                if (!operStack.isEmpty() && priority(ch) <= priority(operStack.peek())) {
                    doCaculateFromStack();
                }
                operStack.push(ch);
            } else {
                keepNum += ch;

                if (index == expression.length() - 1) {
                    // 如果已經是最後一位，就放入 numStack
                    numStack.push(Integer.parseInt(keepNum));
                } else if (isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                    // 如果下一個 char 為符號，就放入 numStack
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }
            }

            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {
            // 當符號棧為空，代表運算結束
            if (operStack.isEmpty()) {
                break;
            }

            doCaculateFromStack();
        }

        return numStack.pop();
    }

    private void doCaculateFromStack() {
        int num1;
        int num2;
        int oper;

        num1 = numStack.pop();
        num2 = numStack.pop();
        oper = operStack.pop();
        numStack.push(cal(num2, num1, oper));
    }

    private int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            System.out.println("未知符號");
            return -1;
        }
    }

    private boolean isOper(int val) {
        int[] operators = new int[] { '+', '-', '*', '/' };
        return Arrays.stream(operators).anyMatch(x -> x == val);
    }

    private int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;

            case '-':
                res = num1 - num2;
                break;

            case '*':
                res = num1 * num2;
                break;

            case '/':
                if (num2 == 0) {
                    throw new IllegalArgumentException("不能除 0");
                }
                res = num1 / num2;
                break;
        }

        return res;
    }
}

package dsisejkovic.algortihms;

import java.util.Stack;

/**
 * Created by dsisejkovic on 10.7.2015..
 */
public class AddingLargeNumbersAlg {
    private Stack<Integer> numStack1;
    private Stack<Integer> numStack2;
    private Stack<Integer> resStack;

    public String execute(String numStr1, String numStr2) {
        this.numStack1 = new Stack<>();
        this.numStack2 = new Stack<>();
        this.resStack = new Stack<>();

        loadStack(numStack1, numStr1);
        loadStack(numStack2, numStr2);

        int carry = 0;
        while (!numStack1.isEmpty() || !numStack2.isEmpty()) {
            int num1 = getNumber(numStack1);
            int num2 = getNumber(numStack2);

            int res = num1 + num2 + carry;

            int unitNum = res % 10;
            resStack.push(unitNum);

            carry = res > 10 ? 1 : 0;
        }

        if (carry == 1) {
            resStack.push(carry);
        }

        return createStrNum(resStack);
    }

    private int getNumber(Stack<Integer> stack) {
        return stack.isEmpty() ? 0 : stack.pop();
    }

    private String createStrNum(Stack<Integer> stack) {
        StringBuilder builder = new StringBuilder();

        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        return builder.toString();
    }

    private void loadStack(Stack<Integer> stack, String numStr) {
        if (stack == null) {
            stack = new Stack<>();
        }

        char[] charNumArr = numStr.toCharArray();

        for (int i = 0; i < charNumArr.length; i++) {
            stack.push(Character.getNumericValue(charNumArr[i]));
        }
    }


    public static void main(String[] args) {
        String num1 = "5111";
        String num2 = "1111111";

        System.out.println(new AddingLargeNumbersAlg().execute(num1, num2));
    }
}

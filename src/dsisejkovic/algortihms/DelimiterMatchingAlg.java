package dsisejkovic.algortihms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by dsisejkovic on 9.7.2015..
 */

public class DelimiterMatchingAlg {

    public boolean execute(String fileName) throws IOException {
        FileInputStream inStream = new FileInputStream(fileName);
        Stack<Character> stack = new Stack<>();
        int c;

        boolean result = true;
        while ((c = inStream.read()) != -1) {
            char ch = (char) c;
            if (isOpeningBracket(ch)) {
                stack.push(ch);
            } else if (isClosingBracket(ch)) {
                if (stack.isEmpty() || !isMatchingBracket(stack.pop(), ch)) {
                    result = false;
                    break;
                }
            }
        }

        inStream.close();

        if (result) {
            result = stack.isEmpty();
        }

        return result;
    }

    private boolean isOpeningBracket(char ch) {
        return ch == '[' || ch == '{' || ch == '(';
    }

    private boolean isClosingBracket(char ch) {
        return ch == ']' || ch == '}' || ch == ')';
    }

    private boolean isMatchingBracket(char ch1, char ch2) {
        return (ch1 == '[' && ch2 == ']') || (ch1 == '{' && ch2 == '}') || (ch1 == '(' && ch2 == ')');
    }


    public static void main(String[] args) {
        try {
            System.out.println(new DelimiterMatchingAlg().execute("chars.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


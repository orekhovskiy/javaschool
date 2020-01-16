package com.tsystems.javaschool.tasks.calculator;
import java.util.Stack;
import java.util.ArrayList;

public class Calculator {

    public String evaluate(String statement) {
        ArrayList<Object> parsedList = parse(statement);
        if (parsedList == null) return null;
        ArrayList<Object> postfixArray = toPostfix(parsedList);
        if (postfixArray  == null) return null;
        Stack<Double> operandStack = new Stack<>();
        for (Object object: postfixArray) {
            if (object instanceof  Double) {
                operandStack.push((Double)object);
            } else {
                if (operandStack.size() < 2) return  null;
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                switch ((String) object) {
                    case "*":
                        operandStack.push(operand1 * operand2);
                        break;
                    case "/":
                        if (operand2 == 0) return null;
                        operandStack.push(operand1 / operand2);
                        break;
                    case "+":
                        operandStack.push(operand1 + operand2);
                        break;
                    case "-":
                        operandStack.push(operand1 - operand2);
                        break;
                }
            }
        }
        if (operandStack.size() > 1) return null;
        Double result = operandStack.pop();
        if (result % 1 < 0.0000001) return Integer.toString((int) Math.round(result));
        else return result.toString();
    }

    private ArrayList<Object> toPostfix(ArrayList<Object> inputArray) {
        ArrayList<Object> outputArray = new ArrayList<>();
        Stack<String> operationStack = new Stack<>();

        for (Object object: inputArray) {
            if (object instanceof Double) {
                outputArray.add(object);
            } else if (object.equals("(")) {
                operationStack.push((String) object);
            } else if (object.equals(")")) {
                if (operationStack.empty()) return null;
                while (!operationStack.peek().equals("("))
                    outputArray.add(operationStack.pop());
                operationStack.pop();
            } else if (!operationStack.empty() && operationStack.peek().equals("(")) {
                operationStack.push((String) object);
            } else if (!operationStack.empty() && getPriority(operationStack.peek()) >= getPriority((String) object)) {
                outputArray.add(operationStack.pop());
                operationStack.add ((String) object);
            } else
                operationStack.push((String) object);
        }
        while (!operationStack.empty()) outputArray.add(operationStack.pop());
        return outputArray;
    }

    private ArrayList<Object> parse(String statement) {
        if (statement == null || statement.isEmpty()) return null;
        ArrayList<Object> parsedList = new ArrayList<>();
        int scale = 0;
        String digits = "";
        for (char c: statement.toCharArray()) {
            if (c == '(' || c == ')' || c == '*' || c == '/' || c == '+' || c == '-') {
                if (digits.length() != 0) {
                    int number = Integer.parseInt(digits);
                    if (scale == 0)
                        parsedList.add((double)number);
                    else
                        parsedList.add((double)number / scale);
                    scale = 0;
                    digits = "";
                }
                parsedList.add(String.valueOf(c));
            } else if (c == '.' && scale == 0) {
                scale = 1;
            } else if (c >= '0' && c <= '9') {
                scale *= 10;
                digits += c;
            } else return null;
        }
        if (digits.length() > 0) {
            int number =  Integer.parseInt(digits);
            if (scale == 0)
                parsedList.add((double)number);
            else
                parsedList.add((double)number / scale);
        }
        return parsedList;
    }

    private int getPriority(String operation) {
        if (operation.equals("*") || operation.equals("/")) return 2;
        else return 1;
    }
}

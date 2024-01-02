import java.util.Arrays;
import java.util.List;

public class Calculator {
    private int num1;
    private int num2;
    private String operator;

    private int calcExp(int n1, String operator, int n2) {
        int result;

        switch (operator) {
            case "+":
                result = n1 + n2;
                break;
            case "-":
                result = n1 - n2;
                break;
            case "*":
                result = n1 * n2;
                break;
            case "/":
                result = n1 / n2;
                break;
            default:
                throw new AssertionError(); // Ошибка не должна возникнуть
        }
        return result;
    }

    public String result(String expression) throws CalculationException {
        Converter converter = new Converter();
        boolean isRomanExp;

        // Разбивка входной строки на части
        List<String> expressionItems = Arrays.asList(expression.split(" "));

        // Проверки:
        // Проверка количества элементов в выражении
        if (expressionItems.size() != 3) {
            throw new CalculationException("Выражение должно иметь вид ЧИСЛО ОПЕРАТОР ЧИСЛО, разделённые пробелом.");
        }

        // проверка оператора, должен быть: + - * /
        if (converter.checkOperator(expressionItems.get(1))) {
            operator = expressionItems.get(1);
        } else {
            throw new CalculationException("Некорректный оператор. Доступны: +, -, *, /");
        }

        // проверка чисел, должны быть оба арабские или оба римские
        if (converter.isArabian(expressionItems.get(0)) && converter.isArabian(expressionItems.get(2))) { // проверяем, что оба числа арабские
            num1 = Integer.parseInt(expressionItems.get(0));
            num2 = Integer.parseInt(expressionItems.get(2));
            isRomanExp = false;
        } else if (converter.isRoman(expressionItems.get(0)) && converter.isRoman(expressionItems.get(2))) { // проверяем, что оба числа римские
            num1 = converter.romanToArabianConvert(expressionItems.get(0));
            num2 = converter.romanToArabianConvert(expressionItems.get(2));
            isRomanExp = true;
        } else { // числа не соответствуют
            throw new CalculationException("Оба числа должны быть или арабские, или римские.");
        }

        // проверка чисел, должны быть от 1 до 10 включительно
        if (!(num1 >= 1 && num1 <= 10) || !(num2 >= 1 && num2 <= 10)) {
            throw new CalculationException("Числа должны быть от 1 до 10 или от I до X включительно.");
        }

        // получаем результат
        int result = calcExp(num1, operator, num2);

        // если числа римские
        if (isRomanExp) {
            if (result < 1) {
                throw new CalculationException("В римской системе нет отрицательных чисел. Ответ должен быть больше нуля.");
            }
            return converter.arabToRomeConvert(Math.abs(result));
        }

        // возвращаем ответ
        return String.valueOf(result);
    }
}
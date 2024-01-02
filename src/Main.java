import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите выражение: ");
            String input = scanner.nextLine();
            String result = calc(input);
            System.out.println("Ответ: " + result);
        } catch (CalculationException e) {
            System.out.println();
        } finally {
            scanner.close();
        }
    }

    public static String calc(String input) throws CalculationException {
        Calculator calculator = new Calculator();
        return calculator.result(input);
    }
}

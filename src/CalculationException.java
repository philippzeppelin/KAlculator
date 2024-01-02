public class CalculationException extends Exception {
    public CalculationException() {
        System.out.println("Возникла ошибка:");
    }

    public CalculationException(String message) {
        this();
        System.out.println(message);
    }
}

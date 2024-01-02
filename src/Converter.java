import java.util.HashMap;
import java.util.Map;

public class Converter {
    // соответствие римские-арабские числа (I..X, 1..10)
    private final Map<String, Integer> romeArabMap = new HashMap<>();

    //соответствие римские-арабские наименования чисел
    private final int[] arabDigit = new int[] {100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final String[] romeDigit = new String[] {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public Converter() {
        romeArabMap.put("I", 1);
        romeArabMap.put("II", 2);
        romeArabMap.put("III", 3);
        romeArabMap.put("IV", 4);
        romeArabMap.put("V", 5);
        romeArabMap.put("VI", 6);
        romeArabMap.put("VII", 7);
        romeArabMap.put("VIII", 8);
        romeArabMap.put("IX", 9);
        romeArabMap.put("X", 10);
    }

    // проверка, что это арабское число
    public boolean isArabian(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // проверка, что это римское число (ограничение I..X)
    public boolean isRoman(String str) {
        return romeArabMap.containsKey(str);
    }

    // конвертация из римского в арабское число (ограничение I..X)
    public Integer romanToArabianConvert(String str) {
        if (romeArabMap.containsKey(str)) {
            return romeArabMap.get(str);
        }
        return null;
    }

    // конвертация из арабского в римское число
    public String arabToRomeConvert(Integer num) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arabDigit.length; i++) {
            while((num - arabDigit[i]) >= 0) {
                num -= arabDigit[i];
                result.append(romeDigit[i]);
            }
        }
        return result.toString();
    }

    // проверка корректности оператора, должно быть: + - * /
    public boolean checkOperator(String operator) {
        return "*".equals(operator) || "/".equals(operator) || "+".equals(operator) || "-".equals(operator);
    }
}
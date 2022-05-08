import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите уравнение:");
        String input = scanner.nextLine();
        String result = calc(input);
        System.out.println(result);
    }

    public static String calc (String input){
        boolean isRoman = false;
        int fistDigit = 0;
        int secondDigit = 0;
        Numbers numbers = new Numbers();
        // Нормализуем ввод, убираем все пробелы, чтобы работало если строку ввели с пробелами и без
        input = input.replace(" ", "");

        String[] inputStr = input.split("([/*+\\-])"); //Сплитим входящую строку по знаку операции

        // если длина массива отличается от 2, значит нам ввели некорректный пример, значит смело бросаем exception

        if (inputStr.length != 2) try {
            throw new IOException();
        } catch (IOException e) {
            System.out.println("Введено некорректное уравнение.");
            System.exit(1);
        }
        // теперь надо проверить , что числа корректны
        boolean isArabic = numbers.isArabic(inputStr[0]) && numbers.isArabic(inputStr[1]);

        if (!isArabic) {
            try {
                fistDigit = numbers.romanToArabic(inputStr[0]);
                secondDigit = numbers.romanToArabic(inputStr[1]);
                isRoman = true;
            } catch (NullPointerException e) {
                System.out.println("Введено некорректное уравнение.");
                System.exit(1);
            }
    } else {
            fistDigit = Integer.parseInt(inputStr[0]);
            secondDigit = Integer.parseInt(inputStr[1]);
        }

        if (isRoman && (fistDigit > 10 | secondDigit > 10))try {
            throw new IOException();
        } catch (IOException e) {
            System.out.println("Введено некорректное уравнение.");
            System.exit(1);
        }


        String operation = numbers.getOperation(inputStr[0], input);

        int digitResult = numbers.calculate(fistDigit, secondDigit, operation);

        if (isRoman && digitResult < 1)  try {
            throw new IOException();
        } catch (IOException e) {
            System.out.println("Римские цифры не могут быть <=0");
            System.exit(1);
        }
        else if (isRoman){
            return numbers.arabicToRoman(digitResult);
        }
        return Integer.toString(digitResult);
    }

}

//Создаем класс , чтобы все операции с числами производить с помощью методов данного класса
class Numbers{
    boolean isArabic(String num){             // проверяем, что число арабское и  0< num <=10
        boolean result;
        try {
            int digit = Integer.parseInt(num);
            result = (digit > 0) && (digit <= 10);
        } catch (NumberFormatException e) { // если распарсить не получилось, значит точно не арабское число
            result = false;
        }
        return result;
    }

    String getOperation (String fistDigit, String fullString){
        return fullString.substring(fistDigit.length(), fistDigit.length()+1); //возвращаем следующий символ после 1 цифры
    }

    int calculate(int fistDigit, int secondDigit, String operation){
        int result = 0;
        switch (operation){
            case ("+") -> result = fistDigit + secondDigit;
            case ("-") -> result = fistDigit - secondDigit;
            case ("*") -> result = fistDigit * secondDigit;
            case ("/") -> result = fistDigit / secondDigit;
        }
        return result;
    }

    int romanToArabic(String string){
        int res = 0;
        HashMap<Character, Integer> romanian = new HashMap<>();
        romanian.put('I', 1);
        romanian.put('V', 5);
        romanian.put('X', 10);
        romanian.put('L', 50);
        romanian.put('C', 100);
        romanian.put('D', 500);
        romanian.put('M', 1000);

        for (int i=0; i < string.length(); i++){
            if (i+1 < string.length() &&
                    romanian.get(string.charAt(i)) < romanian.get(string.charAt(i+1))){
                res -= romanian.get(string.charAt(i));
            } else {
                res += romanian.get(string.charAt(i));
            }
        }
        return res;
    }

    public String arabicToRoman(int num) {
        int[] value = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] roman = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder res = new StringBuilder();

        for (int i=0; i < value.length; i++){
            while (num >= value[i]){
                num -= value[i];
                res.append(roman[i]);
            }
        }
        return res.toString();
    }

}
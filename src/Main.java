import java.io.IOException;
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

        if (!isArabic){

        }

        System.out.println(isArabic);



        // получаем операцию
        String operation = numbers.getOperation(inputStr[0], input);


        System.out.println(numbers.calculate(Integer.parseInt(inputStr[0]), Integer.parseInt(inputStr[1]), operation));
        return "";
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

    boolean isRoman(String num){


        return true;
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
}
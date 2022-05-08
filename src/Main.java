import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static String calc (String input){
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





        System.out.println(Arrays.asList(inputStr));

        return "";
    }

    public boolean isArabic (String digit){
        int dig = Integer.parseInt(digit);
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите уравнение:");

        String input = scanner.nextLine();

        String result = calc(input);

        System.out.println(result);
    }
}
//Создаем класс , чтобы все операции с числами производить с помощью методов данного класса
class numbers{
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

}
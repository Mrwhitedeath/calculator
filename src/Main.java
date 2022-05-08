import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите уравнение:");

        String input = scanner.nextLine();
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

        System.out.println(Arrays.asList(inputStr));



    }
}
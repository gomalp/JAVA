/*
Создай консольное приложение “Калькулятор”. Приложение должно читать из консоли введенные пользователем строки, числа,
арифметические операции проводимые между ними и выводить в консоль результат их выполнения.
Реализуй класс Main с методом public static String calc(String input). Метод должен принимать строку с арифметическим выражением
между двумя числами и возвращать строку с результатом их выполнения. Ты можешь добавлять свои импорты, классы и методы.
Добавленные классы не должны иметь модификаторы доступа (public или другие)


Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.
Данные передаются в одну строку (смотри пример)!
Решения, в которых каждое число и арифметическая операция передаются с новой строки считаются неверными.
Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по величине и могут быть любыми.
Калькулятор умеет работать только с целыми числами.
При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
Результатом операции деления является целое число, остаток отбрасывается.
Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
 */
package by.teachme.calculator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    public static String INVITE_MESSAGE ="Enter the expression:";
    protected static String ERROR_ARIFMETIC_OP ="Invalid arithmetic operation!";
    protected static String ERROR_INPUT ="Invalid input data: ";
    protected static String WARNING ="User keep your eyes open. You made a mistake!";
    protected static String REGEXP="^([0-9]|10)([\\+\\-\\*\\/])([0-9]|10)$";

    public static String calc(String input)  {
        int result = 0;
        try {
            String[] elements= parseExpress(input);
            String operation=elements[1];
            int a=Integer.parseInt(elements[0]);
            int b=Integer.parseInt(elements[2]);
            result = switch (operation) {
                case "+" -> a+b;
                case "-" -> a-b;
                case "/" -> a/b;
                case "*" -> a*b;
                default -> throw new IllegalArgumentException(ERROR_ARIFMETIC_OP);
            };
        } catch (Exception e){
        System.out.println(ERROR_INPUT + e.getMessage());
        System. exit(1);
        }
        return String.valueOf(result);
    }

    static String [] parseExpress(String express)throws InputException{
        String stringPattern =REGEXP ;
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(express);
        if (matcher.find()) {
            String digitOne = matcher.group(1);
            String operation = matcher.group(2);
            String digitTwo = matcher.group(3);
            return new String[]{digitOne, operation, digitTwo};
        } else throw new InputException(WARNING);
    }

    static String getConsoleExp() {
        System.out.println(INVITE_MESSAGE);
        return in.nextLine();
    }

    public static void main(String[] args) {
            System.out.println("Output:");
            System.out.println(calc(getConsoleExp()));
    }
}

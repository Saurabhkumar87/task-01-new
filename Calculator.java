import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" first number : ");
        int num1 = sc.nextInt();
        System.out.println(" second number : ");
        int num2 = sc.nextInt();
        System.out.println(" select operator ('+' , '-' , '*' ,'/' )");
        char opr = sc.next().charAt(0);
        int result;

        switch (opr) {
            case '+':
                result = num1 + num2;
                System.out.println("Result: " + result);
                break;
            case '-':
                result = Math.abs(num1 - num2);
                System.out.println("Result: " + result);
                break;
            case '*':
                result = num1 * num2;
                System.out.println("Result: " + result);
                break;
            case '/':
                result = num1 / num2;
                System.out.println("Result: " + result);
                break;
            default:
                System.out.println(" pls select correct operator ");
        }
    }
}

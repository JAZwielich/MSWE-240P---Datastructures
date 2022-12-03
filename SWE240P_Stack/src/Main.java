public class Main {
    public static void main(String[] args) {
        String equation = "4 * 20 / 20 / 3";
        Stack stackEquation = new Stack<>();
        System.out.println(stackEquation.solveStack(equation));
    }
}
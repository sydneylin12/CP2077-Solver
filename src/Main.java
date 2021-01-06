import java.util.List;

/**
 * Testing class
 */
public class Main {
    public static void main(String[] args) {
        BreachProtocol b = new BreachProtocol(5);
        String testBoard1 =
                "BD E9 1C BD BD\n" +
                "1C 1C BD BD 55\n" +
                "BD BD 55 BD BD\n" +
                "1C 55 55 E9 BD\n" +
                "55 1C BD 55 55";
        String testCase1 = "BD BD 55 1C E9 BD";

        b.parseBoard(testBoard1);
        b.search(testCase1);
    }
}

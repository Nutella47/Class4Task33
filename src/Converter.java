import java.util.*;


public class Converter {

    private Scanner scanner;
    private Map<Character, Integer> hexaDecimalLetterToDecimal;
    private Map<Integer, Character> decimalToHexaDecimalLetter;
    public Converter() {
        this.scanner = new Scanner(System.in);
        this.hexaDecimalLetterToDecimal = new HashMap<>();
        this.hexaDecimalLetterToDecimal.put('A', 10);
        this.hexaDecimalLetterToDecimal.put('B', 11);
        this.hexaDecimalLetterToDecimal.put('C', 12);
        this.hexaDecimalLetterToDecimal.put('D', 13);
        this.hexaDecimalLetterToDecimal.put('E', 14);
        this.hexaDecimalLetterToDecimal.put('F', 15);

        this.decimalToHexaDecimalLetter = new HashMap<>();
        this.decimalToHexaDecimalLetter.put(10, 'A');
        this.decimalToHexaDecimalLetter.put(11, 'B');
        this.decimalToHexaDecimalLetter.put(12, 'C');
        this.decimalToHexaDecimalLetter.put(13, 'D');
        this.decimalToHexaDecimalLetter.put(14, 'E');
        this.decimalToHexaDecimalLetter.put(15, 'F');
    }

    private final Set<Character> allowedOperations = new HashSet<>(Arrays.asList('A','B','C','D','E','F','W'));
    private final char notValidOperation = '0';
    public void printMenu() {
        System.out.println("Choose what operation you want to perform:\n");
        System.out.println("A. Convert from any system to the decimal system");
        System.out.println("B. Convert from decimal system to another system");
        System.out.println("C. Summing up numbers from different systems");
        System.out.println("D. Subtracting numbers from different systems");
        System.out.println("E. Multiplying numbers from different systems");
        System.out.println("F. Dividing numbers from different systems\n");
        System.out.println("W. Exit the program");
    }

    public char readOperation() {
        char operation = this.scanner.next().charAt(0);
        if (!allowedOperations.contains(operation)) {
            return notValidOperation;
        }
        return operation;
    }

    private int convertFromAnySystemToDecimal() {
        System.out.println("Please enter the number");
        String number = this.scanner.next();
        System.out.println("Please enter the base");
        int base = this.scanner.nextInt();
        return toDecimal(number, base);
    }


    private String convertFromDecimalToAnySystem() {
        System.out.println("Please enter the number");
        int number = this.scanner.nextInt();
        System.out.println("Please enter the base");
        int base = this.scanner.nextInt();
        return toAnyBase(number, base);
    }

    private void addAnySystem() {
        int number1Int = readFirstNumber();
        int number2Int = readSecondNumber();

        int resultDecimal = number1Int + number2Int;

        System.out.println("Please enter the base for the result");
        int baseResult = this.scanner.nextInt();
        System.out.println("The result is " + toAnyBase(resultDecimal, baseResult));
    }

    private void substractAnySystem() {
        int number1Int = readFirstNumber();
        int number2Int = readSecondNumber();

        int resultDecimal = number1Int - number2Int;

        System.out.println("Please enter the base for the result");
        int baseResult = this.scanner.nextInt();
        System.out.println("The result is " + toAnyBase(resultDecimal, baseResult));
    }

    private void multiplyAnySystem() {
        int number1Int = readFirstNumber();
        int number2Int = readSecondNumber();

        int resultDecimal = number1Int * number2Int;

        System.out.println("Please enter the base for the result");
        int baseResult = this.scanner.nextInt();
        System.out.println("The result is " + toAnyBase(resultDecimal, baseResult));
    }

    private void divisionAnySystem() {
        int number1Int = readFirstNumber();
        int number2Int = readSecondNumber();

        int resultDecimal = number1Int / number2Int;

        System.out.println("Please enter the base for the result");
        int baseResult = this.scanner.nextInt();
        System.out.println("The result is " + toAnyBase(resultDecimal, baseResult));
    }

    public void performOperation(char operation) {
        switch (operation) {
            case 'A':
                int result = convertFromAnySystemToDecimal();
                System.out.println("The result is " + result + "\n");
                break;
            case 'B':
                String resultString = convertFromDecimalToAnySystem();
                System.out.println("The result is " + resultString + "\n");
                break;
            case 'C':
                addAnySystem();
                break;
            case 'D':
                substractAnySystem();
                break;
            case 'E':
                multiplyAnySystem();
                break;
            case 'F':
                divisionAnySystem();
                break;
            default:
                break;
        }
    }

    public int toDecimal(String number, int base) {
        int power = 0, result = 0;
        while (!number.isEmpty()) {
            char remainder = number.charAt(number.length() - 1);
            number = number.substring(0, number.length() - 1);
            int remainderNumber;
            if (!Character.isDigit(remainder)) {
                remainderNumber = this.hexaDecimalLetterToDecimal.get(remainder);
            } else {
                remainderNumber = Character.getNumericValue(remainder);
            }

            result = (int) (result + remainderNumber * Math.pow(base, power));
            power = power + 1;
        }
        return result;
    }

    public String toAnyBase(int number, int base) {
        StringBuilder result = new StringBuilder();
        while (number > 0)
        {
            int remainder = number % base;
            char remainderSymbol;
            if (remainder > 9) {
                remainderSymbol = this.decimalToHexaDecimalLetter.get(remainder);
            } else {
                remainderSymbol = Integer.toString(remainder).charAt(0);
            }
            number = number / base;
            result.insert(0, remainderSymbol);
        }
        return result.toString();
    }

    public int readFirstNumber() {
        System.out.println("Please enter the first number");
        String number1 = this.scanner.next();
        System.out.println("Please enter the base of the first number");
        int base1 = this.scanner.nextInt();
        return toDecimal(number1, base1);
    }

    public int readSecondNumber() {
        System.out.println("Please enter the second number");
        String number2 = this.scanner.next();
        System.out.println("Please enter the base of the second number");
        int base2 = this.scanner.nextInt();
        return toDecimal(number2, base2);
    }
}

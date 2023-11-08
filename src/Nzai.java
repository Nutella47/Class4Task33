public class Nzai {
    public static void main(String[] args) {
        Converter converter = new Converter();

        while (true) {
            converter.printMenu();
            char operation = converter.readOperation();
            if (operation == 'W') {
                System.out.println("Exiting the program");
                break;
            }
            if (operation == '0') {
                System.out.println("Wrong operation. Please choose from the list");
                continue;
            }

            converter.performOperation(operation);
        }

    }
}

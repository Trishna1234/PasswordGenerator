import java.util.Scanner;

public class Generator {

    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner){
        keyboard = scanner;
    }

    public Generator(boolean includeUpper, boolean includeLower, boolean includeNumber, boolean includeSymbol){
        alphabet = new Alphabet(includeUpper, includeLower, includeNumber, includeSymbol);
    }

    public void mainLoop(){
        System.out.println("Welcome to Password Generator");
        printMenu();

        String userOption = "-1";

        while ((!userOption.equals("4"))){
            userOption = keyboard.next();

            switch (userOption){
                case "1" -> {
                    requestPassword();
                    printMenu();
                }
                case "2" -> {
                    checkPassword();
                    printMenu();
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                }
                case "4" -> {
                    printQuitMessage();
                }
                default -> {
                    System.out.println();
                    System.out.println("Kindly select one of the available choice");
                    printMenu();
                }
            }
        }
    }

    private void printUsefulInfo() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase & uppercase alphabetic characters, numbers & symbols if permitted");
        System.out.println("Generate password randomly where feasible");
        System.out.println("Avoid using same password twice (e.g., across multiple user accounts and/or software systems");
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences," +
                "\nusernames, relative or pet names and biographical information (e.g., ID numbers, ancestors' names or dates");
        System.out.println("Avoid using information that the user's colleagues and/or " +
                "acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    }

    private void checkPassword() {
        String input;

        System.out.print("\nEnter your password: ");
        input = keyboard.next();

        final Password p =new Password(input);

        System.out.println(p.calculateScore());
    }

    private void requestPassword() {
        boolean includeUpper = false;
        boolean includeLower = false;
        boolean includeNumber = false;
        boolean includeSymbol = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Welcome to the Password Generator :) answer the following questions bt Yes or No \n");

        do{
            String input;
            correctParams = false;

            do{
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            }while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if(isInclude(input)) includeLower = true;

            do{
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            }while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if(isInclude(input)) includeUpper = true;

            do{
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            }while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if(isInclude(input)) includeNumber = true;

            do{
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            }while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if(isInclude(input)) includeSymbol = true;


            if(!includeUpper && !includeLower && !includeNumber && !includeSymbol){
                System.out.println("You have selected no characters to generate your password, at least one of your answers needs to be yes\n");
                correctParams = true;
            }
        } while (correctParams);

        System.out.println("Now enter the length of the password");
        int length = keyboard.nextInt();

        final Generator generator = new Generator(includeUpper, includeLower, includeNumber, includeSymbol);
        final Password password = generator.GeneratePassword(length);

        System.err.println("Your Generated password -> " + password);
    }

    private Password GeneratePassword(int length) {
        final StringBuilder pass = new StringBuilder("");
        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;
        for(int i=0;i<length;i++){
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }

    private void PasswordRequestError(String input) {
        if(!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"))
            System.out.println("You have entered something incorrect let's go over it again \n");
    }

    private boolean isInclude(String input) {
        if(input.equalsIgnoreCase("yes"))
            return true;
        else return false;
    }

    private void printMenu(){
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Enter choice: ");
    }

    private void printQuitMessage(){
        System.out.println("Closing the Program");
    }
}

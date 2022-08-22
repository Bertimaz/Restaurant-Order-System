import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;



/*Aplicativo que simula pedidos no Berti's Burger*/

public class Main {
    public static void main(String[] args) {

        //Inicializa Variaveis
        Scanner sc = new Scanner(System.in);
        Hamburger orderArray[] = new Hamburger[10];
        int orderCount = 0;
        ExtrasArray extrasArrayObj = new ExtrasArray();
        String extrasArray[] = extrasArrayObj.getExtrasArray();
        double extrasArrayPrice[] = extrasArrayObj.getExtrasArrayPrice();


        while (true) //Loop Principal. Imprime opções de pedido e guia até a finalização
        {
            //Iniciliza variavel
            int optionSelected = -1;

            //Imprime opções iniciais
            printBurgerOption();


            if (sc.hasNextInt()) //se usuario adicionou opção válida, salva a opção
            {
                optionSelected = sc.nextInt();
                sc.nextLine();
            } else //Caso contrário reinicia o loop
            {
                System.out.println("\nInvalid Number code:" + optionSelected);
                sc.nextLine();
                continue;
            }

            if (optionSelected == 1) //Seleciona classic hamburguer
            {
                orderArray[orderCount] = new Hamburger("Classic Burger", 5, "Simple Burger", "White Bread");

            } else if (optionSelected == 2) //Seleciona Hamburguer Healthy
            {
                orderArray[orderCount] = new HealthyBurger();
            } else if (optionSelected == 3) //Seleciona pedido deluxe
            {
                orderArray[orderCount] = new DeluxeBurger();
            } else //se numero invalido, reinicia Loop
            {
                System.out.println("Invalid Entry, try again.\n");

                continue;
            }

            while (orderArray[orderCount].canIAddMoreExtras())  //Loop Secundário. Imprime opções de acompanhamentos até finalização do hamburguer
            {
                System.out.println("Would you like to add any Extras? You have more " + (orderArray[orderCount].howManyExtrasMoreCanIGet()) + " extras in " + orderArray[orderCount].getClass().getSimpleName() + "\n");
                printExtrasList(extrasArray);

                if (sc.hasNextInt()) {
                    optionSelected = sc.nextInt();
                    sc.nextLine();
                } else {
                    System.out.println("\nInvalid Entry:" + optionSelected);
                    sc.nextLine();
                    continue;
                }


                if (optionSelected > 0 && optionSelected <= extrasArray.length) // se escolher outro extra dentro das opções
                    orderArray[orderCount].addExtra(extrasArray[optionSelected - 1], extrasArrayPrice[optionSelected - 1]);
                else if (optionSelected == 0) { // se escolher finalizar hamburger
                    break;
                } else { //se digitar código invalido
                    System.out.println("Invalid Entry:" + optionSelected);
                }
                if (!orderArray[orderCount].canIAddMoreExtras()) { //se acabar o espaco no hamburger
                    System.out.println("Your Burger is Full");
                    break;
                }

            }
            System.out.println(orderCount + 1 + "º Hamburger added: " + orderArray[orderCount].getClass().getSimpleName()); //Imprime Item Adicionado
            orderCount++;

            boolean isValidCode = false;
            while (!isValidCode) //Loop Secundario. Verifica se há novos pedidos
            {
                System.out.println("Do you have any other order? \n");
                System.out.println("1 - Yes. \n2 - No.");
                if (sc.hasNextInt()) {
                    optionSelected = sc.nextInt();
                    if (optionSelected == 1 || optionSelected == 2) {
                        isValidCode = true;
                    } else System.out.println("Invalid Entry");
                } else System.out.println("Invalid Entry");
                sc.nextLine();
            }


            if (optionSelected == 1) // Se houver novos pedidos, recomeca o Loop Principal
            { // tiver outros pedidos
                continue;
            } else //Caso Contrário encerra.
            {
                break;
            }

        }


        finalizarPedido(orderArray, orderCount); //Finaliza Pedido


    }

    static void finalizarPedido(Hamburger orderArray[], int orderCount) {
        double price = 0;


        for (int i = 0; i < orderCount; i++) {
            price = price + orderArray[i].getTotalPrice();
        }

        System.out.println("Order Closed\n" +
                " Total Price:" + currencyDolars(price) + "\n ");

        for (int i = 0; i < orderCount; i++) {
            orderArray[i].endOrder();
        }


    }

    static void printExtrasList(String extrasArray[]) {
        for (int i = 0; i < extrasArray.length; i++) {        //imprime os extras
            System.out.println((i + 1) + " - " + extrasArray[i]);
        }
        System.out.println("0 - I don't want any more deliciousness");
    }

    static void printBurgerOption() {
        System.out.println("What is your Type of Hamburger? \n" +
                "1 - Classic Hamburger. Simple Burger on White Bread \n" +
                "2 - Healthy Burger. Picanha Burger on delicious Brown Rye Bread \n" +
                "3 - Deluxe Burger. Sausage and Bacon Burger on White Bread Combo, with chips and drink \n");
    }


    static int readNextInt(Scanner sc) {

        while (true) {
            try {
                return sc.nextInt();


            } catch (Exception e) {
                //  System.out.println("Enter a valid number");
            }
            sc.nextLine();
        }


    }

    public static String currencyDolars(double value) {
        BigDecimal decimalValue = new BigDecimal(value);
        double amt = 123.456;

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        // System.out.println(formatter.format(value));

        return formatter.format(value);

    }
}


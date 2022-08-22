import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;


/*Objeto Hamburger*/

public class Hamburger {
    private String burgerName;
    private double basePrice;
    private int additionQuantity;  //Quantidade de extras adicionados
    private double totalPrice;     // Preco Total
    private String meatType;
    private String breadRollType;   //Tipo de Pao

    private String extrasNameArray[] = new String[10];
    private double extrasPriceArray[] = new double[10];

    private int maximumExtras;


    public Hamburger(String burguerName, double basePrice, String meatType, String breadRollType) {
        this.burgerName = burguerName;
        this.basePrice = basePrice;
        this.meatType = meatType;
        this.breadRollType = breadRollType;
        this.additionQuantity = 0;
        this.totalPrice = basePrice;
        maximumExtras = 4;
    }

    public int getMaximumExtras() {
        return maximumExtras;
    }

    public void addExtra(String extraName, double extraPrice) //addExtra recebe dois parametros extraName e extraPrice. Adiciona extras no hamburguer até atingir o maximumExtras
    {
        if (additionQuantity < maximumExtras) {
            this.extrasNameArray[additionQuantity] = extraName;
            this.extrasPriceArray[additionQuantity] = extraPrice;
            System.out.println("Added " + extraName + " for " + currencyDolars(extraPrice));
            additionQuantity++;
            totalPrice += extraPrice;
        } else {
            extraLimitReached(extraName);
        }
    }

    public void extraLimitReached(String extraName) // Imprime mensagem de limite atigindo
    {
        System.out.println("Extra's limit reached. " + extraName + " wasn't added");

    }

    public void endOrder() //Imprime o preço detalhado do hamburger
    {
        int price = 0;
        System.out.println(("    ")+getClass().getSimpleName() + " on " + breadRollType + " with " + meatType + "\n"
                + "    "+"    "+"Burger Total Price: " + currencyDolars(totalPrice) + " \n"
                +"    "+"    "+ "Base Price: " + currencyDolars(basePrice));
        for (int i = 0; i < additionQuantity; i++) {
            System.out.println("    "+"    "+"    "+"Extra nº" + (i + 1) + ". " + extrasNameArray[i] + ". Price: " + currencyDolars(extrasPriceArray[i]) + " \n");
            //imprimir o preco dos extras

        }

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public static String currencyDolars(double value) {
        BigDecimal decimalValue = new BigDecimal(value);
        double amt = 123.456;

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        // NumberFormat formatter = NumberFormat.getCurrencyInstance();
        // System.out.println(formatter.format(value));

        return formatter.format(value);

    }


    public void setMaximumExtras(int maximumExtras) {
        this.maximumExtras = maximumExtras;
    }


    public boolean canIAddMoreExtras() {
        if (maximumExtras > additionQuantity) {
            return true;
        } else {
            return false;
        }
    }

    public int howManyExtrasMoreCanIGet() {
        return maximumExtras - additionQuantity;
    }
}

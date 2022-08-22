public class HealthyBurger extends Hamburger{
    /*Objeto HealthyBurger
     *
     * Base Price = 6
     * BreadRollType = Brwon Rye Bread
     * Limite de Extras = 6
     * MeatType=Picanha
     *
     */

    private String[] extrasNameArray = new String[10];
    private double[] extrasPriceArray = new double[10];

    public HealthyBurger() {
        super("Healthy Burger", 6, "Picanha","Brown Rye Bread");
        this.setMaximumExtras(6);

    }

    }


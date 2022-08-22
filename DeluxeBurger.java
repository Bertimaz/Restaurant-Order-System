public class DeluxeBurger extends Hamburger {
/*Objeto DeluxeBurger
* basePrice=5
* meatType=Sausage And Bacon
* BreadRollType = White Bread
* Extras = chips +drink
* Sem Extras adicionais
*/

    public DeluxeBurger() {
        super("Deluxe Burger", 5,"Sausage and Bacon","White Bread" );
        this.setMaximumExtras(2);
        this.addExtra("Chips", 5);
        this.addExtra("Drink", 2);
    }

    @Override
    public void extraLimitReached(String extraName) {
        System.out.println("Cannot Add Extra to Deluxe Burger. " + extraName + " wasn't added");
    }
}

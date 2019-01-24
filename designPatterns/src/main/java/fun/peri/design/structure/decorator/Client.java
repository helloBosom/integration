package fun.peri.design.structure.decorator;

public class Client {

    public static void main(String[] args) {
        Beverage beverage = new HouseBlend();
        beverage = new Mocha(beverage);
        beverage = new Milk(beverage);
        System.out.println(beverage.cost());
        Beverage darkRoast = new DarkRoast();
        darkRoast = new Milk(darkRoast);
        darkRoast = new Mocha(darkRoast);
        System.out.println(darkRoast.cost());
    }

}

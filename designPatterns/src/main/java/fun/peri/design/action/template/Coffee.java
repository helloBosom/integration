package fun.peri.design.action.template;

public class Coffee extends CaffeineBeverage {

    void brew() {
        System.out.println("Coffee.brew");
    }

    @Override
    void addCondiments() {
        System.out.println("Coffee.addCondiments");
    }

}

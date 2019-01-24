package fun.peri.design.action.template;

public class Tea extends CaffeineBeverage {

    @Override
    void brew() {
        System.out.println("Tea.brew");
    }

    @Override
    void addCondiments() {
        System.out.println("Tea.addCondiments");
    }

}

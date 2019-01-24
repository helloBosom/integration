package fun.peri.design.action.strategy;

public class Bark implements BarkBehavior {

    @Override
    public void bark() {
        System.out.println("bark");
    }

}

package fun.peri.design.action.strategy;

public class Squeak implements BarkBehavior {

    @Override
    public void bark() {
        System.out.println("squeak!");
    }

}

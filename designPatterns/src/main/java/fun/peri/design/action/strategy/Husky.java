package fun.peri.design.action.strategy;

public class Husky {

    private BarkBehavior barkBehavior;

    public void performBark() {
        if (barkBehavior != null) {
            barkBehavior.bark();
        }
    }

    public BarkBehavior getBarkBehavior() {
        return barkBehavior;
    }

    public void setBarkBehavior(BarkBehavior barkBehavior) {
        this.barkBehavior = barkBehavior;
    }

}

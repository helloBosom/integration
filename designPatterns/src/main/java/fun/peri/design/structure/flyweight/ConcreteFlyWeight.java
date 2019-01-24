package fun.peri.design.structure.flyweight;

public class ConcreteFlyWeight implements Flyweight {

    private String intrinsicState;

    public ConcreteFlyWeight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void doOperation(String extrinsicState) {
        System.out.println("Object address:" + System.identityHashCode(this));
        System.out.println("IntrinsicState:" + intrinsicState);
        System.out.println("ExtrinsicState:" + extrinsicState);
    }
    
}

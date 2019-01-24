package fun.peri.design.create.factory.method;

public class SecondFactory extends Factory {
    @Override
    public Product getFactory() {
        return new SecondImpl();
    }
}

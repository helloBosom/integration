package fun.peri.design.create.factory.method;

public class FirstFactory extends Factory{

    @Override
    public Product getFactory() {
        return new FirstImpl();
    }
}

package fun.peri.design.create.factory.abstractfactory;

public class ConcreteFactoryTwo extends AbstractFactory {
    @Override
    FirstAbstractProduct createFirstProduct() {
        return new FirstProductTwo();
    }

    @Override
    SecondAbstractProduct createSecondProduct() {
        return new SecondProductTwo();
    }
}

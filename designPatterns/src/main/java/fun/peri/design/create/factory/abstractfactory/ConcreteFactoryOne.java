package fun.peri.design.create.factory.abstractfactory;

public class ConcreteFactoryOne extends AbstractFactory{
    @Override
    FirstAbstractProduct createFirstProduct() {
        return new FirstProductOne();
    }

    @Override
    SecondAbstractProduct createSecondProduct() {
        return new SecondProductOne();
    }
}

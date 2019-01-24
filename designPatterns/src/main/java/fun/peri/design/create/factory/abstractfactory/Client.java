package fun.peri.design.create.factory.abstractfactory;

public class Client {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new ConcreteFactoryOne();
        FirstAbstractProduct firstProduct = abstractFactory.createFirstProduct();
        SecondAbstractProduct secondProduct = abstractFactory.createSecondProduct();
        System.out.println(firstProduct.getClass());
        System.out.println(secondProduct.getClass());
    }
}

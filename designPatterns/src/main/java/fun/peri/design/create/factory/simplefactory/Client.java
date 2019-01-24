package fun.peri.design.create.factory.simplefactory;

public class Client {

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Product product = simpleFactory.createProduct("1");
        System.out.println(product.getClass());
    }

}

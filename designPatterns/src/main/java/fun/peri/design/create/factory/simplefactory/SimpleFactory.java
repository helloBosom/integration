package fun.peri.design.create.factory.simplefactory;

public class SimpleFactory {

    public Product createProduct(String type) {
        switch (type) {
            case "1":
                return new FirstImpl();
            case "2":
                return new SecondImpl();
            case "3":
                return new ThirdImpl();
        }
        return new FirstImpl();
    }

}

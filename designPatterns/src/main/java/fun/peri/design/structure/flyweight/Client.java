package fun.peri.design.structure.flyweight;

public class Client {

    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        Flyweight flyweightOne = flyweightFactory.getFlyweight("snk");
        Flyweight flyweightTwo = flyweightFactory.getFlyweight("snk");
        flyweightOne.doOperation("yes");
        flyweightTwo.doOperation("no");
    }

}

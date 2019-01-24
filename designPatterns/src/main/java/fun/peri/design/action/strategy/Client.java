package fun.peri.design.action.strategy;

public class Client {

    public static void main(String[] args) {
        Husky husky = new Husky();
        husky.setBarkBehavior(new Squeak());
        husky.performBark();
        husky.setBarkBehavior(new Bark());
        husky.performBark();
    }

}

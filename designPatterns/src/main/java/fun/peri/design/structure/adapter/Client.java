package fun.peri.design.structure.adapter;

public class Client {

    public static void main(String[] args) {
        Wolf wolf = new WildWolf();
        Husky husky = new HuskyAdapter(wolf);
        husky.bark();
    }

}

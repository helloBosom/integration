package fun.peri.design.create.prototype;

public class Client {

    public static void main(String[] args) {
        MainPrototype prototype = new ConCretePrototype("hello world");
        MainPrototype clone = prototype.prototypeClone();
        System.out.println(clone);
    }

}

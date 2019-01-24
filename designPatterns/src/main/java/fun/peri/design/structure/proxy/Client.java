package fun.peri.design.structure.proxy;

public class Client {

    public static void main(String[] args) {
        Resolution resolution = new Resolution(System.currentTimeMillis());
        EntityProxy entityProxy = new EntityProxy(resolution);
        entityProxy.doSomething();
    }

}

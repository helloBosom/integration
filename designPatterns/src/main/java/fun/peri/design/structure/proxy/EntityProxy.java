package fun.peri.design.structure.proxy;

public class EntityProxy implements Entity {

    private Resolution resolution;

    public EntityProxy(Resolution resolution) {
        this.resolution = resolution;
    }

    @Override
    public void doSomething() {
        while (!resolution.isLoad()) {
            System.out.println("entity");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resolution.doSomething();
    }

}

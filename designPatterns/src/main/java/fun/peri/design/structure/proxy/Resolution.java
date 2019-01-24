package fun.peri.design.structure.proxy;

public class Resolution implements Entity{

    private long startTime;

    public Resolution(long startTime) {
        this.startTime = startTime;
    }

    boolean isLoad(){
        return System.currentTimeMillis() - startTime > 3000;
    }

    @Override
    public void doSomething() {
        System.out.println();
    }

}

package fun.peri.design.structure.bridge;

public class Sony extends TV {

    @Override
    void on() {
        System.out.println("Sony.on()");
    }

    @Override
    void off() {
        System.out.println("Sony.off()");
    }

    @Override
    void tuneChannel() {
        System.out.println("Sony.tuneChannel");
    }

}

package fun.peri.design.structure.bridge;

public class ConcreteRemoteControlTwo extends RemoteControl {

    public ConcreteRemoteControlTwo(TV tv) {
        super(tv);
    }

    @Override
    void on() {
        System.out.println("ConcreteRemoteControlTwo.on()");
        tv.on();
    }

    @Override
    void off() {
        System.out.println("ConcreteRemoteControlTwo.off()");
        tv.off();
    }

    @Override
    void tuneChannel() {
        System.out.println("ConcreteRemoteControlTwo.tuneChannel()");
        tv.tuneChannel();
    }

}

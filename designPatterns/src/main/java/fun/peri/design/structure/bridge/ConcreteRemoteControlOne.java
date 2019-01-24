package fun.peri.design.structure.bridge;

public class ConcreteRemoteControlOne extends RemoteControl {

    public ConcreteRemoteControlOne(TV tv) {
        super(tv);
    }

    @Override
    void on() {
        System.out.println("ConcreteRemoteControlOne.on()");
        tv.on();
    }

    @Override
    void off() {
        System.out.println("ConcreteRemoteControlOne.off()");
        tv.off();
    }

    @Override
    void tuneChannel() {
        System.out.println("ConcreteRemoteControlOne.tuneChannel()");
        tv.tuneChannel();
    }

}

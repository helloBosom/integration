package fun.peri.design.structure.bridge;

public abstract class RemoteControl {

    TV tv;

    public RemoteControl(TV tv) {
        this.tv = tv;
    }

    abstract void on();

    abstract void off();

    abstract void tuneChannel();

}

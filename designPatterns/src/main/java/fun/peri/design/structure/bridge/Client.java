package fun.peri.design.structure.bridge;

public class Client {

    public static void main(String[] args) {
        RemoteControl remoteControlOne = new ConcreteRemoteControlOne(new TCL());
        remoteControlOne.on();
        remoteControlOne.off();
        remoteControlOne.tuneChannel();
        RemoteControl remoteControlTwo = new ConcreteRemoteControlTwo(new Sony());
        remoteControlTwo.on();
        remoteControlTwo.off();
        remoteControlTwo.tuneChannel();
    }

}

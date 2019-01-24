package fun.peri.design.structure.bridge;

public class TCL extends TV {

    @Override
    void on() {
        System.out.println("TCL.on()");
    }

    @Override
    void off() {
        System.out.println("TCL.off()");
    }

    @Override
    void tuneChannel() {
        System.out.println("TCL.tuneChannel()");
    }

}

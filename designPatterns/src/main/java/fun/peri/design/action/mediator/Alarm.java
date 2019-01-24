package fun.peri.design.action.mediator;

public class Alarm extends Colleague {

    public void doAlarm() {
        System.out.println("doAlarm()");
    }

    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("alarm");
    }

}

package fun.peri.design.action.mediator;

public class ConcreteMediator extends Mediator {

    private Alarm alarm;
    private Calender calender;
    private Sprinkler sprinkler;
    private CoffeePot coffeePot;

    public ConcreteMediator(Alarm alarm, Calender calender, Sprinkler sprinkler, CoffeePot coffeePot) {
        this.alarm = alarm;
        this.calender = calender;
        this.sprinkler = sprinkler;
        this.coffeePot = coffeePot;
    }

    @Override
    public void doEvent(String eventType) {
        switch (eventType) {
            case "alarm":
                doAlarmEvent();
                break;
            case "coffeePot":
                doCoffeePotEvent();
                break;
            case "calender":
                doCalenderEvent();
                break;
            case "sprinkler":
                doSprinklerEvent();
                break;
        }
    }

    public void doAlarmEvent() {
        alarm.doAlarm();
        coffeePot.doCoffeePot();
        calender.doCalender();
        sprinkler.doSprinkler();
    }

    public void doCalenderEvent() {

    }

    public void doSprinklerEvent() {

    }

    public void doCoffeePotEvent() {

    }

}

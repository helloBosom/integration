package fun.peri.design.action.mediator;

public class Client {

    public static void main(String[] args) {
        Alarm alarm = new Alarm();
        CoffeePot coffeePot = new CoffeePot();
        Calender calender = new Calender();
        Sprinkler sprinkler = new Sprinkler();
        Mediator mediator = new ConcreteMediator(alarm, calender, sprinkler, coffeePot);
        alarm.onEvent(mediator);
    }

}

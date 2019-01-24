package fun.peri.design.action.observer;

public class Client {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        new ConcreteObserverOne(subject);
        new ConcreteObserverTwo(subject);
        subject.setMeasurements(0, 0, 0);
        subject.setMeasurements(1, 1, 1);
    }

}

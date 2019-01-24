package fun.peri.design.action.observer;

public class ConcreteObserverOne implements Observer {

    public ConcreteObserverOne(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("ConcreteObserverOne.update:" + temperature + " " + humidity + " " + pressure);
    }

}

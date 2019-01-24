package fun.peri.design.action.observer;

public class ConcreteObserverTwo implements Observer {

    public ConcreteObserverTwo(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("ConcreteObserverTwo.update:" + temperature + " " + humidity + " " + pressure);
    }

}

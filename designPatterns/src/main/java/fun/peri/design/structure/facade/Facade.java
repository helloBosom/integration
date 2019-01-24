package fun.peri.design.structure.facade;

public class Facade {

    SubSystem subSystem = new SubSystem();

    void watchMovie() {
        subSystem.turnOnTV();
        subSystem.setCD("husky");
        subSystem.startWatching();
    }

}

package fun.peri.design.structure.adapter;

public class HuskyAdapter implements Husky {

    Wolf wolf;

    public HuskyAdapter(Wolf wolf) {
        this.wolf = wolf;
    }

    @Override
    public void bark() {
        wolf.howl();
    }

}

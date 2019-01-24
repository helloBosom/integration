package fun.peri.design.create.prototype;

public class ConCretePrototype extends MainPrototype{

    private String filed;

    public ConCretePrototype(String filed) {
        this.filed = filed;
    }

    @Override
    MainPrototype prototypeClone() {
        return new ConCretePrototype(filed);
    }

    public String toString(){
        return filed;
    }

}

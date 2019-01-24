package fun.peri.design.action.instancenull;

public class Client {

    public static void main(String[] args) {
        AbstractOperation abstractOperation = func(-1);
        abstractOperation.request();
    }

    static AbstractOperation func(int param) {
        if (param < 0) {
            return new NullOperation();
        }
        return new RealOperation();
    }

}

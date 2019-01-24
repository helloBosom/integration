package fun.peri.design.action.chain;

public abstract class Handler {

    Handler successor;

    public Handler(Handler successor) {
        this.successor = successor;
    }

    abstract void handleRequest(Request request);

}

package fun.peri.design.action.chain;

public class ConcreteHandlerOne extends Handler {

    public ConcreteHandlerOne(Handler successor) {
        super(successor);
    }

    @Override
    void handleRequest(Request request) {
        if (request.getRequestType() == RequestType.TYPEONE) {
            System.out.println(request.getName() + " is handle by ConcreteHandlerOne");
            return;
        }
        if (successor != null) {
            successor.handleRequest(request);
        }
    }

}

package fun.peri.design.action.chain;

public class ConcreteHandlerTwo extends Handler {

    public ConcreteHandlerTwo(Handler successor) {
        super(successor);
    }

    @Override
    void handleRequest(Request request) {
        if (request.getRequestType() == RequestType.TYPETWO) {
            System.out.println(request.getName() + " is handle by ConcreteHandlerTwo");
            return;
        }
        if (successor != null) {
            successor.handleRequest(request);
        }
    }

}

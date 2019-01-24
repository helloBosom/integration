package fun.peri.design.action.chain;

public class Client {

    public static void main(String[] args) {
        Handler handlerOne = new ConcreteHandlerOne(null);
        Handler handlerTwo = new ConcreteHandlerTwo(handlerOne);
        Request requestOne = new Request(RequestType.TYPEONE, "requestOne");
        handlerTwo.handleRequest(requestOne);
        Request requestTwo = new Request(RequestType.TYPETWO, "requestWTwo");
        handlerTwo.handleRequest(requestTwo);
    }

}

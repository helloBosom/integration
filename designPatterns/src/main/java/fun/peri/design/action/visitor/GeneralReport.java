package fun.peri.design.action.visitor;

public class GeneralReport implements Visitor {

    private int customersNo;
    private int orderNo;
    private int itemNo;

    @Override
    public void visit(Customer customer) {
        System.out.println(customer.getName());
        customersNo++;
    }

    @Override
    public void visit(Order order) {
        System.out.println(order.getName());
        orderNo++;
    }

    @Override
    public void visit(Item item) {
        System.out.println(item.getName());
        itemNo++;
    }

    public void displayResults() {
        System.out.println("number of customers:" + customersNo);
        System.out.println("number of orders:" + orderNo);
        System.out.println("number of items:" + itemNo);
    }

}

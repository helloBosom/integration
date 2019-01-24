package fun.peri.design.action.visitor;

public interface Visitor {

    void visit(Customer customer);

    void visit(Order order);

    void visit(Item item);

}

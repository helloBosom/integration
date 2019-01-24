package fun.peri.design.action.visitor;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Element {

    private String name;

    private List<Order> orders = new ArrayList<>();

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Order order : orders) {
            order.accept(visitor);
        }
    }

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void addOrder(Order order) {
        orders.add(order);
    }

}

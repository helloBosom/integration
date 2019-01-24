package fun.peri.design.action.visitor;

public class Client {

    public static void main(String[] args) {
        Customer customerOne = new Customer("customerOne");
        customerOne.addOrder(new Order("orderOne", "itemOne"));
        customerOne.addOrder(new Order("orderTwo", "itemOne"));
        customerOne.addOrder(new Order("orderThree", "itemOne"));

        Order order = new Order("orderA");
        order.addItem(new Item("itemAOne"));
        order.addItem(new Item("itemATwo"));
        order.addItem(new Item("itemAThree"));
        Customer customerTwo = new Customer("customerTwo");
        customerTwo.addOrder(order);

        CustomerGroup customerGroup = new CustomerGroup();
        customerGroup.addCustomer(customerOne);
        customerGroup.addCustomer(customerTwo);

        GeneralReport report = new GeneralReport();
        customerGroup.accept(report);
        report.displayResults();
    }

}

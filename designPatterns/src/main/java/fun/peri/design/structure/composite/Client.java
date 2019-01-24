package fun.peri.design.structure.composite;

public class Client {

    public static void main(String[] args) {
        Composite root = new Composite("root");
        Component leftNode = new Leaf("leftLeaf");
        Component composite = new Composite("composite");
        Component rightLeaf = new Leaf("rightLeaf");
        root.add(leftNode);
        root.add(composite);
        root.add(rightLeaf);
        Component nodeLeft = new Leaf("nodeLeft");
        Component nodeComposite = new Composite("nodeComposite");
        composite.add(nodeLeft);
        composite.add(nodeComposite);
        Component nodeRight = new Leaf("nodeRight");
        nodeComposite.add(nodeRight);
        root.print();
    }

}

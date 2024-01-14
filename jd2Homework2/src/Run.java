public class Run {

    @FunctionalInterface
    interface FactoryPrinter {
        void print(Factory factory);
    }

    public static void main(String[] args) throws InterruptedException {
        Factory factory1 = new Factory("Factory1");
        Factory factory2 = new Factory("Factory2");
        JunkYard junkYard = JunkYard.getInstance();
        junkYard.addFactories(factory1, factory2);
        junkYard.start();
        junkYard.join();
        FactoryPrinter output = (o) -> System.out.println(o.getFactoryName() + " - " + o.getCountRobots());
        System.out.print("Winner: ");
        output.print(junkYard.getFactoryOnPos(0));
        System.out.print("Loser: ");
        output.print(junkYard.getFactoryOnPos(1));


    }
}
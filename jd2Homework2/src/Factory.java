import java.util.ArrayList;
import java.util.List;

public class Factory implements Comparable<Factory> {
    private String factoryName;

    private Integer countRobots = 0;

    private final List<Component> components = new ArrayList<>();

    public Factory(String factoryName) {
        Minion minion = new Minion(this);
        minion.start();
        Scientist scientist = new Scientist(this);
        scientist.start();
        setFactoryName(factoryName);
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public synchronized int getCountRobots() {
        return countRobots;
    }

    public synchronized void setCountRobots(int countRobots) {
        this.countRobots = countRobots;
    }

    public synchronized List<Component> getComponents() {
        List<Component> rt = new ArrayList<>(components);
        components.clear();
        return rt;
    }

    public synchronized void putComponents(List<Component> components) {
        this.components.addAll(components);
    }

    @Override
    public int compareTo(Factory o) {
        return o.countRobots.compareTo(this.countRobots);
    }
}

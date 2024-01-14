import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Scientist extends Thread {
    private int robotCreatedCounter = 0;
    private final List<Robot> robotsQueue = new ArrayList<>();

    private final Factory linkedFactory;

    public Scientist(Factory factory) {
        this.linkedFactory = factory;
    }

    @Override
    public synchronized void run() {
        JunkYard junkYard = JunkYard.getInstance();
        robotsQueue.add(new Robot());
        while (junkYard.getState() != State.TERMINATED) {
            try {
                wait(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int iter = 0;
            List<Component> components = new ArrayList<>(linkedFactory.getComponents());
            for (Component component : components) {
                while (true) {
                    try {
                        if (robotsQueue.get(iter).getCountParts() != 9) {
                            Map<Component, Boolean> map = robotsQueue.get(iter).getComponents();
                            if (!map.get(component)) {
                                map.put(component, true);
                                robotsQueue.get(iter).addCountParts();
                                break;
                            } else iter++;
                        } else {
                            robotCreatedCounter++;
                            robotsQueue.remove(iter);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        robotsQueue.add(new Robot());
                    }
                }
            }
            //System.out.println(robotCreatedCounter);;
            linkedFactory.setCountRobots(robotCreatedCounter);
        }


    }
}

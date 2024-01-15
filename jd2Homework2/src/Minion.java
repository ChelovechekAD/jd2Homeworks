import java.util.ArrayList;
import java.util.List;

public class Minion extends Thread {

    private final Factory linkedFactory;

    public void pickUpComponent(Component component) {
        backpack.add(component);
    }

    public List<Component> getComponents() {
        return backpack;
    }

    private void refreshBackpack() {
        backpack.clear();
    }

    public Minion(Factory factory) {
        this.linkedFactory = factory;
    }

    private final List<Component> backpack = new ArrayList<>();


    @Override
    public synchronized void run() {
        JunkYard junkYard = JunkYard.getInstance();
        while (junkYard.getState() != State.TERMINATED) {
            this.setName("minionThread");
            refreshBackpack();
            while (true) {
                if (junkYard.getState() == State.WAITING || junkYard.isOpenState()) {
                    //System.out.println("Зашел на свалку");
                    while (junkYard.getState() == State.WAITING || junkYard.isOpenState()) {
                        try {
                            pickUpComponent(junkYard.pickUpComponent());
                        } catch (Exceptions.ComponentsNotFound e) {
                            break;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                }
            }
            //System.out.println("Ушел");
            linkedFactory.putComponents(this.getComponents());

        }
    }

}

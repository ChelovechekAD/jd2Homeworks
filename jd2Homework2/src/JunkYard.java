import java.util.*;

public class JunkYard extends Thread {

    private static JunkYard instance = null;
    private final List<Component> components = new ArrayList<>();
    private boolean openState = false;
    private final List<Factory> factories = new ArrayList<>();

    public synchronized void addFactories(Factory... factories) {
        this.factories.addAll(Arrays.asList(factories));
    }

    public Factory getFactoryOnPos(int pos) throws IndexOutOfBoundsException {
        return factories.get(pos);
    }

    private Component generateComponent() {
        Random random = new Random();
        return Component.valueOf(
                ConstValues.componentsNames[random.nextInt(ConstValues.COUNT_OF_COMPONENTS_TYPE)]);

    }

    public boolean isOpenState() {
        return openState;
    }

    public synchronized Component pickUpComponent() throws Exceptions.ComponentsNotFound, InterruptedException {
        try {
            Component component = components.get(0);
            components.remove(0);
            return component;
        } catch (IndexOutOfBoundsException e) {
            this.openState = false;
            throw new Exceptions.ComponentsNotFound();
        }
    }

    private JunkYard() {
        for (int i = 0; i < ConstValues.START_COUNT_OF_COMPONENTS_ON_JUNK_YARD; i++) {
            components.add(generateComponent());
        }

    }

    public synchronized static JunkYard getInstance() {
        if (instance == null) {
            instance = new JunkYard();
        }
        return instance;
    }

    public void refreshComponents() {
        Random random = new Random();
        for (int i = 0; i < random.nextInt(ConstValues.COUNT_OF_NEW_COMPONENTS_EVERY_NIGHT) + 1; i++) {
            components.add(generateComponent());
        }
    }

    private void dayTimeSwap() {
        try {
            wait(ConstValues.DAY_TIME_SWAP_TIME);
            refreshComponents();
            this.openState = true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sortFactories() {
        Collections.sort(factories);
    }

    @Override
    public synchronized void run() {
        this.setName("JunkYard");
        for (int i = 0; i < ConstValues.COUNT_DAYS; i++) {
            dayTimeSwap();
        }
        sortFactories();
    }


}

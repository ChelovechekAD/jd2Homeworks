import java.util.HashMap;
import java.util.Map;

public class ConstValues {

    public final static String[] componentsNames = {"HEAD", "BODY", "LEFT_ARM", "RIGHT_ARM", "LEFT_LEG", "RIGHT_LEG", "CPU", "RAM", "HDD"};
    public final static int COUNT_OF_COMPONENTS_TYPE = 9;
    public final static int START_COUNT_OF_COMPONENTS_ON_JUNK_YARD = 20;
    public final static int COUNT_OF_NEW_COMPONENTS_EVERY_NIGHT = 4;
    public final static int DAY_TIME_SWAP_TIME = 100;
    public final static int COUNT_DAYS = 100;
    public final static Map<Component, Boolean> components = new HashMap<>();

    static {
        components.put(Component.HEAD, false);
        components.put(Component.BODY, false);
        components.put(Component.LEFT_ARM, false);
        components.put(Component.RIGHT_ARM, false);
        components.put(Component.LEFT_LEG, false);
        components.put(Component.RIGHT_LEG, false);
        components.put(Component.CPU, false);
        components.put(Component.RAM, false);
        components.put(Component.HDD, false);
    }

}

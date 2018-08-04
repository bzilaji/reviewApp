package reviewapp.test.bence.reviewapp.dagger;

public class InjectHelper {
    public static MainComponent component;

    public static void setComponent(MainComponent comp) {
        component = comp;
    }

    public static MainComponent getComponent() {
        return component;
    }
}

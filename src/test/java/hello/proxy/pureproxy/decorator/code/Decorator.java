package hello.proxy.pureproxy.decorator.code;

public abstract class Decorator implements Component {
    private final Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        return this.component.operation();
    }

}

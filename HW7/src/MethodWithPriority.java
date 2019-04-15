import java.lang.reflect.Method;

public class MethodWithPriority {
    public Method method;
    public Integer priority;

    public MethodWithPriority(Method method, int priority) {
        this.method = method;
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }
}
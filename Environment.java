import java.util.*;

public class Environment {

    private Map<String, Object> map = new HashMap<>();

    public void set(String name, Object value) {
        map.put(name, value);
    }

    public Object get(String name) {
        if (!map.containsKey(name)) {
            throw new RuntimeException("Variable not found: " + name);
        }
        return map.get(name);
    }
}
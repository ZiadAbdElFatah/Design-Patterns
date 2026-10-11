package factory;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    List<String> toppings = new ArrayList<>();

    void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough");
        System.out.println("Adding sauce");
        System.out.println("Adding toppings: ");
        for (String topping : toppings) {
            System.out.println("   " + topping);
        }
    }

    void cut() {
        System.out.println("Cutting pizza into diagonal slices");
    }

    void bake() {
        System.out.println("Bake for 25 mins at 350");
    }

    void box() {
        System.out.println("Place pizza in the official box");
    }

    String getName() {
        return name;
    }
}
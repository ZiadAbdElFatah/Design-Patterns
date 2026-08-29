package strategy;

public class DuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.display();
        mallard.performFly();
        mallard.performQuack();

        Duck rubberDuck = new RubberDuck();
        rubberDuck.display();
        rubberDuck.performFly();
        rubberDuck.performQuack();

        rubberDuck.setFlyBehavior(new FlyWithWings());
        rubberDuck.setQuackBehavior(new QuackWithSound());
        rubberDuck.display();
        rubberDuck.performFly();
        rubberDuck.performQuack();
    }
}

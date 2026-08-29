package strategy;

public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWhere();
        quackBehavior = new QuackWithSound();
    }

    public void display() {
        System.out.println("I am a Model Duck");
    }
}

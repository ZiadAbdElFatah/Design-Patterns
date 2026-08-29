package strategy;

public class Decoy extends Duck {
    public Decoy() {
        flyBehavior = new FlyNoWhere();
        quackBehavior = new QuackWithNoSound();
    }

    public void display() {
        System.out.println("I am a Decoy Duck");
    }
}


## Algorithms vs. Design Patterns

Patterns are often confused with algorithms, because both concepts describe typical solutions to some known problems. While an algorithm always defines a clear set of actions that can achieve some goal, a pattern is a more high-level description of a solution. The code of the same pattern applied to two different programs may be different.

An analogy: an algorithm is like a cooking recipe - both have clear steps to achieve a goal. A pattern is more like a blueprint: you can see what the result and its features are, but the exact order of implementation is up to you.

---

## Classification of Design Patterns

Design patterns differ by their complexity, level of detail, and scale of applicability to the entire system being designed.

- **Idioms** - the most basic and low-level patterns. Usually apply only to a single programming language.
- **Architectural patterns** - the most universal and high-level patterns. Developers can implement these in virtually any language, and unlike other patterns, they can be used to design the architecture of an entire application.

All patterns can also be categorized by **intent/purpose**, in three main groups:

- **Creational patterns** - provide object creation mechanisms that increase flexibility and reuse of existing code.
- **Structural patterns** - explain how to assemble objects and classes into larger structures, while keeping these structures flexible and efficient.
- **Behavioral patterns** - take care of effective communication and the assignment of responsibilities between objects.

---

## 1st Design Principle - Encapsulate What Varies

> Take the parts that vary and encapsulate them, so that later you can alter or extend the parts that vary without affecting those that don't.

Two main steps:

1. Identify the aspects of your application that vary, and separate them from what stays the same.
2. Take what varies and encapsulate it so it won't affect the rest of your code.

As simple as this concept is, it forms the basis of almost all design patterns. All patterns provide a way to let some parts of a system vary independently of all other parts.

**Result:** fewer unintended consequences from code changes, and more flexibility in your systems.

---

## 2nd Design Principle - Program to an Interface, Not an Implementation

- We make a set of classes whose entire reason for living is to represent a behavior - it's the **behavior class** (implementing the behavior interface), rather than the duck superclass, that implements the actual behavior.
- Example using polymorphic type - abstract class `Animal`, with two concrete implementations, `Dog` and `Cat`:

**Programming to implementation:**

```java
Dog d = new Dog();
d.bark();
```

**Programming to an interface/supertype:**

```java
Animal animal = new Dog();
animal.makeSound();
```

**Even better - assign the concrete implementation object at runtime:**

```java
Animal a = getAnimal();
a.makeSound();
```

All these principles and patterns can be applied at any stage of the development lifecycle.

---

## 3rd Design Principle - Favor Composition Over Inheritance

- Composition means having classes _contain_ other classes to get their behavior, instead of inheriting that behavior - **HAS-A** instead of **IS-A**.
- Composition not only lets you encapsulate a family of algorithms into their own set of classes, but also lets you change behavior at **runtime**.
- We call each interchangeable set of behaviors a **family of algorithms**.
- Composition is used in many design patterns.

---

## The SimUDuck Problem (why this all matters)

The motivating example: a `Duck` superclass with subclasses (`MallardDuck`, `RedheadDuck`, etc.) that all `quack()` and `swim()` via inheritance.

New requirement: **ducks need to fly.** Naive fix - add `fly()` to the `Duck` superclass so all ducks inherit it.

**This breaks down** because not every duck should fly (e.g. `RubberDuck`). Now every new subclass has to override `fly()` to cancel out inherited behavior it doesn't want - inheritance is forcing behavior onto types that don't need it, and it doesn't scale as new duck types are added.

### The Fix: Behavior Objects via Composition

- `Duck` gets two instance variables, typed as **interfaces**, not concrete classes: `flyBehavior` (of type `FlyBehavior`) and `quackBehavior` (of type `QuackBehavior`).
- Concrete behavior classes implement these interfaces: e.g. `FlyWithWings`, `FlyNoWay` implement `FlyBehavior`; `Quack`, `MuteQuack` implement `QuackBehavior`.
- Each duck subclass sets its `flyBehavior`/`quackBehavior` to the appropriate concrete behavior object - instead of implementing `fly()`/`quack()` itself.
- `Duck.performFly()` simply **delegates**: `flyBehavior.fly()`. The `Duck` class doesn't know or care which concrete behavior object it's holding - just that it satisfies the interface.
- **HAS-A concretely:** a `Duck` _has-a_ `FlyBehavior` (and _has-a_ `QuackBehavior`) - composition, not inheritance, supplies the behavior.

#### Setting behavior: constructor vs. runtime setter

- Behavior is typically set in the duck subclass's **constructor** initially.
- The real payoff of composition: a **setter** (e.g. `setFlyBehavior()`) lets you **swap behavior dynamically at runtime** - e.g. a duck gets injured and can no longer fly, so you reassign its `flyBehavior` to `FlyNoWay` mid-program. Inheritance/direct interface implementation on the duck class itself can't do this - you can't change an object's class at runtime.

#### Why not just have each duck subclass implement its own `fly()` directly (e.g. via a `Flyable` interface)?

This would solve "not every duck can fly" - but it misses two things composition gives you:

1. **Reuse** - if `MallardDuck` and `RedheadDuck` fly the exact same way, direct implementation means duplicating that code in both classes. With behavior objects, both ducks just reference the same `FlyWithWings` implementation - write it once, share it.
2. **Runtime flexibility** - a behavior _object_ can be swapped out via a setter while the program runs. Behavior hardcoded into a duck subclass can't be changed per-instance at runtime.

So composition solves not just "some ducks can't fly," but "flying behavior needs to be shared across types **and** reassignable per-instance at runtime."

---

## The Strategy Pattern

> The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

![[Pasted image 20260824064758.png]]

**Reference:** [Strategy Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/strategy) (very useful, cross-reference alongside the book)

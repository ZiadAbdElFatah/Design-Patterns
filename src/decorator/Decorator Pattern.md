
## The Starbuzz Problem (recap)

Starbuzz modeled every beverage + condiment combination as its own subclass (`DarkRoastWithSteamedMilk`, `HouseBlendWithWhip`, etc.) - **subclass explosion**. Every new condiment or price change meant creating or editing many subclasses. This violates:

- **Encapsulate what varies** - which condiments are added, and their cost, should be pulled out and made independently changeable, not smeared across dozens of subclasses.
- **Favor composition over inheritance** - "has a condiment" was modeled as "is-a" subclass, when it's really a HAS-A relationship that should be composable at runtime.

---

## 5th Design Principle - Open-Closed Principle

> Classes should be open for extension, but closed for modification.

---

## The Decorator Pattern

> **Decorator Pattern:** attaches additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.

Key characteristics:

- Provides an alternative to subclassing for extending behavior.
- Involves a set of decorator classes used to **wrap** concrete components.
- Decorator classes **mirror the type** of the components they decorate — they are the same type as the components they decorate, via inheritance or interface implementation.
- Decorators change behavior by adding new functionality **before and/or after** (or even in place of) method calls to the component.
- A component can be wrapped with **any number** of decorators, stacked.
- Decorators are typically **transparent** to the client — unless the client relies on the component's concrete type.
- Decorators can result in **many small objects** in the design; overuse adds complexity. (Factory and Builder patterns can help make Decorator easier to manage.)

---

## The Concrete Fix: Beverage / CondimentDecorator

- `Beverage` stays an **abstract class** declaring `cost()` (and holds `description`).
- `CondimentDecorator` is also abstract and **extends `Beverage`** this is the concrete instance of "decorators mirror the type of the component they decorate": a `CondimentDecorator` IS-A `Beverage`, type-compatible with what it wraps.
- `CondimentDecorator` holds an internal reference to a `Beverage` object this is the **composition** piece: it HAS-A `Beverage` it wraps.
- Concrete decorators (`Mocha`, `Whip`, `Soy`, etc.) wrap a `Beverage`, call the wrapped object's `cost()`, then **add their own cost on top** before returning the total.
- Result, you can compose beverages by nesting decorators at runtime, e.g.:
    ```java
    Beverage b = new Mocha(new Whip(new DarkRoast()));
    ```

---

### Why this solves the subclass explosion

Instead of needing a named subclass for every beverage+condiment combination, any beverage can be wrapped with any combination of condiment decorators at runtime — no new classes needed per combination. This is Open-Closed in action: `Beverage` and existing decorators are never modified (**closed for modification**) to support a new condiment — you just write one new decorator class (**open for extension**).

---

## The Java I/O Example

The JDK's own I/O classes use the same structural pattern:

- `BufferedInputStream`, and custom decorators like `LowerCaseInputStream`, all **extend `FilterInputStream`** (the decorator layer).
- `FilterInputStream` itself **extends `InputStream`** (the abstract component).

This mirrors `CondimentDecorator extends Beverage` exactly — same shape, applied to streams instead of beverages. Example chain:

```java
InputStream in = new LowerCaseInputStream(
    new BufferedInputStream(
        new FileInputStream("test.txt")));
```

Each layer wraps the one inside it, adding behavior (buffering, then lowercase-to-uppercase transformation) around the base component's reads.

---

## Tradeoff: Why Overuse Gets Complex

Decorator chains can make debugging harder: instead of one object you can easily inspect, you end up with a pile of small, similarly-shaped wrapper objects. Tracing behavior means walking through potentially many layers of wrapping to understand what actually happens on a given method call.

---

**Reference:** [Decorator Pattern — Refactoring Guru](https://refactoring.guru/design-patterns/decorator) (very useful, cross-reference alongside the book)
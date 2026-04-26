package HomeWork3;

interface Coffee {
    String getDescription();
    double cost();
}

class SimpleCoffee implements Coffee {
    @Override public String getDescription() { return "Simple coffee"; }
    @Override public double cost() { return 2.0; }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    @Override public double cost() {
        return decoratedCoffee.cost();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override public String getDescription() {
        return super.getDescription() + ", milk";
    }
    @Override public double cost() {
        return super.cost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override public String getDescription() {
        return super.getDescription() + ", sugar";
    }
    @Override public double cost() {
        return super.cost() + 0.2;
    }
}

public class Decorator {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription() + " costs $" + coffee.cost());
    }
}

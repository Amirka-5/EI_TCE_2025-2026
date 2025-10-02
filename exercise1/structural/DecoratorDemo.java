package structural;

// Decorator Pattern - Coffee Order System
interface Coffee {
    double getCost();
    String getDescription();
}

class BasicCoffee implements Coffee {
    @Override
    public double getCost() {
        return 2.00;
    }
    
    @Override
    public String getDescription() {
        return "Basic Coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.50;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.25;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }
}

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.75;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Whipped Cream";
    }
}

class CaramelSyrupDecorator extends CoffeeDecorator {
    public CaramelSyrupDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 1.00;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Caramel Syrup";
    }
}

public class DecoratorDemo {
    public static void demonstrate() {
        System.out.println("\n=== DECORATOR PATTERN DEMO ===");
        
        // Basic coffee
        Coffee coffee = new BasicCoffee();
        printCoffee(coffee);
        
        // Coffee with milk and sugar
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        printCoffee(coffee);
        
        // Fancy coffee with everything
        coffee = new BasicCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        coffee = new WhippedCreamDecorator(coffee);
        coffee = new CaramelSyrupDecorator(coffee);
        printCoffee(coffee);
    }
    
    private static void printCoffee(Coffee coffee) {
        System.out.println(coffee.getDescription() + " : $" + coffee.getCost());
    }
}
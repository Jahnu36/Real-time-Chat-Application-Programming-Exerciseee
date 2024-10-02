import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Coffee {
    double getCost();
    String getDescription();
}

class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 2.0;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}

class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", milk";
    }
}

class Sugar extends CoffeeDecorator {
    public Sugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.2;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", sugar";
    }
}

class Whip extends CoffeeDecorator {
    public Whip(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.7;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", whip";
    }
}

public class CoffeeShop {
    private static List<String> menu = new ArrayList<>();
    static {
        menu.add("1. Simple Coffee - $2.00");
        menu.add("2. Milk - $0.50");
        menu.add("3. Sugar - $0.20");
        menu.add("4. Whip - $0.70");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Coffee coffee = new SimpleCoffee();
        boolean ordering = true;

        System.out.println("Welcome to the Coffee Shop!");
        while (ordering) {
            displayMenu();
            System.out.print("Enter your choice (1-4), or 0 to finish ordering: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    ordering = false;
                    break;
                case 1:
                    coffee = new SimpleCoffee();
                    break;
                case 2:
                    coffee = new Milk(coffee);
                    break;
                case 3:
                    coffee = new Sugar(coffee);
                    break;
                case 4:
                    coffee = new Whip(coffee);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (ordering) {
                System.out.printf("Current order: %s%nCost: $%.2f%n%n", coffee.getDescription(), coffee.getCost());
            }
        }

        System.out.printf("%nFinal order: %s%nTotal cost: $%.2f%n", coffee.getDescription(), coffee.getCost());
        System.out.println("Thank you for your order!");
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        for (String item : menu) {
            System.out.println(item);
        }
    }
}
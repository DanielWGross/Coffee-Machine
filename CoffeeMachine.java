package machine;
import java.util.Scanner;

class Machine {
    int money;
    int water;
    int milk;
    int beans;
    int cups;
    boolean systemOn;

    public Machine(int money, int water, int milk, int beans, int cups, boolean systemOn) {
        this.money = money;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.systemOn = true;
    }
}

enum Action {
    BUY, FILL, TAKE, REMAINING, EXIT
}

public class CoffeeMachine {
    private static void updateInventory(Machine machine, int money, int water, int milk, int beans) {
        if (water > machine.water) {
            System.out.println("Sorry, not enough water!");
            return;
        }

        if (milk > machine.milk) {
            System.out.println("Sorry, not enough milk!");
            return;
        }

        if (beans > machine.beans) {
            System.out.println("Sorry, not enough beans!");
            return;
        }

        if (machine.cups <= 0) {
            System.out.println("Sorry, not enough cups!");
            return;
        }

        System.out.println("I have enough resources, making you a coffee!");
        machine.money += money;
        machine.water -= water;
        machine.milk -= milk;
        machine.beans -= beans;
        machine.cups--;
    }

    private static void handleBuy(Machine machine) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:%n");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                updateInventory(machine, 4, 250, 0, 16);
                break;
            case "2":
                 updateInventory(machine, 7, 350, 75, 20);
                 break;
            case "3":
                 updateInventory(machine, 6, 200, 100, 12);
                 break;
            default:
                break;
        }
    }

    private static void handleFill(Machine machine) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        machine.water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        machine.milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        machine.beans += scanner.nextInt();

        System.out.println("Write how many disposable cups of coffee you want to add:");
        machine.cups += scanner.nextInt();
    }

    private static void printInventory(Machine machine) {
        System.out.println();
        System.out.printf("The coffee machine has:%n");
        System.out.printf("%d ml of water%n", machine.water);
        System.out.printf("%d ml of milk%n", machine.milk);
        System.out.printf("%d g of coffee beans%n", machine.beans);
        System.out.printf("%d disposable cups%n", machine.cups);
        System.out.printf("$%d of money%n", machine.money);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine machine = new Machine(550, 400, 540, 120, 9, true);

        do {
            System.out.printf("%nWrite action (buy, fill, take, remaining, exit):%n");
            Action action = Action.valueOf(scanner.nextLine().toUpperCase());

            switch (action) {
                case BUY:
                    handleBuy(machine);
                    break;
                case FILL:
                    handleFill(machine);
                    break;
                case TAKE:
                    System.out.printf("I gave you $%d%n", machine.money);
                    machine.money = 0;
                    break;
                case REMAINING:
                    printInventory(machine);
                    break;
                default:
                    machine.systemOn = false;
            }
        } while (machine.systemOn);
    }
}

package src;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        final String message = "\nThis is a Babering Shop Java Program\nEnter space and press enter to create a new event\n";
        BarberingShop shop = new BarberingShop();
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        ShopDisplay.displayHeader();
        while (true) {
            String input = sc.nextLine();
            if (!input.equals(" ")) {
                break; // Break the loop if input is not a space
            }
            int x = new Random().nextInt(4); // generate a random integer between 0 and 3
            shop.event = x;
            switch (x) {
                case 3:
                case 2:
                    shop.handleOrdClient();
                    break;
                case 1:
                    shop.handleVipClient();
                    break;
                case 0:
                    shop.clearMainChair();
                    break;
            }
        }
        sc.close(); // Close the scanner to prevent resource leak
    }
}
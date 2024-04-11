package src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        BarberingShop shop = new BarberingShop();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the barbershop!");
        shop.displayHeader();
        while (true) {
            String input = sc.nextLine();
            if (!input.equals(" ")) {
                break; // Break the loop if input is not a space
            }
            int x = new Random().nextInt(4); // generate a random integer between 0 and 3
            shop.x = x;
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
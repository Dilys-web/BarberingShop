package src;

import java.util.*;

public class ShopDisplay {
    LinkedList<String> queue;
    int seatSize;

    public ShopDisplay(LinkedList<String> queue, int seatSize) {
        this.queue = queue;
        this.seatSize = seatSize;
    }

    public static void displayHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("    X         Events                   State of the Shops\n");
        sb.append(
                "+--------+--------------+------------------------------------------------+");
        System.out.println(sb.toString());
    }

    public void displayShopState(String client, int event, int vipCount, int ordCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(" " + event);
        sb.append(" ----->  ");
        sb.append(formatEvents(client, event, vipCount, ordCount));
        sb.append("       [   ");
        sb.append(formatQueue());
        sb.append(" ]");
        System.out.println(sb.toString());
    }

    private String formatQueue() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seatSize; i++) {
            if (i > 0) {
                sb.append(" : ");
            }
            if (!queue.isEmpty() && i < queue.size()) {
                String element = queue.get(i);
                if (element == null) {
                    queue.removeLast();
                    sb.append("----");
                    continue;
                }
                sb.append(element);
            } else {
                sb.append("----");
            }
        }
        return sb.toString();
    }

    private String formatEvents(String client, int event, int vipCount, int ordCount) {
        StringBuilder sb = new StringBuilder();
        switch (event) {
            case 3:
            case 2:
                if (queue.size() <= seatSize) {
                    sb.append("( ++ " + client + " )");
                } else {
                    sb.append("( +- " + client + " )");
                    ordCount--;
                }
                if (queue.size() == seatSize) {
                    queue.add(null);
                }
                break;
            case 1:
                if (queue.size() <= seatSize) {
                    sb.append("( ++ " + client + " )");
                } else {
                    sb.append("( +- " + client + " )");
                    vipCount--;
                }
                if (queue.size() == seatSize) {
                    queue.add(null);
                }
                break;
            case 0:
                sb.append("( -- " + client + " )");
                break;
            default:
                break;
        }
        return sb.toString();
    }

}

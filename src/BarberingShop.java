package src;

import java.util.*;

public class BarberingShop {
    int vipCount, ordCount;
    LinkedList<String> queue; // FIFO queue for the waiting room
    int seatSize;
    int x;

    public BarberingShop() {
        vipCount = 0;
        ordCount = 0;
        queue = new LinkedList<>();
        this.seatSize = 6;

    }

    public void handleVipClient() {
        vipCount++;
        if (queue.isEmpty()) {
            queue.add("VIP" + vipCount);
        } else {
            if (queue.size() < seatSize) {
                boolean foundOrd = false;
                for (int i = 1; i < queue.size(); i++) {
                    if (queue.get(i).contains("ORD")) {
                        queue.add(i, "VIP" + vipCount);
                        foundOrd = true;
                        break;
                    }
                }
                if (!foundOrd) {
                    queue.add("VIP" + vipCount);
                }
            }
        }
        displayShopState("VIP" + vipCount);
    }

    public void handleOrdClient() {
        ordCount++;
        if (queue.isEmpty() || queue.size() < seatSize) {

            queue.add("ORD" + ordCount);
        }
        displayShopState("ORD" + ordCount);
        // poll(): This method retrieves and removes the head of the queue, or returns
        // null if the queue is empty.
    }

    public void clearMainChair() {
        String first = queue.peek();
        if (first != null) {
            queue.poll();

            displayShopState(first);

        }
    }

    private void displayShopState(String client) {
        StringBuilder sb = new StringBuilder();
        sb.append(" " + x);
        sb.append(" ----->  ");
        switch (x) {
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

        sb.append("       [   ");
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
        sb.append(" ]");
        System.out.println(sb.toString());

    }

    public void displayHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("    X         Events                   State of the Shops\n");
        sb.append("+--------+--------------+------------------------------------------------+");
        System.out.println(sb.toString());
    }
}

// public void addCustomer(boolean isVIP) {
// if (isVIP){
// vipcount++;
// System.out.println("A VIP customer has arrived.");
// } else {
// ordCount++;
// System.out.println("An ordinary customer has arrived.");

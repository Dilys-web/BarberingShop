package src;

import java.util.*;

public class BarberingShop {
    int vipCount, ordCount;
    LinkedList<String> queue; // FIFO queue for the waiting room
    int seatSize;
    int event;
    ShopDisplay shopDisplay;

    // Constructor
    public BarberingShop() {
        vipCount = 0;
        ordCount = 0;
        queue = new LinkedList<>();
        seatSize = 6;
        shopDisplay = new ShopDisplay(queue, seatSize);
    }

    public void handleVipClient() {
        vipCount++;
        if (queue.isEmpty()) {
            addToQueue("VIP" + vipCount);
        } else {
            if (queue.size() < seatSize) {
                boolean foundOrd = searchForOrdClient();
                if (!foundOrd) {
                    addToQueue("VIP" + vipCount);
                }
            }
        }
        shopDisplay.displayShopState("VIP" + vipCount, event, vipCount, ordCount);
    }

    private void addToQueue(String clientName) {
        queue.add(clientName);
    }

    private boolean searchForOrdClient() {
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i).contains("ORD")) {
                queue.add(i, "VIP" + vipCount);
                return true;
            }
        }
        return false;
    }

    public void handleOrdClient() {
        ordCount++;
        if (queue.isEmpty() || queue.size() < seatSize) {
            addToQueue("ORD" + ordCount);
        }
        shopDisplay.displayShopState("ORD" + ordCount, event, vipCount, ordCount);
    }

    public void clearMainChair() {
        String first = queue.peek();
        if (first != null) {
            queue.poll();
            shopDisplay.displayShopState(first, event, vipCount, ordCount);
        }
    }

}
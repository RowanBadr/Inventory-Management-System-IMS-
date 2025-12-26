import java.util.ArrayList;
import java.util.List;

class OrderQueue {
    private List<Order> queue;

    public OrderQueue() {
        queue = new ArrayList<>();
    }

    public void enqueueOrder(Order order) {
        queue.add(order);
    }

    public Order dequeueOrder() {
        if (!queue.isEmpty()) {
            return queue.remove(0);
        }
        return null;
    }

    public int getQueueSize() {
        return queue.size();
    }
}

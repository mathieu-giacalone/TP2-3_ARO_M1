import java.util.ArrayList;

public class Knapsach {
    int profit;
    double[] takenItems;

    public int run (int level, int profit, ArrayList<Item> items, double capacity, double[] takenItems) {
        if (level == items.size() - 1) {
            return profit;
        }

        Item item = items.get(level);
        int profit1 = 0;
        int profit2 = 0;
        if (capacity - item.getWeight() > 0.0) {
            takenItems[level] = 1;
            profit1 += run ((level + 1),  (profit + item.getValue()), items, (capacity - item.getWeight()), takenItems);
        }


        double gloutonValue = Main.valueOfTakenItems(takenItems, items);
        if (gloutonValue >= profit1) {
            takenItems[level] = 0;
            profit2 += run ((level + 1), profit, items, capacity, takenItems);
        }

        if (profit1 > profit2) profit = profit1;
        else profit = profit2;
        if (level == 0) {
            this.profit = profit;
            this.takenItems = takenItems;
        }
        return profit;
    }// run ()
}// Knapsach()

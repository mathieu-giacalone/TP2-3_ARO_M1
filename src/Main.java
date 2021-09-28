import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws Exception {
        // Etape 1 : Ordonné les objets dans l'ordre décroissant en fonction de leur ratio Valeur/Poids
        // Etape 2 : Lancer l'algorithme glouton pour obtenir une solution optimale fractionnaire
        // Etape 3 : Chercher de maniere recursive la solution optimale entière en mettant des contriante du style x1 = 0 et x1 = 1 etc..
        // règle de coupure : capacité du sac inssufisant et algorithme glouton avec les choix déjà pris moins bien
        // que le profit déjà trouvé avant
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            int randomWeight = ThreadLocalRandom.current().nextInt(1, 20 + 1);
            int randomValue = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            Item item = new Item(randomValue, randomWeight);
            items.add(item);
        }

        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.ratio() < o2.ratio()) {
                    return 1;
                } else if ((o1.ratio() > o2.ratio())){
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        Backpack backpack = new Backpack(5);
        System.out.println(items);
        double[] takenItems = new double[items.size()];
        Arrays.fill(takenItems, 0);
        takenItems = takeItemsGlutton (items, backpack.capacity, -1, takenItems );
        double optimalValue = 0;
        optimalValue = valueOfTakenItems(takenItems, items);
        for (int i = 0; i < takenItems.length; ++i) {
            System.out.println(takenItems[i]);
        }
        System.out.println("optimalValue: " + optimalValue);
        double[] newTakenItems = new double[items.size()];
        Arrays.fill(newTakenItems, 0);
        Knapsach knapsach = new Knapsach();
        knapsach.run(0, 0, items, backpack.capacity, newTakenItems);
        newTakenItems = knapsach.takenItems;
        int profit = knapsach.profit;
        System.out.println("KnapsachProfit: " + profit);
        System.out.println("KnapsachItemTaken: ");
        /*for (int i = 0; i < newTakenItems.length; ++i) {
            System.out.println(newTakenItems[i]);
        }*/
    }// main ()

    public static double valueOfTakenItems (double[] takenItems, ArrayList<Item> items) {
        double value = 0;
        for (int i = 0; i < takenItems.length; ++i) {
            if (takenItems[i] != 0.0) {
                value += items.get(i).getValue() * takenItems[i];
            }
        }
        return value;
    }// valueOfTakenItems()

    public static double[] takeItemsGlutton (ArrayList<Item> items, double capacity, int notChangeItemsTaken, double[] takenItems) {
        for (int i = notChangeItemsTaken + 1; i < items.size(); ++i) {
            double newCapacity = capacity;
            newCapacity -= items.get(i).getWeight();
            if (newCapacity < 0) {
                newCapacity = capacity;
                double partOfItem;
                partOfItem =  newCapacity / items.get(i).getWeight();
                newCapacity -= items.get(i).getWeight() * partOfItem;
                takenItems[i] = partOfItem;
            } else {
                takenItems[i] = 1;
            }
            capacity = newCapacity;
            if (capacity == 0.0) break;
        }
        return takenItems;
    }// takeObjectsGlutton ()

    public static int knapsach (int level, int profit, ArrayList<Item> items, double capacity, double[] takenItems) {
        if (level == items.size() - 1) {
            return profit;
        }

        Item item = items.get(level);
        int profit1 = 0;
        int profit2 = 0;
        if (capacity - item.getWeight() > 0.0) {
            takenItems[level] = 1;
            profit1 += knapsach ((level + 1),  (profit + item.getValue()), items, (capacity - item.getWeight()), takenItems);
        }


        double gloutonValue = valueOfTakenItems(takenItems, items);
        if (gloutonValue >= profit1) {
            takenItems[level] = 0;
            profit2 += knapsach ((level + 1), profit, items, capacity, takenItems);
        }

        if (profit1 > profit2) profit = profit1;
        else profit = profit2;
        return profit;
    }// Knapsach ()
}// Main

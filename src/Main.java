import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws Exception {
        // Etape 1 : Ordonné les objets dans l'ordre décroissant en fonction de leur ratio Valeur/Poids
        // Etape 2 : Lancer l'algorithme glouton pour obtenir une solution optimale fractionné
        // Etape 3 : Chercher de maniere recursive la solution optimale entière en mettant des contriante du style x1 = 0 et x1 = 1 etc..

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
    }// main ()

    public int takeObjectsGlutton () {
        return 0;
    }
}// Main

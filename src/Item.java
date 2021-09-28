public class Item {
    private int value;
    private int weight;

    public Item (int value, int weight) throws Exception {
        if (weight < 0) throw new Exception("Le poid doit etre > à 0.");
        this.value = value;
        this.weight = weight;
    }// Object()

    public double ratio () {
        return (double) value / weight;
    }// ratio()

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "value=" + value +
                ", weight=" + weight +
                ", ratio=" + ratio() +
                '}';
    }
}

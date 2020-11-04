public class Package {
    public Package(String name){
        this.name = name;
    }
    private String name = new String();
    private int weight;
    private Food food;


    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food, int weight) {
        this.food = food;
        this.weight = weight;
    }
}

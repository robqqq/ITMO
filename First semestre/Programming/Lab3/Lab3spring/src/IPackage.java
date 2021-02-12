public interface IPackage {
    String getName();
    int getWeight();
    IFood getFood();
    void setFood(IFood food, int weight);
}

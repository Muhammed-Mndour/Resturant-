
package resturant;

public class Drink {
    int id,cost;
    String name,type;

    public Drink(int id, String name,  String type ,int cost) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

   
    
    
}


import java.util.Objects;

public class Product {
    static int lastId = 1;
    private final String name;
    private final int price;
    private final String tag;

    private final int id;

    public Product(String name,int price,String tag){
        this.name = name;
        this.price = price;
        this.tag = tag;

        id = lastId;
        lastId++;
    }


    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", price='" + price +'\''+
                ", id='" + id +'\'' +
                '}'+ "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name);
    }

}
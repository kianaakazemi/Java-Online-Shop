import java.util.LinkedList;
import java.util.Objects;

public class Seller {
    static int lastId = 1;
    private final String username;
    private final String password;
    private final String name;
    private final String lastName;
    private final String birthDate;
    private final int phoneNumber;
    private final String email;
    private final int id;
    private boolean verification = false;

    public Seller(String username, String password, String name, String lastName, String birthDate, int phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        id = lastId;
        lastId++;
    }

    LinkedList<Product> sellerProducts = new LinkedList<>();

    public boolean getVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    public LinkedList<Product> getSellerProducts() {
        return sellerProducts;
    }

    public void addProduct(Product newProduct){
        if (verification) {
            sellerProducts.add(newProduct);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(username, seller.username);
    }


    public void removeProduct(int id){
        for (Product p : sellerProducts) {
            if (p.getId() == id) {
                sellerProducts.remove(p);
                break;
            }
        }
    }



    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", verification=" + verification +
                '}' + "\n";
    }
}

import java.util.Objects;

public class Customer {
    private final String username;
    private final String password;
    private final String name;
    private final String lastName;
    private final String birthDate;
    private final int phoneNumber;
    private final String email;
    private int balance = 0;

    public Customer(String username, String password, String name, String lastName, String birthDate, int phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getUsername() { return username; }

    public void addBalance(int balance) {
        this.balance += balance;
    }

    public void buyProducts(Product product){
        if (balance >= product.getPrice()) {
            balance -= product.getPrice();
            Main.store.removeProduct(product.getId());
            Main.store.removeFromSameTag(product.getId());
            System.out.println("Congratulations!You bought this item successfully.");
        }else{
            System.out.println("Sorry, you don’t have enough balance to buy this item!");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(username, customer.username) && Objects.equals(password, customer.password);
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
                ", balance=" + balance +
                '}' + "\n";
    }
}

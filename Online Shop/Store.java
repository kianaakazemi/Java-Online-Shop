import java.util.LinkedList;

public class Store {
   private final LinkedList<Seller> unapprovedSellers = new LinkedList<>();

    public LinkedList<Seller> getUnapprovedSellers() {
        return unapprovedSellers;
    }

    public void addUnSeller(Seller newSeller){
        unapprovedSellers.add(newSeller);
    }

    public void approveSeller(String username){
        boolean flag = false;
        for (Seller s:unapprovedSellers) {
            if (s.getUsername().equals(username)) {
                flag = true;
                unapprovedSellers.remove(s);
                s.setVerification(true);
                break;
            }
        }
        if (flag) {
            System.out.println("User has been approved successfully.");
        }else{
            System.out.println("You can't approve this user.");
        }
    }
    public boolean removeUnSeller(String username){
        boolean flag = false;
        for (Seller s:unapprovedSellers) {
            if (s.getUsername().equals(username)) {
                flag = true;
                unapprovedSellers.remove(s);
                break;
            }
        }
        return flag;
    }



   private final LinkedList<Seller> allSellers = new LinkedList<>();

    public LinkedList<Seller> getAllSellers() {
        return allSellers;
    }

    public void addSeller(Seller newSeller){
        allSellers.add(newSeller);
    }

    public boolean removeSeller(String username){
        boolean flag = false;
        for (Seller s:allSellers) {
            if (s.getUsername().equals(username)) {
                flag = true;
                allSellers.remove(s);
                break;
            }
        }
        return flag;
    }



    private final LinkedList<Product> allProducts = new LinkedList<>();

    public LinkedList<Product> getAllProducts() {
        return allProducts;
    }

    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public boolean removeProduct(int id){
        boolean flag = false;
        for (Product p:allProducts) {
            if (p.getId() == id) {
                flag = true;
                allProducts.remove(p);
                break;
            }
        }
        return flag;
    }


    private final LinkedList<Product> sameTag = new LinkedList<>();

    public void sameTagProducts(String tagName){
        for (Product p : allProducts) {
            if (p.getTag().equals(tagName)) {
                sameTag.add(p);
            }
        }
    }

    public LinkedList<Product> getSameTag() {
        return sameTag;
    }

    public void removeFromSameTag(int id){
        for (Product p:sameTag) {
            if (p.getId() == id) {
                allProducts.remove(p);
            }
        }
    }

    public void clearSameTagList(){
        sameTag.clear();
    }



    private final LinkedList<Customer> allCustomers = new LinkedList<>();

    public LinkedList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void addCustomer(Customer newCustomer){
        allCustomers.add(newCustomer);
    }

    public boolean removeCustomer(String username){
        boolean flag = false;
        for (Customer c:allCustomers) {
            if (c.getUsername().equals(username)) {
                flag = true;
                allCustomers.remove(c);
                break;
            }
        }
        return flag;
    }

    public boolean removeUser(String username){
        return Main.store.removeCustomer(username) || Main.store.removeUnSeller(username) || Main.store.removeSeller(username);
    }


}

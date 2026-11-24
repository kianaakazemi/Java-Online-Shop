import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    static Store store = new Store();
    static Scanner myScanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        System.out.println("Hi, welcome to our online shop ^_^");
        file();
        start();
    }

    public static File file() throws IOException {
        String path = "./src/file/file.txt";
        File file = new File(path);

        file.getParentFile().mkdirs();

        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static void start() throws IOException {
        System.out.println();
        System.out.println("Here’s a list of available commands:\n" +
                "1. sign up\n" + "2. log in\n" + "3. close\n" +
                "note: At each step, you can return to the previous section by typing the word 'back'.");
       String choiceStr = myScanner.next();
        if (!(choiceStr.equals("1") || choiceStr.equals("2") || choiceStr.equals("3"))) {
            System.out.println("Invalid input!");
            start();
        }
       int choice = Integer.parseInt(choiceStr);
        switch (choice) {
            case 1:
                singUp();
                break;
            case 2:
                singIn();
                break;
            case 3:
                break;
        }

    }

    public static void singUp() throws IOException {
        File file = file();
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);

        System.out.println();
        System.out.println("Do you want to sign up as a customer or seller?");
        String ans = myScanner.next();
        if (ans.equals("back")) {
            start();
            return;
        }
        if (!(ans.equals("seller") || ans.equals("customer"))) {
            System.out.println("Invalid input!");
            singUp();
            return;
        }
        System.out.println("Please enter following information:");
        System.out.println("Username:");
        String username = myScanner.next();
        if (username.equals("back")) {
            start();
            return;
        }
        boolean usernameStatus = false;
        for (Customer c : store.getAllCustomers()) {
            if (c.getUsername().equals(username)) {
                usernameStatus = true;
                break;
            }
        }
        for (Seller s : store.getAllSellers()) {
            if (s.getUsername().equals(username)) {
                usernameStatus = true;
                break;
            }
        }
        if (usernameStatus || username.equals("admin")) {
            System.out.println("This username already exist!");
            singUp();
            return;

        } else {
            System.out.println("Password:");
            String password = myScanner.next();
            if (password.equals("back")) {
                start();
                return;
            }
            System.out.println("Name:");
            String name = myScanner.next();
            if (name.equals("back")) {
                start();
                return;
            }
            System.out.println("Last name:");
            String lastName = myScanner.next();
            if (lastName.equals("back")) {
                start();
                return;
            }
            System.out.println("Birth date:");
            String birthDate = myScanner.next();
            if (birthDate.equals("back")) {
                start();
                return;
            }
            System.out.println("Phone number:");
            String phoneNum = myScanner.next();
            if (phoneNum.equals("back")) {
                start();
                return;
            }
            int phoneNumber = Integer.parseInt(phoneNum);
            System.out.println("Email address:");
            String email = myScanner.next();
            if (email.equals("back")) {
                start();
                return;
            }
            if (ans.equals("customer")) {
                Customer customer = new Customer(username, password, name, lastName, birthDate, phoneNumber, email);
                store.addCustomer(customer);
                System.out.println("You have signed up successfully!");
                printStream.println("SIGN UP{ role: customer, username: " + customer.getUsername() + "}");
            } else {
                Seller seller = new Seller(username, password, name, lastName, birthDate, phoneNumber, email);
                store.addSeller(seller);
                store.addUnSeller(seller);
                System.out.println("You have signed up successfully!");
                System.out.println("Your id: " + seller.getId());
                printStream.println("SIGN UP{ role: seller, username: " + seller.getUsername() + ", id: " + seller.getId() + "}");
            }
        }
        printStream.flush();
        printStream.close();
        start();
    }

    public static void singIn() throws IOException {
        File file = file();
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);

        System.out.println();
        System.out.println("Do you want to sign in as a customer, seller or admin?");
        String ans2 = myScanner.next();
        if (ans2.equals("back")) {
            start();
            return;
        }

        if (!(ans2.equals("seller") || ans2.equals("customer") || ans2.equals("admin"))) {
            System.out.println("Invalid input!");
            singIn();
            return;
        }

        System.out.println("Please enter your username:");
        String username2 = myScanner.next();
        if (username2.equals("back")) {
            start();
            return;
        }

        System.out.println("Please enter your password:");
        String password2 = myScanner.next();
        if (password2.equals("back")) {
            start();
            return;
        }

        if (ans2.equals("seller")) {
            System.out.println("Please enter your id number:");
            String idStr = myScanner.next();
            if (idStr.equals("back")) {
                start();
                return;
            }
            int id = Integer.parseInt(idStr);
            Seller seller = null;
            boolean flag = false;
            for (Seller s : store.getAllSellers()) {
                if (username2.equals(s.getUsername()) && id == s.getId()) {
                    seller = s;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Invalid information!");
                singIn();
                return;
            } else {
                printStream.println("SIGN IN{ role: seller, username: " + seller.getUsername() + ", id: " + seller.getId() + "}");
                singInSeller(seller);
            }


        } else if (ans2.equals("admin")) {
            if (username2.equals("admin") && password2.equals("admin")) {
                singInAdmin();

            } else {
                System.out.println("Invalid information!");
                singIn();
                return;
            }
        } else {
            Customer customer = null;
            boolean flag = false;
            for (Customer c : store.getAllCustomers()) {
                if (username2.equals(c.getUsername())) {
                    customer = c;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Invalid information!");
                singIn();
                return;
            } else {
                printStream.println("SIGN IN{ role: customer, username: " + customer.getUsername() + "}");
                singInCustomer(customer);
            }

        }
        printStream.flush();
        printStream.close();
    }

    public static void singInSeller(Seller seller) throws IOException {
        File file = file();
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);

        System.out.println();
        System.out.println("Here's a list of available commands for you:\n" + "1. account info\n" +
                "2. add item\n" + "3. remove item\n" + "4. log out");
        String ans3Str = myScanner.next();
        if (ans3Str.equals("back")) {
            singIn();
            return;
        }
        int ans3 = Integer.parseInt(ans3Str);

        switch (ans3) {
            case 1:
                System.out.println(seller.toString());
                System.out.println();
                break;
            case 2:
                System.out.println("Enter item's name:");
                String name = myScanner.next();
                if (name.equals("back")) {
                    singInSeller(seller);
                    return;
                }
                System.out.println("Enter item's price:");
                String priceStr = myScanner.next();
                if (priceStr.equals("back")) {
                    singInSeller(seller);
                    return;
                }
                int price = Integer.parseInt(priceStr);
                System.out.println("Enter item's tag:");
                String tag = myScanner.next();
                if (tag.equals("back")) {
                    singInSeller(seller);
                    return;
                }
                if (seller.getVerification()) {
                    Product product = new Product(name, price, tag);
                    store.addProduct(product);
                    seller.addProduct(product);
                    System.out.println("Item " + product.getId() + " has been added successfully.");
                    printStream.println("ADD ITEM{ seller's username: " + seller.getUsername() + ", item's name:" + product.getName()
                            + ", item's tag:" + product.getTag() + ", item's id:" + product.getId() + "}");
                } else {
                    System.out.println("You don’t have the permission to add an item.");
                }

                break;
            case 3:
                System.out.println("Which item do you want to remove?(Enter item's id)");
                String ans7Str = myScanner.next();
                if (ans7Str.equals("back")) {
                    singInSeller(seller);
                    return;
                }
                int ans7 = Integer.parseInt(ans7Str);
                boolean flag2 = false;
                for (Product p : seller.getSellerProducts()) {
                    if (ans7 == p.getId()) {
                        store.removeProduct(ans7);
                        seller.removeProduct(ans7);
                        flag2 = true;
                        System.out.println("Item " + ans7 + " has been removed successfully.");
                        printStream.println("REMOVE ITEM{ seller's username: " + seller.getUsername() + ", item's name:" + p.getName()
                                + ", item's tag:" + p.getTag() + ", item's id:" + p.getId() + "}");
                        break;
                    }
                }
                if (!flag2) {
                    System.out.println("You can’t remove this item.");
                }
                break;
            case 4:
                printStream.println("LOG OUT{ role: seller, username: " + seller.getUsername() + "}");
                start();
                return;
        }
        printStream.flush();
        printStream.close();
        singInSeller(seller);
    }

    public static void singInAdmin() throws IOException {
        System.out.println();
        System.out.println("Here's a list of available commands for you:\n" + "1. list of unverified users " +
                "\n" + "2. list of all users\n" + "3. remove user \n" + "4. remove item\n"
                + "5. users' activities\n" + "6. log out");
        String ans4Str = myScanner.next();
        if (ans4Str.equals("back")) {
            singIn();
            return;
        }
        int ans4 = Integer.parseInt(ans4Str);
        switch (ans4) {
            case 1:
                System.out.println(store.getUnapprovedSellers().toString());
                System.out.println("Do you want to approve any seller?(0 = no , 1 = yes)");
                String ans5Str = myScanner.next();
                if (ans5Str.equals("back")) {
                    singInAdmin();
                    return;
                }
                int ans5 = Integer.parseInt(ans5Str);
                if (ans5 == 1) {
                    System.out.println("Which user do you want to approve?(Enter username)");
                    String approvedUser = myScanner.next();
                    if (approvedUser.equals("back")) {
                        singInAdmin();
                        return;
                    }
                    store.approveSeller(approvedUser);
                }
                break;
            case 2:
                System.out.println("sellers:\n" + store.getAllSellers().toString());
                System.out.println("customers:\n" + store.getAllCustomers().toString());
                break;
            case 3:
                System.out.println("Which user do you want to remove?(Enter username)");
                String user = myScanner.next();
                if (user.equals("back")) {
                    singInAdmin();
                    return;
                }
                boolean flag = store.removeUser(user);
                if (flag) {
                    System.out.println("User " + user + " has been removed successfully!");
                }else{
                    System.out.println("You can't remove this user.");
                }
                break;
            case 4:
                System.out.println("Which item do you want to remove?(Enter item's id)");
                String removeProductStr = myScanner.next();
                if (removeProductStr.equals("back")) {
                    singInAdmin();
                    return;
                }
                int removeProduct = Integer.parseInt(removeProductStr);
                boolean flag2 = store.removeProduct(removeProduct);
                store.removeProduct(removeProduct);
                if (flag2) {
                    System.out.println("Item " + removeProduct + " has been removed successfully.");
                }else{
                    System.out.println("You can't remove this item.");
                }

                break;
            case 5:
                File file = file();
                Scanner scanner = new Scanner(file);

                while (scanner.hasNext()) {
                    String str = scanner.nextLine();
                    System.out.println(str);
                }
                break;
            case 6:
                start();
                return;
        }
        singInAdmin();
    }

    public static void singInCustomer(Customer customer) throws IOException {
        File file = file();
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);

        System.out.println();
        System.out.println("Here's a￼list of available commands for you:\n" + "1. account info\n" + "2. add balance\n" +
                "3. list of items\n" + "4. search by tag\n" + "5. log out");
        String ans8Str = myScanner.next();
        if (ans8Str.equals("back")) {
            singInCustomer(customer);
            return;
        }
        int ans8 = Integer.parseInt(ans8Str);

        switch (ans8) {
            case 1:
                System.out.println(customer.toString());
                System.out.println();
                break;
            case 2:
                System.out.println("How much balance do you want to add?");
                String balanceStr = myScanner.next();
                if (balanceStr.equals("back")) {
                    singInCustomer(customer);
                    return;
                }
                int balance = Integer.parseInt(balanceStr);
                customer.addBalance(balance);
                System.out.println(balance + "$ has been added to your wallet successfully!");
                printStream.println("ADD BALANCE{ customer's username: " + customer.getUsername() + ", balance amount: " + balance + "}");
                break;
            case 3:
                System.out.println(store.getAllProducts().toString());
                System.out.println("Do you want to buy any item?(0 = no , 1 = yes)");
                String ans9Str = myScanner.next();
                if (ans9Str.equals("back")) {
                    singInCustomer(customer);
                    return;
                }
                int ans9 = Integer.parseInt(ans9Str);
                if (ans9 == 1) {
                    System.out.println("Which item do you want to buy?(Enter product's id)");
                    String ans10Str = myScanner.next();
                    if (ans10Str.equals("back")) {
                        singInCustomer(customer);
                        return;
                    }
                    int ans10 = Integer.parseInt(ans10Str);
                    Product pro = null;
                    for (Product p : store.getAllProducts()) {
                        if (p.getId() == ans10) {
                            pro = p;
                        }
                    }
                    if (pro != null) {
                        customer.buyProducts(pro);
                        printStream.println("BUY ITEM{ customer's username: " + customer.getUsername() + ", item's name:" + pro.getName()
                                + ", item's tag:" + pro.getTag() + ", item's id:" + pro.getId() + ", item's price:" + pro.getPrice() + "}");
                    } else {
                        System.out.println("This item does not exist!");
                    }
                }
                break;
            case 4:
                System.out.println("Please enter the tag name:");
                String ans11 = myScanner.next();
                if (ans11.equals("back")) {
                    singInCustomer(customer);
                    return;
                }
                store.sameTagProducts(ans11);
                System.out.println(store.getSameTag().toString());
                store.clearSameTagList();
                System.out.println("Do you want to buy any item?(0 = no , 1 = yes)");
                String ans12Str = myScanner.next();
                if (ans12Str.equals("back")) {
                    singInCustomer(customer);
                    return;
                }
                int ans12 = Integer.parseInt(ans12Str);
                if (ans12 == 1) {
                    System.out.println("Which item do you want to buy?(Enter product's id)");
                    String ans13Str = myScanner.next();
                    int ans13 = Integer.parseInt(ans13Str);
                    Product pro = null;
                    for (Product p : store.getAllProducts()) {
                        if (p.getId() == ans13) {
                            pro = p;
                        }
                    }
                    if (pro != null) {
                        customer.buyProducts(pro);
                        printStream.println("BUY ITEM{ customer's username: " + customer.getUsername() + ", item's name:" + pro.getName()
                                + ", item's tag:" + pro.getTag() + ", item's id:" + pro.getId() + ", item's price:" + pro.getPrice() + "}");
                    } else {
                        System.out.println("This item does not exist!");
                    }
                }
                break;
            case 5:
                printStream.println("LOG OUT{ role: customer, username: " + customer.getUsername() + "}");
                start();
                return;
        }
        printStream.flush();
        printStream.close();
        singInCustomer(customer);
    }
}
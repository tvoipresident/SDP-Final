import java.util.ArrayList;
import java.util.List;

public class Admin {
    private List<Customer> customers;

    public Admin() {
        this.customers = new ArrayList<>();
    }

    public void viewCustomerList() {
        System.out.println("Customer List:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getName());
        }
    }

    public void deleteCustomer(int index) {
        if (index >= 0 && index < customers.size()) {
            System.out.println("Deleted customer: " + customers.get(index).getName());
            customers.remove(index);
        } else {
            System.out.println("Invalid customer index.");
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}

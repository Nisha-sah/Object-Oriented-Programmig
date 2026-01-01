package studentmanagement;

import java.util.*;

/**
 * Represents a product in the e-commerce system.
 */
class Product {
    private int productId;
    private String name;
    private double price;
    private int stockQuantity;

    /**
     * Constructor to create a product.
     */
    public Product(int productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    /**
     * Display product details.
     */
    public void getDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Stock: " + stockQuantity);
        System.out.println("----------------------");
    }

    /**
     * Check if the required quantity is available.
     */
    public boolean checkAvailability(int quantity) {
        return stockQuantity >= quantity;
    }

    /**
     * Reduce the stock quantity after purchase.
     */
    public void reduceStock(int quantity) {
        if(quantity <= stockQuantity) {
            stockQuantity -= quantity;
        }
    }
}

/**
 * Represents a shopping cart containing products.
 */
class ShoppingCart {
    private Map<Product, Integer> cartItems;

    public ShoppingCart() {
        cartItems = new HashMap<>();
    }

    /**
     * Add a product with quantity to the cart.
     */
    public void addProduct(Product product, int quantity) {
        if(product.checkAvailability(quantity)) {
            cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
            System.out.println(quantity + " x " + product.getName() + " added to cart.");
        } else {
            System.out.println("Sorry, " + product.getName() + " is out of stock.");
        }
    }

    /**
     * Remove a product from the cart.
     */
    public void removeProduct(Product product) {
        if(cartItems.containsKey(product)) {
            cartItems.remove(product);
            System.out.println(product.getName() + " removed from cart.");
        } else {
            System.out.println(product.getName() + " is not in the cart.");
        }
    }

    /**
     * Calculate the total price of the cart.
     */
    public double calculateTotalPrice() {
        double total = 0;
        for(Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    /**
     * Display all items in the cart with quantities and total price.
     */
    public void viewCartItems() {
        if(cartItems.isEmpty()) {
            System.out.println("Shopping cart is empty.");
        } else {
            System.out.println("Cart Items:");
            for(Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                System.out.println(entry.getKey().getName() + " x " + entry.getValue() 
                    + " = $" + (entry.getKey().getPrice() * entry.getValue()));
            }
            System.out.println("Total Price: $" + calculateTotalPrice());
        }
    }

    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }

    /**
     * Clear the cart after checkout.
     */
    public void clearCart() {
        cartItems.clear();
    }
}

/**
 * Represents a customer in the e-commerce system.
 */
class Customer {
    private int customerId;
    private String name;
    private String email;
    private String password;

    public Customer(int customerId, String name, String email, String password) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Register the customer (dummy for demo).
     */
    public void register() {
        System.out.println(name + " registered successfully with email " + email);
    }

    /**
     * Login customer (dummy login, always checks credentials).
     */
    public boolean login(String email, String password) {
        if(this.email.equals(email) && this.password.equals(password)) {
            System.out.println(name + " logged in successfully.");
            return true;
        } else {
            System.out.println("Login failed.");
            return false;
        }
    }

    /**
     * View a list of available products.
     */
    public void viewProducts(List<Product> products) {
        System.out.println("Available Products:");
        for(Product p : products) {
            p.getDetails();
        }
    }

    /**
     * Add a product to the shopping cart.
     */
    public void addToCart(Product product, int quantity, ShoppingCart cart) {
        cart.addProduct(product, quantity);
    }

    /**
     * Proceed to checkout and reduce stock quantities.
     */
    public void checkout(ShoppingCart cart) {
        System.out.println("Checkout complete. Total price: $" + cart.calculateTotalPrice());
        for(Map.Entry<Product, Integer> entry : cart.getCartItems().entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
        cart.clearCart();
    }
}

/**
 * Main class to run the E-Commerce Shopping Cart System.
 */
public class ShoppingCart {
    public static void main(String[] args) {
        // Create some products
        Product prod1 = new Product(101, "Laptop", 1200.0, 5);
        Product prod2 = new Product(102, "Smartphone", 800.0, 10);
        Product prod3 = new Product(103, "Headphones", 150.0, 15);

        List<Product> products = Arrays.asList(prod1, prod2, prod3);

        // Create a customer
        Customer customer1 = new Customer(201, "Alice", "alice@example.com", "password123");
        customer1.register();
        customer1.login("alice@example.com", "password123");

        // View available products
        customer1.viewProducts(products);

        // Create a shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Add products to cart
        customer1.addToCart(prod1, 1, cart);
        customer1.addToCart(prod3, 2, cart);

        // View cart items
        cart.viewCartItems();

        // Checkout
        customer1.checkout(cart);

        // View cart after checkout
        cart.viewCartItems();

        // Show stock after checkout
        System.out.println("Stock after checkout:");
        for(Product p : products) {
            p.getDetails();
        }
    }
}

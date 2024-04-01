/**
 * Name: Busra Zenbilci
 * Student Number: 20170808054
 * Date: 30.05.2023
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 class Product {

    private Long Id;
    private String Name;

    private double Price;


    public Product(Long Id, String Name, double Price)
            throws InvalidPriceException, InvalidAmountException {
        this.Id = Id;
        this.Name = Name;

        if (Price < 0) {
            throw new InvalidPriceException();
        } else {
            this.Price = Price;
        }
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) throws InvalidPriceException {
        if(price < 0){
            throw new InvalidPriceException();
        }else
            this.Price = price;
    }


    public boolean equals(Object o) {
        if (!(o instanceof Product)) {
            return false;
        }
        Product other = (Product) o;
        return Math.abs(Price - other.Price) < 0.001;
    }

    public String toString() {
        return  + getId() + " - " + Name + " @ " + Price;
    }
}


class CleaningProduct extends Product {

    private boolean Liquid;

    private String WhereToUse;

    public CleaningProduct(Long id, String name, double price, boolean Liquid, String WhereToUse) {
        super(id, name, price);
        this.Liquid = Liquid;
        this.WhereToUse = WhereToUse;
    }

    public String getWhereToUse() {
        return WhereToUse;
    }

    public void setWhereToUse(String whereToUse) {
        WhereToUse = whereToUse;
    }

    public boolean isLiquid() {
        return Liquid;
    }
}

class FoodProduct extends Product {

    private int Calories;
    private boolean Dairy;
    private boolean Eggs;
    private boolean Peanuts;
    private boolean Gluten;

    public FoodProduct(Long Id, String Name, double Price, int calories, boolean dairy,
                       boolean eggs, boolean peanuts, boolean gluten) throws InvalidAmountException {
        super(Id, Name, Price);
        if(calories < 0) {
            throw new InvalidAmountException(calories);
        }
        else{
            this.Calories = calories;
        }
        this.Dairy = dairy;
        this.Eggs = eggs;
        this.Gluten = gluten;
        this.Peanuts = peanuts;

    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) throws InvalidAmountException {
        if(calories < 0){
            throw new InvalidAmountException(calories);
        }
        Calories = calories;
    }

    public boolean containsDairy() {
        return Dairy;
    }

    public boolean containsEggs() {
        return Eggs;
    }

    public boolean containsPeanuts() {
        return Peanuts;
    }

    public boolean containsGluten() {
        return Gluten;
    }
}

class Customer {
    private String Name;
    private Map<Store, List<Product>> cart;
    private Map<Store, List <Integer>> Count;
    private double totalDue;

    private Map<Store, Integer> points;

    public Customer(String name) {
        this.Name = name;
        this.cart = new HashMap<>();
        this.Count = new HashMap<>();
        this.totalDue = 0.0;
        this.points = new HashMap<>();

    }

    public void addToCart(Store store ,Product product, int count ){

        try {
            if (store.getInventory().containsKey(product) && store.getInventory().get(product) >= count) {
                store.purchase(product, count);

                List<Product> storeCart = cart.getOrDefault(store, new ArrayList<>());
                List<Integer> storeCount = this.Count.getOrDefault(store, new ArrayList<>());

                storeCart.add(product);
                storeCount.add(count);
                cart.put(store, storeCart);
                this.getCount().put(store, storeCount);
                totalDue += product.getPrice() * count;

            } else {
                System.out.println("ERROR: ProductNotFoundException: ID - " + product.getId() + " Name - " + product.getName());
            }
        }catch (ProductNotFoundException e){
            System.out.println("ERROR: ProductNotFoundException " + e.getMessage());
        }
    }

    public String receipt(Store store){
        if (!cart.containsKey(store)) {
            throw new StoreNotFoundException("Customer does not have a cart for the store.");
        }

        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("Customer receipt for ").append(store.getName()).append("\n");
        receiptBuilder.append("\n");
        System.out.println();
        List<Product> storeCart = cart.get(store);
        List<Integer> storeCount = Count.get(store);

        for (int i = 0; i < storeCart.size(); i++) {
            Product product = storeCart.get(i);
            int productCount = storeCount.get(i);
            double productTotal = product.getPrice() * productCount;
            receiptBuilder.append(product.getId()).append(" - ").append(product.getName())
                    .append(" @ ").append(product.getPrice()).append(" X ").append(productCount)
                    .append(" ... ").append(productTotal).append("\n");


        }
        receiptBuilder.append("\n").append("---------------------\n").append("\n");
        receiptBuilder.append("Total Due - ").append(totalDue);
        return receiptBuilder.toString();
    }


    public double getTotalDue(Store store) throws StoreNotFoundException{
        if(!cart.containsKey(store)) {
            throw new StoreNotFoundException("Customer does not have a cart for the store.");
        }
        return totalDue;

    }

    public int getPoints (Store store) throws StoreNotFoundException{
        if(!cart.containsKey(store)){
            throw new StoreNotFoundException("Customer is not in the Customer collection for the store.");
        }
        return points.get(store);
    }

    public double pay(Store store, double amount, boolean usePoints)
            throws InsufficientFundsException, StoreNotFoundException{

        System.out.println();
        for(int i = 0; i <= 1; i++) {
            double totalDue = getTotalDue(store);
            int points = 0;
            try {
                points = store.getCustomerPoints(this);
            } catch (Exception e) {
                points = 0;
            }
            if (usePoints && points > 0) {
                double valueOfPoints = points * 0.01;
                if (valueOfPoints >= totalDue) {
                    points -= (int) (totalDue * 100);
                    totalDue = 0;
                } else {
                    totalDue -= valueOfPoints;
                    points = 0;
                }
            }
            if (amount >= getTotalDue(store)) {
                double change = amount - getTotalDue(store);
                System.out.println("Thank you for your business");
                getCart().clear();
                getCount().clear();
                totalDue = 0;
                return change;
            } else
                throw new InsufficientFundsException(totalDue, amount);
        }

        if (amount >= getTotalDue(store)) {
            double change = amount - getTotalDue(store);
            System.out.println("Thank you for your business");
            getCart().clear();
            getCount().clear();
            totalDue = 0;
            return change;
        } else
            throw new InsufficientFundsException(totalDue, amount);

    }

    public Map<Store, List<Product>> getCart() {
        return cart;
    }

    public void setCart(Map<Store, List<Product>> cart) {
        this.cart = cart;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Map<Store, List<Integer>> getCount() {
        return Count;
    }

    public void setCount(Map<Store, List<Integer>> count) {
        Count = count;
    }

    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }

}

class Store {
    private String Name;
    private String Website;
    private final Map<Product, Integer> inventory;
    private final Map<Customer, Integer> customerPoints;
    private int Quantity;

    public Store(String name, String website) {
        this.Name = name;
        this.Website = website;
        this.inventory = new HashMap<>();
        this.customerPoints = new HashMap<>();

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public Map<Product, Integer> getInventory() {
        return inventory;
    }

    public Map<Customer, Integer> getCustomerPoints() {
        return customerPoints;
    }

    public int getCount(){
        return inventory.size();
    }

    public int remaining() {
        return Quantity;
    }

    public void addCustomer(Customer customer) {
        customerPoints.put(customer , 0);
    }

    public int getProductCount (Product product) throws ProductNotFoundException {
        if(inventory.containsKey(product)) {
            return inventory.get(product);
        }
        throw new ProductNotFoundException(product.getName());
    }

    public int getCustomerPoints (Customer customer) throws CustomerNotFoundException{
        if(inventory.containsKey(customer)) {
            return customerPoints.get(customer);
        }
        throw new CustomerNotFoundException(customer.getName());
    }


    public void removeProduct(Product product) throws ProductNotFoundException{
        if(inventory.containsKey(product)){
            inventory.remove(product);
        }
        throw new ProductNotFoundException(product.getName());
    }


    public void addToInventory(Product product, int amount) throws InvalidAmountException {
        if(amount < 0){
            throw new InvalidAmountException("Amount cannot be negative.");
        }

        if (inventory.containsKey(product)) {
            int currentAmount = inventory.get(product);
            inventory.put(product, currentAmount + amount);
        }
        else{
            inventory.put(product, amount);
        }

    }

    public double purchase(Product product, int amount) throws InvalidAmountException, ProductNotFoundException {
        if(amount < 0){
            throw new InvalidAmountException("Amount cannot be negative.");
        }

        if(inventory.containsKey(product)) {
            int currentAmount = inventory.get(product);
            if(amount <= currentAmount){
                inventory.put(product, currentAmount - amount);
                return product.getPrice() * amount;
            }
            else{
                throw new InvalidAmountException("Insufficient quantity in inventory.");
            }
        }
        else{
            throw new ProductNotFoundException(product.getName());
        }

    }

}

class ProductNotFoundException extends IllegalArgumentException{
    Long ID;
    String name;

    Product product;

    public ProductNotFoundException(Long ID){
        this.name = null;
    }

    public ProductNotFoundException(String name){
        this.ID = ID;
        this.product = product;
    }


    public String toString() {

        return "ProductNotFoundException: ID -" + ID + " Name -" + name;

    }
}

class CustomerNotFoundException extends IllegalArgumentException{
    private String phone;

    private Customer customer;

    public CustomerNotFoundException(String phone) {
        this.phone = phone;
    }

    public String toString() {

        return "CustomerNotFoundException: Name - " + customer.getName();
    }
}


class InsufficientFundsException extends RuntimeException{
    double total;
    double payment;

    public InsufficientFundsException(double totalDue, double amount) {
    }

    public String toString() {
        return "InsufficientFundsException" + total + " due, but only " + payment + "given";
    }
}


class InvalidAmountException extends RuntimeException {
    int amount;
    int quantity;


    public InvalidAmountException(int amount) {

        this.amount = this.amount;
        this.quantity = this.quantity;
    }

    public InvalidAmountException(String s) {

        this.amount = amount;
    }

    public int getAmount(){     //???
        return amount;
    }

    public int getQuantity(){
        return quantity;
    }

    public String toString(){
        if(quantity == 0){
            return "InvalidAmountException: " + amount;
        }
        else{
            return "InvalidAmountException: " + amount + " was requested, but only " + quantity + " remaining.";
        }
    }

}


class InvalidPriceException extends RuntimeException {
    double price;

    public String toString(){
        return "InvalidPriceException" + price;
    }
}

 class StoreNotFoundException extends IllegalArgumentException{

    private String name;

    public StoreNotFoundException(String name) {
        this.name = name;
    }

    public String toString(){
        return "StoreNotFoundException: " + name;
    }
}







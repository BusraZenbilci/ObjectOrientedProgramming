/**
 * author: Busra Zenbilci
 * number: 20170808054
 * date: 15.04.2023
 */

import java.util.ArrayList;
import java.util.List;

public class Assignment02_20170808054 {
}

class CustomerNotFoundException extends IllegalArgumentException{
    private String phone;

    public CustomerNotFoundException(String phone) {
        this.phone = phone;
    }

    public String toString() {

        return "CustomerNotFoundException: " + phone;
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

    public InvalidAmountException() {

        this.amount = amount;
    }

    public int getAmount(){     //???
        return amount;
    }

    public int getQuantity(){
        return quantity;
    }

    public String toString(){   // ???
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

class ProductNotFoundException extends IllegalArgumentException{
    Long ID;
    String name;

    public ProductNotFoundException(Long ID){
        this.name = null;
    }

    public ProductNotFoundException(String name){
        this.ID = (long)(10);   // ???
    }

    public String toString() {
        if(name ==  null){
            return "ProductNotFoundException: ID -" + ID;
        }
        else{
            return "ProductNotFoundException: Name -" + name;
        }
    }
}
class Product {

    private Long Id;
    private String Name;
    private int Quantity;
    private double Price;


    public Product(Long Id, String Name, int Quantity, double Price)
            throws InvalidPriceException, InvalidAmountException {
        this.Id = Id;
        this.Name = Name;
        if(Quantity < 0){
            throw new InvalidAmountException(Quantity);
        }else {
            this.Quantity = Quantity;
        }
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

    public int remaining() {
        return Quantity;
    }

    public int addToInventory(int amount) throws InvalidAmountException {
        if (amount < 0) {
            throw new InvalidAmountException(amount);

        } else {
            return this.Quantity += amount;
        }

    }

    public double purchase(int amount) throws InvalidAmountException {
        if(amount < 0){
            throw new InvalidAmountException();
        }

        if (amount > Quantity)  {
            throw new InvalidAmountException(amount);     // ???

        }

        Quantity -= amount;
        double totalPrice = amount * Price;
        return totalPrice;

    }

    public boolean equals(Object o) {
        if (!(o instanceof Product)) {
            return false;
        }
        Product other = (Product) o;
        return Math.abs(Price - other.Price) < 0.001;
    }

    public String toString() {
        return "Product " + Name + " has " + Quantity + " remaining";
    }
}

class FoodProduct extends Product {

    private int Calories;
    private boolean Dairy;
    private boolean Eggs;
    private boolean Peanuts;
    private boolean Gluten;

    public FoodProduct(Long Id, String Name, int Quantity, double Price, int calories, boolean dairy,
                       boolean eggs, boolean peanuts, boolean gluten) throws InvalidAmountException {
        super(Id, Name, Quantity , Price);
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

class CleaningProduct extends Product {

    private boolean Liquid;

    private String WhereToUse;

    public CleaningProduct(Long id, String name, int quantity, double price, boolean Liquid, String WhereToUse) {
        super(id, name, quantity, price);
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


class Customer {

    private String Name;
    private List<Product> cart;
    private List <Integer> Count;
    private double totalDue;

    public Customer(String name) {
        this.Name = name;
        this.setCount(new ArrayList<>());
        this.setCart(new ArrayList<>());
        this.totalDue = 0.0;   // default

    }

    public void addToCart(Product product, int count ){
        try{
            product.purchase(count);
            cart.add(product);
            Count.add(count);
            totalDue += product.getPrice() * count;
        }
        catch (InvalidAmountException ex){
            System.err.println("ERROR: " + ex.toString());
        }
    }

    public String receipt(){
        StringBuilder receiptBuilder = new StringBuilder();
        List<Product> cart = getCart();
        List<Integer> countList = getCount();

        if (cart != null && countList != null && cart.size() == countList.size()) {
            cart.forEach((product) -> {
                int count = countList.get(cart.indexOf(product));
                receiptBuilder.append(product.getName())
                        .append(" - ")
                        .append(product.getPrice())
                        .append(" X ")
                        .append(count)
                        .append(" ... ")
                        .append(product.getPrice() * count)
                        .append("\n");
            });
            receiptBuilder.append("Total Due - ").append(totalDue);
        } else {
            receiptBuilder.append("Error");
        }

        return receiptBuilder.toString();
    }

    public double getTotalDue(){
        return totalDue;
    }

    public double pay(double amount) throws InsufficientFundsException{

        double change = amount - totalDue;
        if (change >= 0) {
            System.out.println("Thank you");
            getCart().clear();
            getCount().clear();
            totalDue = 0.0;
            return change;
        }
        else{
            throw new InsufficientFundsException(totalDue, amount);
        }

    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Integer> getCount() {
        return Count;
    }

    public void setCount(List<Integer> count) {
        this.Count = count;
    }

    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }


}


class ClubCustomer extends Customer {

    private String Phone;
    private int Points;


    public ClubCustomer(String name, String Phone) {
        super(name);
        this.Phone = Phone;
        this.Points = 0;   // default
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getPoints() {
        return Points;
    }

    public void addPoints(int points) {
        if (points > 0) {
            this.Points += points;
        }
    }

    public double pay(double amount, boolean usePoints) throws InsufficientFundsException{
        double totalAfterPoints = super.getTotalDue();
        if (usePoints && Points > 0) {
            totalAfterPoints -= (Points * 0.01);
        }

        if (amount >= totalAfterPoints) {
            double change = amount - totalAfterPoints;
            System.out.println("Thank you for shopping with us.");
            getCart().clear();
            getCount().clear();
            super.setTotalDue(0.0);

            if (usePoints) {

                if (totalAfterPoints <= 0 && Points > 0) {
                    Points -= (int) (super.getTotalDue() * 100);
                }
                else{
                    Points = 0;

                }

            }
            Points += (int) (totalAfterPoints);
            return  change;
        }
        else{
            throw new InsufficientFundsException(super.getTotalDue(), amount);
        }


    }


    public String toString() {
        return  super.toString() + " has " + Points + " points";
    }
}




/**
 *  author: Busra Zenbilci 20170808054
 *  since: 24.03.2023
 */

import java.util.ArrayList;
import java.util.List;

public class Assignment01_20170808054 {

}


    class Product {

        private String Id;
        private String Name;
        private int Quantity;
        private double Price;

        public Product(String id, String name, int quantity, double price) {
            this.Id = id;
            this.Name = name;
            this.Quantity = quantity;
            this.Price = price;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
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

        public void setPrice(double price) {
            this.Price = price;
        }

        public int remaining() {
            return Quantity;
        }

        public int addToInventory(int amount) {
            if (amount < 0) {
                return Quantity;
            } else {
                return Quantity += amount;
            }

        }

        public double purchase(int amount) {
            if (amount > 0 && amount > Quantity) {
                return 0;

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

        public FoodProduct(String id, String Name, int Quantity, double price, int calories, boolean Diary, boolean eggs, boolean peanuts, boolean gluten) {
            super(id, Name, Quantity, price);
            this.Calories = calories;
            this.Dairy = Dairy;
            this.Eggs = eggs;
            this.Gluten = gluten;
            this.Peanuts = peanuts;

        }

        public int getCalories() {
            return Calories;
        }

        public void setCalories(int calories) {
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


        public CleaningProduct(String id, String name, int quantity, double price, boolean Liquid, String WhereToUse) {
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

        public Customer(String name) {
            this.Name = name;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String toString() {
            return Name;
        }

    }

     class ClubCustomer extends Customer {

        private String Phone;
        private int Points;


        public ClubCustomer(String name, String Phone) {
            super(name);
            this.Phone = Phone;
            this.Points = 0;
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

        public String toString() {
            return super.toString() + " has " + Points + " points";
        }

    }


     class Store {

        private String Name;
        private String Website;

        private List<Product> inventory;

        public Store(String name, String website) {
            this.Name = name;
            this.Website = website;
            this.inventory = new ArrayList<>();

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

        public int getInventorySize() {
            return inventory.size();
        }

        public void addProduct(Product product, int index) {
            if (index < 0 || index > inventory.size()) {
                inventory.add(product);
            } else {
                inventory.add(index, product);
            }
        }

        public void addProduct(Product product) {
            inventory.add(product);
        }

        public Product getProduct(int index) {
            if (index < 0 || index >= inventory.size()) {
                return null;
            } else {
                return inventory.get(index);
            }
        }

        public int getProductIndex(Product p) {
            return inventory.indexOf(p);
        }

    }







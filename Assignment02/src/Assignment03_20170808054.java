/**
 * @author: Büşra Zenbilci
 * number: 20170808054
 * date: 04.04.2022
 */

import javax.security.auth.login.AccountNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Assignment03_20170808054 {
}

class Transaction {
    private int type;
    private String to;
    private String from;
    private double amount;

     public Transaction (int type, String to, String from, double amount){

     }
}

 class Bank {
    private String Name;
    private String Address;

    ArrayList<Customer> Customers = new ArrayList<Customer>();
    ArrayList<Company> Companies = new ArrayList<Company>();
    ArrayList< Account> Accounts = new ArrayList< Account>();

    public Bank(String Name, String Address){
        this.Name=Name;
        this.Address=Address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    void addCustomer(int id, String name){
        Customer customer = new Customer(id, name);
        Customers.add(id, customer);
    }

    void addCompany(int id, String name){
        Company company=new Company(id, name);
        Companies.add(id,company);
    }
    void addAccount(Account account){
        Accounts.add(account);
    }

    Customer getCustomer(int id) throws CustomerNotFoundException{
        if(Customers.contains(id)){
            return Customers.get(id);
        }
        else
            throw new CustomerNotFoundException(id);
    }
    Customer getCustomer(String name, String surname) throws CustomerNotFoundException {
        if (Customers.contains(name) && Customers.contains(surname)) {

            for (Customer c : Customers) {
                if (c.getName().equals(name) && c.getSurname().equals(surname)) {
                    return c;
                }
            }
            return null;
        }
        else {
            throw new CustomerNotFoundException(name , surname);
        }
    }

    Company getCompany(int id) throws CompanyNotFoundException{
        if(Companies.contains(id)){
            return Companies.get(id);
        }
        else
            throw new CompanyNotFoundException(id);
    }

    Company getCompany(String name) throws CompanyNotFoundException{
        if(Companies.contains(name)){
            for(Company cmp: Companies){
                if(cmp.getName().equals(name)){
                    return cmp;
                }
            } return null;
        }
        else{
            throw new CompanyNotFoundException(name);
        }
    }
    Account getAccount(String accountNum) throws AccountNotFoundException{
        if(Accounts.contains(accountNum)){
            for(Account a: Accounts){
                if(a.getAcctNum().equals(accountNum)){
                    return a;
                }
            } return null;
        }
        else {
            throw new AccountNotFoundException();
        }
    }

    void closeAccount(String accountNum)
            throws AccountNotFoundException, BalanceRemainingException{
        if(Accounts.contains(accountNum)){
            for(Account a: Accounts){
                if(a.getAcctNum().equals(accountNum)){
                    if(a.getBalance()==0){
                        Accounts.remove(a);
                    }
                    else{
                        throw new BalanceRemainingException();
                    }
                }
            }
        }
        else{
            throw new AccountNotFoundException();
        }
    }

    void transferFunds(String accountFrom, String accountTo, double amount)
            throws AccountNotFoundException, InvalidAmountException {
        if(Accounts.contains(accountFrom) && Accounts.contains(accountTo)){
            if(amount>0 && Accounts.get(Integer.parseInt(accountFrom)).getBalance()>amount){
                Accounts.get(Integer.parseInt(accountFrom)).withdrawal(amount);
                Accounts.get(Integer.parseInt(accountTo)).deposit(amount);
            }
            else
                throw new InvalidAmountException();
        }
        else
            throw new ArithmeticException();
    }

}

class Account {

    private String AcctNum;
    private double Balance;

    public Account(String AcctNum) {
        Balance = 0;
        this.AcctNum = AcctNum;
    }

    public Account(String AcctNum, double Balance) {
        this.AcctNum = AcctNum;

        if (Balance < 0) {
            Balance = 0;
        } else
            this.Balance = Balance;
    }

    public Account() {

    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount >= 0) {
            Balance += amount;
        } else {
            throw new InvalidAmountException();
        }
    }

    public void withdrawal(double amount) throws InvalidAmountException {
        if (amount < 0 || amount > getBalance()) {
            throw new InvalidAmountException();
        } else {
            Balance -= amount;
        }
    }

    public String getAcctNum() {
        return AcctNum;
    }

    public double getBalance() {
        return Balance;
    }
}

class PersonalAccount extends Account {

    private String Name;
    private String Surname;
    private String PIN;

    public PersonalAccount(String AcctNum, String Name, String Surname) {

        super(AcctNum);

        this.Name = Name;
        this.Surname = Surname;

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        Random random = new Random();
        this.PIN = " ";
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(numbers.size());
            this.PIN += numbers.get(index).toString();
            this.PIN = PIN;
        }


    }

    public PersonalAccount(String AcctNum, String Name, String Surname, double Balance) {
        super(AcctNum, Balance);
        this.Name = Name;
        this.Surname = Surname;

    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    @Override
    public String toString() {
        return
                "Account " + super.getAcctNum() +
                        " belonging to " + Name + " " +
                        Surname.toUpperCase() + " has " + super.getBalance();
    }

}

class BusinessAccount extends Account{
    private double rate;

    public BusinessAccount(String AcctNum, double rate){
        super(AcctNum);
        this.rate=rate;
    }

    public BusinessAccount(String AcctNum, double Balance, double rate){
        super(AcctNum, Balance);
        this.rate=rate;
    }
    public double calculateInterest(){
        return rate*getBalance();
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        rate = rate;
    }

}

class Customer {

    private int id;
    private String Name, Surname;
    ArrayList<PersonalAccount> PersonalAccounts = new ArrayList<PersonalAccount>();


    public Customer(int id,String Name, String Surname){
        if(id > 0)
            this.id=id;
        else
            this.id=0;

        this.Name=Name;
        this.Surname=Surname;
    }

    public Customer(int id, String Name) {
        if(id > 0)
            this.id=id;
        else
            this.id=0;

        this.Name=Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }


    public void openAccount(String acctNum){
        for(PersonalAccount pa: PersonalAccounts){
            if(pa.getAcctNum().equals(acctNum)){
                PersonalAccounts.add(pa);
            }
        }
    }

    Account getAccount(String accountNum) throws AccountNotFoundException {
        if (PersonalAccounts.contains(accountNum)) {
            for (PersonalAccount pa : PersonalAccounts) {
                if (pa.getAcctNum().equals(accountNum)) {
                    return pa;
                }
            } return null;
        }
        else {
            throw new AccountNotFoundException();
        }
    }

    void closeAccount(String accountNum) throws AccountNotFoundException, BalanceRemainingException{
        if (PersonalAccounts.contains(accountNum)) {
            for (PersonalAccount pa : PersonalAccounts) {
                if (pa.getAcctNum().equals(accountNum)) {
                    if(pa.getBalance()==0) {
                        PersonalAccounts.remove(pa);
                    }
                    else{
                        throw new BalanceRemainingException();
                    }
                }
            }
        }
        else {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public String toString() {
        return Name + " " + Surname.toUpperCase();
    }
}


class Company {
    private int id;
    private String Name;
    ArrayList<BusinessAccount>  BusinessAccounts = new ArrayList<BusinessAccount>();

    public Company(int id, String Name){
        if(id > 0)
            this.id=id;
        else
            this.id=0;
        this.Name=Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void openAccount(String acctNum, double rate){
        for(BusinessAccount ba: BusinessAccounts){
            if(ba.getAcctNum().equals(acctNum) && ba.equals(rate)){
                BusinessAccounts.add(ba);
            }
        }
    }

    Account getAccount(String acctNum) throws AccountNotFoundException {
        if(BusinessAccounts.contains(acctNum)){
            for(BusinessAccount ba: BusinessAccounts ){
                if(ba.getAcctNum().equals(acctNum)){
                    return ba;
                }
            } return null;
        }
        else{
            throw new AccountNotFoundException();
        }
    }

    void closeAccount(String accountNum ) throws AccountNotFoundException, BalanceRemainingException {
        if(BusinessAccounts.contains(accountNum)){
            for(BusinessAccount ba: BusinessAccounts){
                if(ba.getAcctNum().equals(accountNum)){
                    if(ba.getBalance()==0){
                        BusinessAccounts.remove(ba);
                    }
                    else{
                        throw new BalanceRemainingException();
                    }
                }
            }
        }
        else{
            throw new AccountNotFoundException();
        }
    }


    @Override
    public String toString() {
        return Name;
    }
}

public class AccountNotFoundException extends RuntimeException{

    private String acctNum;

    @Override
    public String toString() {
        return "AccountNotFoundException{" +
                "acctNum='" + acctNum + '\'' +
                '}';
    }
}

class InvalidAmountException extends RuntimeException {
    private double amount;

    @Override
    public String toString() {
        return "InvalidAmountException{" + amount;
    }
}




public class BalanceRemainingException extends RuntimeException {
    private double balance;

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BalanceRemainingException{" + balance;
    }
}

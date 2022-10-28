public class InvalidRankException extends RuntimeException {

    private int rank;

    @Override
    public String toString() {
        return "InvalidRankException: " + rank;
    }
}

public class CompanyNotFoundException extends RuntimeException {
    private int id;
    private String name;

    public CompanyNotFoundException(int id) {
        this.id = id;
        this.name = null;
    }

    public CompanyNotFoundException(String name) {
        this.name = name;
        this.id = 1;
    }

    @Override
    public String toString() {
        if (name.equals(null)) {
            return "CompanyNotFoundException: id - " + id;
        } else {
            return "CompanyNotFoundException: name - " + name;
        }
    }
}

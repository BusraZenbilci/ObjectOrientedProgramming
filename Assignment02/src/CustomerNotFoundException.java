public class CustomerNotFoundException extends RuntimeException {
    private int id;
    private String name, surname;

    public CustomerNotFoundException(int id) {
        this.id = id;
        this.name = null;
        this.surname = null;
    }

    public CustomerNotFoundException(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = 2;
    }

    @Override
    public String toString() {
        if (name.equals(null) && surname.equals(null)) {
            return "CustomerNotFoundException: id -" + id;
        } else
            return "CustomerNotFoundException: name -" + name + " " + surname;
    }
}



public class Department {

    private String ID;
    private String name;
    private Teacher Chair;

    public Department(String ID , String name ) {
        if (ID.length() == 3 || ID.length() == 4)
            this.ID = ID;
        else {
            throw new InvalidValueException(ID, 1);
        }
            this.name = name;

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        if (ID.length() == 3 || ID.length() == 4)
            this.ID = ID;
        else {
            throw new InvalidValueException(ID, 1);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getChair() {
        return Chair;
    }

    public void setChair(Teacher chair) {
        if (getChair() != null) {
            if (getChair().getDepartment() == chair.getDepartment()) {
                this.Chair = chair;
            } else
                throw new DepartmentMismatchException(chair.getDepartment(),
                        chair);
        }
        else
            this.Chair=chair;    // atama yapıldı
    }


}

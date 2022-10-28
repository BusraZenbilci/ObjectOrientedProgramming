


public abstract class Person {

    private String Name;
    private String Email;
    private long ID;
    private Department Department;


    public Person(String name, String Email , long ID , Department Department ) {
        this.Name = name;
        this.Email = Email;
        this.Department=Department;
        if (ID > 0) {
            this.ID = ID;
        } else {
            throw new IllegalArgumentException(ID + " is not valid. It must be"
                    + "positive.");
        }

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        if (ID > 0) {
            this.ID = ID;
        } else {
            throw new InvalidValueException(ID, 5);
        }
    }


    public Department getDepartment() {
        return Department;
    }

    public void setDepartment(Department department) {
        Department = department;
    }

    @Override
        public String toString () {
            return   Name  + "(" + ID+ ")"+ " - " + Email  ;
        }

    }
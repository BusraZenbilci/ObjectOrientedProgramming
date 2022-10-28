public class DepartmentMismatchException extends  RuntimeException {

    private Department department;
    private Teacher person;
    private Course course;

    public DepartmentMismatchException(Course course, Teacher person) {

        this.course = course;
        this.person = person;
        this.department = null;
    }

    public DepartmentMismatchException(Department department, Teacher person) {

        this.department = department;
        this.person = person;
        this.course = null;
    }

    @Override
    public String toString() {
        if (this.course == null) {
            return "DepartmentMismatchException: " + person.getName() + "(" +
                    person.getID() + ") cannot be chair of " +
                    department.getID() + " because he/she is currently " +
                    "assigned to " + person.getDepartment().getID();
        }
        else {
            return "DepartmentMismatchException: " + person.getName() + "(" +
                    person.getID() + ") cannot teach " + course.courseCode() +
                    " because he/she is currently assigned to " +
                    person.getDepartment().getID();
        }
    }
}

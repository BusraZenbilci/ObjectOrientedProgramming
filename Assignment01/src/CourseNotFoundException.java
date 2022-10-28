public class CourseNotFoundException extends RuntimeException {
    private Student student;
    private Course course;

    @Override
    public String toString() {
        return "CourseNotFoundException: " + student.getID() + " has not yet " +
                "taken " + course.getNumber();
    }
}

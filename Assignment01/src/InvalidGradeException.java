public class InvalidGradeException extends RuntimeException {

    private double grade;

    public InvalidGradeException(double grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "InvalidGradeException: " + grade;

    }

}

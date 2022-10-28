


public class GradStudent extends Student {

    private String Thesis;

    public GradStudent(String name, String Email, int ID, Department Department, String thesis) {
        super(name, Email, ID, Department);
        Thesis = thesis;
    }

    public String getThesis() {
        return Thesis;
    }

    public void setThesis(String thesis) {
        Thesis = thesis;
    }

    @Override
    public double courseGpaPoints(Course course) {

        double grade = grades.get(courses.indexOf(course));

        if (!courses.contains(course)) {
            throw new RuntimeException();
        }

        if (grade >= 90) {
            return 4.0;

        } else if (grade >= 85) {

            return 3.5;

        } else if (grade >= 80) {

            return 3.0;

        } else if (grade >= 75) {

            return 2.5;

        } else if(grade >= 70)

            return 2.0;
        else
            return 1.5;

    }

    @Override
    public String courseGradeLetter(Course course) {
        double grade = grades.get(courses.indexOf(course));

        if (!courses.contains(course)) {
            throw new RuntimeException();
        }
        if (grade >= 90) {
            return "AA";

        } else if (grade >= 85) {
            return "BA";

        } else if (grade >= 80) {
            return "BB";

        } else if (grade >= 75) {
            return "CB";

        } else if(grade >=70){
            return "CC";
        }
        else
            return "DC";
    }

    @Override
    public String courseResult(Course course) {

        double grade = grades.get(courses.indexOf(course));

        if (!courses.contains(course)) {
            throw new RuntimeException();
        }

        return  (grade >= 70 && grade <= 100) ? "Passed" :  "Failed ";


    }

}




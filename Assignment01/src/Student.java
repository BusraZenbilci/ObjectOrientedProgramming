import java.util.ArrayList;


public class Student extends Person {



    public Student(String name, String Email, long number, Department Department) {
        super(name, Email, number, Department);


    }

    ArrayList <Course> courses = new ArrayList<>();

    ArrayList <Double> grades = new ArrayList<>();

    public int getAKTS(){
        int passedCourse=0;
        for(int i=0 ;i<courses.size() ; i++){
            if (courseResult(courses.get(i)).equals("Passed")) {
                passedCourse += courses.get(i).getAKTS();
            }
        }  return passedCourse;
    }

    public int getAttemptedAKTS(){
        int akts=0;
        for(int i=0 ; i<courses.size() ; i++)
            akts += courses.get(i).getAKTS();
            return  akts;
    }

    public void addCourse (Course course , double grade){
        if(grade<0 || 100<grade)
            throw new InvalidGradeException(grade);
        else {
            if (courses.contains(course)) {
                grades.set(courses.indexOf(course),grade);
            }
            else {
                courses.add(course);
                grades.add(grade);
            }
        }

    }


    public String courseGradeLetter(Course course) {

        double grade=grades.get(courses.indexOf(course));

        if (!courses.contains(course)) {
            throw new CourseNotFoundException();
        }

        if (grade >= 88) {
            return "AA";

        } else if (grade >= 81) {
            return "BA";

        } else if (grade >= 74) {
            return "BB";

        } else if (grade >= 66) {
            return "CB";

        } else if (grade >= 60) {
            return "CC";

        } else if (grade >= 53) {
            return "DC";

        } else if (grade >= 46) {
            return "DD";

        } else if (grade >= 35) {
            return "FD";

        } else {
            return "FF";

        }
    }


    public double courseGpaPoints(Course course) {

        double grade= grades.get(courses.indexOf(course));
        if (!courses.contains(course)) {
            throw new CourseNotFoundException();
        }


        if (grade >= 88) {
            return 4.0;

        } else if (grade >= 81) {

            return 3.5;

        } else if (grade >= 74) {

            return 3.0;

        } else if (grade >= 66) {

            return 2.5;

        } else if (grade >= 60) {

            return 2.0;

        } else if (grade >= 53) {

            return 1.5;

        } else if (grade >= 46) {

            return 1.0;

        } else if (grade >= 35) {

            return 0.5;

        } else {

            return 0.0;
        }

    }


    public String courseResult(Course course) {

        double grade=grades.get(courses.indexOf(course));

        if(!courses.contains(course)){
            throw new CourseNotFoundException();
        }

        if (grade >= 60 && grade <= 100) {
            return "passed";

        } else if (grade >= 46 && grade < 60) {
            return "conditionally passed";

        } else {
            return "failed";

        }
    }

    public double getGPA(){

        double kredi=0;
        double toplam=0;

        for(int i=0 ; i<courses.size() ; i++){
            kredi+= courses.get(i).getAKTS()*courseGpaPoints(courses.get(i));
        }
        double gpa=  (kredi/toplam);
        return gpa;
    }

        @Override
        public String toString () {
            return super.toString() + " GPA " + getGPA();
        }


}
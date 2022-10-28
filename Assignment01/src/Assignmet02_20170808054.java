import java.util.ArrayList;

/***
 ** @author Büşra Zenbilci
 * @date 16.03.2021
 **/


public class Assignmet02_20170808054 {

    public static void main(String args []) {
        Department cse = new Department("CSE", "Computer Engineering");
        Teacher t = new Teacher("Joseph LEDET",
                "josephledet@akdeniz.edu.tr", 123L, cse, 1);
        Course c101 = new Course(cse, 101, "Programming 1",
                6, t);
        Course c102 = new Course(cse, 102, "Programming 2",
                4, t);
        Student s = new Student("Test STUDENT", "me@somewhere.com",
                123L, cse);
        s.addCourse(c101, 80);
        s.addCourse(c102, 30);
        System.out.println(s.getAKTS());
        System.out.println(s.getAttemptedAKTS());
        System.out.println(s.getGPA());
        System.out.println(s);
        s = new GradStudent("Test GRADSTUDENT", "me@somewhere.com",
                456, cse, "MDE");
        s.addCourse(c101, 80);
        s.addCourse(c102, 70);
        System.out.println(s.getAKTS());
        System.out.println(s.getAttemptedAKTS());
        System.out.println(s.getGPA());
        System.out.println(s);
    }

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



    public class Course {

        private Department Department;
        private int Number;
        private String Title;
        private int AKTS;
        private Teacher Teacher;

        public Course(Department Department, int Number, String Title, int AKTS, Teacher Teacher) {


            this.Department = Department;

            if ((100 < Number && Number < 499) || (5000 < Number && Number < 5999)
                    || (7000 < Number && Number < 7999))
                this.Number = Number;
            else {
                throw new InvalidValueException(Number, 3);
            }
            this.Title = Title;
            if (AKTS >= 0)
                this.AKTS = AKTS;
            else {
                throw new InvalidValueException(Number, 2);

            }

            if(getTeacher().getDepartment() != this.getDepartment()){
                throw new DepartmentMismatchException(this , Teacher);

            }

            this.Teacher = Teacher;
        }

        public Department getDepartment() {
            return Department;
        }

        public void setDepartment(Department department) {
            Department = department;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int number) {
            if ((100 < Number && Number < 499) || (5000 < Number && Number < 5999)
                    || (7000 < Number && Number < 7999))
                this.Number = number;
            else {
                throw new InvalidValueException(number, 3);
            }
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            this.Title = title;
        }

        public int getAKTS() {
            return AKTS;
        }


        public void setAKTS(int AKTS) {
            if (AKTS > 0)
                this.AKTS = AKTS;
            else {
                throw new InvalidValueException(AKTS, 2);
            }
        }

        public Teacher getTeacher() {
            return Teacher;
        }

        public void setTeacher(Teacher teacher) {
            if(getTeacher().getDepartment() == this.getDepartment()){
                this.Teacher=teacher;
            }
            else
                throw new DepartmentMismatchException(Department, teacher);
        }


        public String courseCode() {

            return  getDepartment().getID() + " " + getNumber();

        }


        @Override
        public String toString() {
            return
                    Department.getID() + '\'' + Number +
                            " - " + Title + '\'' +
                            "(" + AKTS + ")" ;
        }
    }




    public abstract class Person {

        private String Name;
        private String Email;
        private long ID;
        private Department Department;


        public Person(String name, String Email , long ID , Department Department ) {
            this.Name = name;
            this.Email = Email;
            this.Department=Department;
            if(ID > 0) {
                this.ID = ID;
            }
            else {
                throw new InvalidValueException(ID, 5);
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


    public class Teacher extends Person {

        private int Rank;


        public Teacher(String name, String Email, long number, Department Department, int rank) {
            super(name, Email, number, Department);
            if(1<=rank && rank<=4 )
                this.Rank=rank;
            else{
                throw new IllegalArgumentException(rank + " is not valid. It must be between 1 and 3.");
            }
        }

        public int getRank() {

            return Rank;
        }

        public void setRank(int rank) {

            if(1<=rank && rank<=4 )
                this.Rank=rank;
            else{
                throw new IllegalArgumentException(Rank + "is not valid. It must be between 1 and 3.");
            }
        }

        @Override
        public void setDepartment(Department department) {
            if(getDepartment().getChair().getName().equals(this.getName())){
                super.setDepartment(null);

            }
            super.setDepartment(department);
        }

        public String getTitle(int rank) {

            if(getRank()==1)
                return "Lecturer";
            else if(getRank()==2)
                return "Assistant Professor";
            else if(getRank()==3)
                return "Associate Professor";
            else if(getRank()==4)
                return "Professor";
            else{
                throw new RuntimeException();
            }

        }

        public void promote(int rank){

            if(1<=rank && rank <= 3) {
                rank++;
            }
            else {
                throw new  InvalidRankException();
            }

        }

        public void demote(int rank){
            if( 2<=rank  && rank <= 4) {
                rank--;
            }
            else {
                throw new InvalidRankException();
            }
        }

        @Override
        public String toString() {
            return
                    getTitle(Rank) + " "+ super.toString();
        }
    }




    public class Student extends Person {



        public Student(String name, String Email, long number, Department Department) {
            super(name, Email, number, Department);


        }

        ArrayList<Course> courses = new ArrayList<>();

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
                return "CB";
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

    public class CourseNotFoundException extends RuntimeException {
        private Student student;
        private Course course;

        @Override
        public String toString() {
            return "CourseNotFoundException: " + student.getID() + " has not yet " +
                    "taken " + course.getNumber();
        }
    }

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



    public class InvalidRankException extends RuntimeException {

        private int rank;

        @Override
        public String toString() {
            return "InvalidRankException: " + rank;
        }
    }


    class InvalidValueException extends RuntimeException {

        private String ID;
        private int AKTS;
        private int number;
        private String email;
        private int exceptionID;
        private long ID_2;

        public InvalidValueException(String temp, int exceptionID) {
            if (exceptionID == 1) {
                this.ID = temp;
            }

            if (exceptionID == 4) {
                this.email = temp;
            }
        }

        public InvalidValueException(int temp, int exceptionID) {
            if (exceptionID == 2) {
                this.AKTS = temp;
            }

            if (exceptionID == 3) {
                this.number = temp;
            }
        }

        public InvalidValueException(long ID_2, int exceptionID) {
            if (exceptionID == 5) {
                this.ID_2 = ID_2;
            }
        }

        @Override
        public String toString() {
            if (exceptionID == 1) {
                return "InvalidValueException: Department.ID - attempted to assign "
                        + "\"" + ID + "\" to a String that must be 3 or 4 " +
                        "character";
            }
            else if (exceptionID == 2) {
                return "InvalidValueException: Course.AKTS - attempted to assign "
                        + "\"" + AKTS + "\" to a int that must be positive.";
            }
            else if (exceptionID == 3) {
                return "InvalidValueException: Course.number - attempted to assign "
                        + "\"" + number + "\" to a int that must be in the range " +
                        "100-499 or 5000-5999 or 7000-7999";

            }
            else if (exceptionID == 4) {
                return "InvalidValueException: Person.email - attempted to assign " +
                        "\"" + email + "\" to a String that have format " +
                        "{text}@{text}.{text}";
            }
            else if (exceptionID == 5) {
                return "InvalidValueException: Person.ID - attempted to assign " +
                        "\"" + ID_2 + "\" to a long that must be positive ";
            }
            else {
                return "IllegalInvalidValueException: Undefined exception";
            }
        }
    }




}

/***
 ** @author Büşra Zenbilci 20170808054
 * @dince 29.03.2021
 **/

import java.util.Scanner;

public class Assignment01_20170808054 {



    public class Course {

        private String Department;
        private int Number;
        private String Title;
        private int AKTS;

        public Course(String Department , int Number , String Title , int AKTS) {

            if (Department.length() == 3 || Department.length() == 4)
                this.Department = Department;
            else {
                throw new IllegalArgumentException(Department +
                        " is not true valid.");
            }
            if ((100 < Number && Number < 499) || (5000 < Number && Number < 5999)
                    || (7000 < Number && Number < 7999))
                this.Number = Number;
            else {
                throw new IllegalArgumentException(Number +
                        "It is not true valid.");
            }
            this.Title = Title;
            if (AKTS >= 0)
                this.AKTS = AKTS;
            else {
                throw new IllegalArgumentException(AKTS
                        + " is not valid. It must be non-negative.");

            }
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String department) {
            if(Department.length()==3 || Department.length()==4)
                this.Department=department;
            else{
                throw new IllegalArgumentException(Department+
                        " is not true valid.");
            }
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int number) {
            if((100<Number && Number<499) || (5000<Number && Number<5999)
                    ||  (7000<Number && Number<7999))
                this.Number=number;
            else{
                throw new IllegalArgumentException(Number +
                        "It is not true valid.");
            }
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public int getAKTS() {
            return AKTS;
        }

        public void setAKTS(int AKTS) {
            if(AKTS>0)
                this.AKTS=AKTS;
            else {
                throw new IllegalArgumentException(AKTS
                        +" is not valid. It must be non-negative.");
            }
        }

        public String courseCode(){
            Scanner tara=new Scanner(System.in);

            String a=String.valueOf(getDepartment());
            String b=Integer.toString(getNumber());

            String s=( a+ " " + b );

            return s;

        }


        @Override
        public String toString() {
            return
                    Department + '\'' + Number +
                            " - " + Title + '\'' +
                            "(" + AKTS + ")" ;
        }
    }



    public class Person {

        private String Name;
        private String Email;
        private long number;
        private String Department;


        public Person(String name, String Email , long number , String Department ) {
            this.Name = name;
            this.Email = Email;
            if (number > 0) {
                this.number = number;
            } else {
                throw new IllegalArgumentException(number + " is not valid. It must be"
                        + "positive.");
            }
            if (Department.length() == 3 || Department.length() == 4)
                this.Department = Department;
            else {
                throw new IllegalArgumentException(Department +
                        " is not true valid.");
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

        public long getNumber() {
            return number;
        }

        public void setNumber(long number) {
            if (number > 0) {
                this.number = number;
            } else {
                throw new IllegalArgumentException(number + " is not valid. It must be"
                        + "positive.");
            }
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String department) {
            if (Department.length() == 3 || Department.length() == 4)
                this.Department = department;
            else {
                throw new IllegalArgumentException(Department +
                        " is not true valid.");
            }
        }

        @Override
        public String toString () {
            return   Name  + "(" + number+ ")"+ " - " + Email  ;
        }

    }



    public class Teacher extends Person {

        private int Rank;


        public Teacher(String name, String Email, long number, String Department, int rank) {
            super(name, Email, number, Department);
            if(1<=rank && rank<=3 )
                this.Rank=rank;
            else{
                throw new IllegalArgumentException(rank + " is not valid. It must be between 1 and 3.");
            }
        }

        public int getRank() {

            return Rank;
        }

        public void setRank(int rank) {

            if(1<=rank && rank<=3 )
                this.Rank=rank;
            else{
                throw new IllegalArgumentException(Rank + "is not valid. It must be between 1 and 3.");
            }
        }

        public String getTitle(int rank) {

            if(getRank()==1)
                return "Assistant Professor";
            else if(getRank()==2)
                return "Associate Professor";
            else if(getRank()==3)
                return "Professor";
            else{
                throw new IllegalArgumentException(this.Rank + " is not valid. " +
                        "It must be between 1 and 3.");
            }

        }

        public void promote(int rank){

            if(1<=rank && rank <= 2) {
                rank++;
            }
            else {
                throw new IllegalArgumentException(rank + " is not valid." +
                        " It must be between 1 and 3.");
            }

        }

        public void demote(int rank){
            if( 2<=rank  && rank <= 3) {
                rank--;
            }
            else {
                throw new IllegalArgumentException(rank + " is not valid." +
                        " It must be between 1 and 3.");
            }
        }

        @Override
        public String toString() {
            return
                    getTitle(Rank) + " "+ super.toString();
        }
    }



    public class Student extends Person {

        private int AKTS;

        public Student(String name, String Email, long number, String Department, int AKTS) {
            super(name, Email, number, Department);
            if(AKTS>=0)
                this.AKTS=AKTS;
            else {
                throw new IllegalArgumentException(AKTS
                        +" is not valid. It must be non-negative.");
            }

        }

        public int getAKTS() {
            return AKTS;
        }

        public void setAKTS(int AKTS) {
            if(AKTS>0)
                this.AKTS=AKTS;
            else {
                throw new IllegalArgumentException(AKTS
                        +" is not valid. It must be non-negative.");
            }
        }

        public void passCourse(Course course){
            this.AKTS +=getAKTS() ;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }




    public class GradStudent extends Student {

        private String Thesis;

        public GradStudent(String name, String Email, int ID, String Department, int AKTS, String thesis) {
            super(name, Email, ID, Department, AKTS);
            Thesis = thesis;
        }

        public String getThesis() {
            return Thesis;
        }

        public void setThesis(String thesis) {
            Thesis = thesis;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }







}

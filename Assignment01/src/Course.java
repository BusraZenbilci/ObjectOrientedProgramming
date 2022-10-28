/***
 ** @author Büşra Zenbilci
 * @date 29.03.2021
 **/

import java.util.Scanner;

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
            throw new InvalidValueException(number, 3);
        }
        this.Title = Title;
        if (AKTS >= 0)
            this.AKTS = AKTS;
        else {
            throw new InvalidValueException(number, 2);

        }

        if(getTeacher().getDepartment() != this.getDepartment()){
            throw new DepartmentMismatchException(this , teacher);

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

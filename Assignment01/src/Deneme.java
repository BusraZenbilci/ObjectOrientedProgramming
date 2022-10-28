

public class Deneme {

    public static void main(String[] args) {


        Course c=new Course("CSE" , 101 , "Programming 1" , 6);
        Course c2=new Course("CSE" , 102 , "Programming 2" , 4);
        Student s=new Student("Can DO" , "cando@akdeniz.edu.tr",
                123L, "CSE" , 0);

        s.passCourse(c);
        s.passCourse(c2);
        c.setAKTS(c2.getAKTS());
        System.out.println(s.getAKTS()-c.getAKTS() - c2.getAKTS());





    }
}

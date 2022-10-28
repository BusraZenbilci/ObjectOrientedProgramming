public class Compile {

    public static void main(String[] args) {
        Course cse102=new Course("CSE" , 102 , "Programming" , 6);
        Person per=new Person("Busra", "busrazenbilci98@gmail.com", 201708080,"CSE");
        Teacher teach=new Teacher("Ali", "ali@gmail.com", 201515484,"CSE",3);
        Student student1=new Student("sevgi", "sev@gmail.com",125364,"CSE", 6);
        GradStudent grad_student=new GradStudent("Can","can@gmail.com", 12546,"CSE", 5,"Security");
        System.out.println(cse102.toString());
        System.out.println(per.toString());
        System.out.println(teach.toString());
        System.out.println(student1.toString());
        System.out.println(grad_student.toString());



    }
}

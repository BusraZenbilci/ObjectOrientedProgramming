/***
 ** @author Büşra Zenbilci
 * @date 29.03.2021
 **/


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

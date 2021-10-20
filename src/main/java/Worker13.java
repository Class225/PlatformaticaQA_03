public class Worker13 extends EmployeeHW13 {
    public Worker13(int baseSalary, String name){
        super(baseSalary,name);
    }

    @Override
    public int getSalary(){
        if(numberOfSubordinates == 0) {
            return super.getSalary();
        } else {
            return getBaseSalary() * (getNumberOfSubordinates()/100 * 9);
        }
    }
}

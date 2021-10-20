public class Director13 extends EmployeeHW13 {
    /*Необходимо создать класс Director с теми же методами, что и Manager,
    но метод getSalary должен возвращать результат по формуле - <базовая ставка> * (<количество подчиненных> / 100 * 9).
    Если количество подчиненных 0, то результат как у обычного рабочего.
    */

    public Director13(int baseSalary, String name) {
        super(baseSalary, name);
    }
    public Director13(int baseSalary, String name, int numberOfSubordinates){
        super(baseSalary,name, numberOfSubordinates);
    }

    public void setNumberOfSubordinates(int numberOfSubordinates) {
        this.numberOfSubordinates = numberOfSubordinates;

    }
    public int getNumberOfSubordinates() {
        return numberOfSubordinates;
    }

    @Override
    public int getSalary() {
        if (numberOfSubordinates == 0) {
            return baseSalary;
        } else {
            return baseSalary * numberOfSubordinates / 100 * 9;
        }
    }
}

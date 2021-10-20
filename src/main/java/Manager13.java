import java.util.ArrayList;

public class Manager13 extends EmployeeHW13 {
    /* Необходимо создать класс Manager в который нужно добавить следующие методы:
     getNumberOfSubordinates - получить количество подчиненных
             setNumberOfSubordinates
     в классе, метод getSalary будет возвращать значение по формуле
      - <базовая ставка> * (<количество подчиненных> / 100 * 3).
     Если количество подчиненных 0, то результат как у обычного рабочего.
     */

    public Manager13(int baseSalary, String name) {
        super(baseSalary, name);
    }
    public Manager13(int baseSalary, String name, int numberOfSubordinates){
        super(baseSalary, name, numberOfSubordinates);

    }

    public void setNumberOfSubordinates() {

    }

    public int getNumberOfSubordinates() {
        return numberOfSubordinates;
    }

    @Override
    public int getSalary() {
        if (numberOfSubordinates == 0) {
            return baseSalary;
        } else {
            return baseSalary * numberOfSubordinates / 100 * 3;
        }
    }
}
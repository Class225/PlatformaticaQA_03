import java.util.ArrayList;

public class HelperHW13{
    private String employeeName;
    /*
    Задача №3
    Необходимо создать утилитарный класс со следующими методами:
    поиск сотрудника в массиве по его имени
    поиск сотрудника в массиве по вхождению указанной строки в его имени
    подсчет зарплатного бюджета для всех сотрудников в массиве
    поиск наименьшей зарплаты в массиве
    поиск наибольшей зарплаты в массиве
    поиск наименьшего количества подчиненных в массиве менеджеров
    поиск наибольшего количества подчиненных в массиве менеджеров
    поиск наибольшей надбавки (разнице между базовой зарплатой и выплатой) в массиве менеджеров
    поиск наименьшей надбавки (разнице между базовой ставки и зарплатой) в массиве менеджеров
     */
    //поиск сотрудника в массиве по его имени
    public static EmployeeHW13 getEmployeeName(String name, EmployeeHW13[]array) {
        for (int i = 0; i < array.length; i++){
            if (name.equals(array[i].getName())){
                return array[i];
            }
        }
        return null;
    }
    

    //поиск сотрудника в массиве по вхождению указанной строки в его имени
    public static EmployeeHW13 geEmployeeName(String subName, EmployeeHW13[]array) {
        for (int i = 0; i < array.length; i++){
            if (array[i].getName().contains(subName)){
                return array[i];
            }
        }
        return null;
    }


    //подсчет зарплатного бюджета для всех сотрудников в массиве
    public static int getSalarySum(EmployeeHW13[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            sum+= array[i].getSalary();
        }
        return sum;
    }
    // FROM SergeiD(51st;13.(10/07) OOP, polymorphism) lesson
    public int getSum(Manager13[] managerArray){
        int sum = 0;
        for(int i = 0; i < managerArray.length; i++){
            sum+= managerArray[i].getSalary();
        }
        return sum;
    }

    //поиск наименьшей зарплаты в массиве
    public static int getMinSalary(EmployeeHW13[] array) {
        int min = 0;
        for (int i = 0; i < array.length; i++) {
            if (min > array[i].getSalary())
            {
                min = array[i].getSalary();
            }
        }
        return min;
    }

    //поиск наибольшей зарплаты в массиве
    public static int getMaxSalary(ArrayList<Integer> salaryArr) {
        int max = 0;
        for (int i = 0; i < salaryArr.size(); i++) {
            if (max < salaryArr.get(i)) {
                salaryArr.set(i, max);
            }
        }
        return max;
    }
    //поиск наименьшего количества подчиненных в массиве менеджеров
    public static Manager13 getMinManagers(Manager13[]array){
        if(array == null || array.length == 0){
            return null;
        }
        Manager13 result = array[0];
        for(int i = 0; i < array.length; i++ ){
            if(result.getNumberOfSubordinates() > array[i].getNumberOfSubordinates()){
                result = array[i];
            }
        }
        return result;
    }
    //поиск наибольшего количества подчиненных в массиве менеджеров
    public static int getMaxManagers(ArrayList<Integer> numberOfSubordinates){
        int  maxNumberOfSubordinates = 0;
        for (int i  = 0; i <= numberOfSubordinates.size(); i++){
            if(maxNumberOfSubordinates < numberOfSubordinates.get(i)){
                maxNumberOfSubordinates = numberOfSubordinates.get(i);
            }
        }
        return maxNumberOfSubordinates;
    }
    //поиск наибольшей надбавки (разнице между базовой зарплатой и выплатой) в массиве менеджеров
    public static int extraMaxSalary(ArrayList<Integer> managerSalary){
        int maxSalary = 0;
        for(int i = 0; i <= managerSalary.size(); i++){
            if( maxSalary < managerSalary.get(i)){
                maxSalary = managerSalary.get(i);
            }
        }
       return maxSalary;
    }
    //поиск наименьшей надбавки (разнице между базовой ставки и зарплатой) в массиве менеджеров
    public static int extraMinSalary(ArrayList<Integer> managerSalary){
        int minSalary = 0;
        for(int i = 0; i <= managerSalary.size(); i++){
            if( minSalary > managerSalary.get(i)){
                minSalary = managerSalary.get(i);
            }
        }
        return minSalary;
    }
}


public class MainSe {
    String name;
    int age;
    int salary;

    MainSe(String name, int age){
        this.name = name;
        this.age = age;
    }

    public static int getSum(int a, int b){
        return a + b;
    }
    public void printPerson(){
        System.out.println("name: " + name + ", age is " + age);

    }


}

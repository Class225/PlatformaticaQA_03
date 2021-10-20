public class Workers {

    char gender;
    String position;
    int bonuses;

    MainSe person;

    Workers(String name, int age, char gender){
        person = new MainSe(name,age);
        this.gender = gender;
    }
    Workers (MainSe person, String position){
        this.person = person;
        this.position = position;
    }

    boolean isAdult(){
        if(person.age >=18){
            return true;
        }else {
            return false;
        }
    }
    void printWorker(){
        person.printPerson();
    }

    }


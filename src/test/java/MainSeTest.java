import org.testng.Assert;
import org.testng.annotations.Test;

public class MainSeTest {
    MainSe person = new MainSe("NameIsUnknown",90);

    @Test
    public void testGetSum(){

            int result = MainSe.getSum(10,10);
            Assert.assertEquals(result,20);

            result = MainSe.getSum(2,20);
            Assert.assertEquals(result,22);


         Workers worker1 = new Workers("Tom", 57, 'M');
         worker1.person.age = 39;
         worker1.person.name = "Jorge";
        System.out.println(worker1.isAdult());
        worker1.printWorker();
        boolean checkAdult = worker1.isAdult();
        }



    }



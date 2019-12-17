import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CollectionTest {

    //ArrayList
    /*
    Contains Duplicates
    Maintain Inseration order

     */
    @Test
    public static void arratListTest(){
        List<String> topcompanies= new ArrayList<String>();
        System.out.println(topcompanies.isEmpty());
        topcompanies.add("Google");
        System.out.println(topcompanies.isEmpty());
        topcompanies.add("Tcs");
        topcompanies.add("epam");
        topcompanies.add("cts");
        topcompanies.add("Apple");
        for(int i=0;i<topcompanies.size();i++){

            System.out.println(topcompanies.get(i));
        }
        Iterator<String> companiesIterator=topcompanies.iterator();
        while(companiesIterator.hasNext()){

            System.out.println(companiesIterator.next());
        }
    }

    @Test
    public static void linkedList(){
        List<String> humanSpecies= new LinkedList<String>();
        humanSpecies.add("Homo Sapiens");
        humanSpecies.add("Homo  Erectus");
        humanSpecies.add("Homo nen");
        humanSpecies.add("Homo Habilis");

        Iterator<String> humaniterator=humanSpecies.iterator();
        while(humaniterator.hasNext()){

            System.out.println(humaniterator.next());
        }




    }
}

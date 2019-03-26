import org.junit.Test;

import static org.junit.Assert.*;

public class BackupTest {

    @Test
    public void testSerializeShoppingCartNull(){
        ShoppingCart sc = null;
        Backup b = new Backup();

        try{
            b.serializeShoppingCart(sc);
        }
        catch(Exception e){
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testSerializeShoppingCart(){
      Item i1 = new Item();
        Item i2 = new Item();

        i1.setName("apple");
        i1.setCost(1);
        i1.setDescription("yum");

        i2.setName("pear");
        i2.setCost(2);
        i2.setDescription("eww");


        ShoppingCart sc = new ShoppingCart();
        Backup b = new Backup();

        sc.addItem(i1);
        sc.addItem(i2);

        b.serializeShoppingCart(sc);

        StringBuffer itemLineTest = new StringBuffer();

        itemLineTest.append(i1.getName());
        itemLineTest.append(",");
        itemLineTest.append(i1.getCost());
        itemLineTest.append(",");
        itemLineTest.append(i1.getDescription());
        itemLineTest.append(",");
        itemLineTest.append(sc.findCartItem(i1).getCount());
        itemLineTest.append(System.getProperty("line.separator"));

        itemLineTest.append(i2.getName());
        itemLineTest.append(",");
        itemLineTest.append(i2.getCost());
        itemLineTest.append(",");
        itemLineTest.append(i2.getDescription());
        itemLineTest.append(",");
        itemLineTest.append(sc.findCartItem(i2).getCount());
        itemLineTest.append(System.getProperty("line.separator"));


    }

}

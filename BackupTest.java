import csc4700.Backup;
import csc4700.Item;
import csc4700.ShoppingCart;
import csc4700.exceptions.SerializedFormatException;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BackupTest {

    @Test
    public void testSerializeCartNull() {
        ShoppingCart sc = null;
        Backup b = new Backup();
        try {
            b.serializeShoppingCart(sc);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testSerializeCart() {
        Item i1 = new Item();
        i1.setName("apple");
        i1.setDescription("yum");
        i1.setCost(2);

        Item i2 = new Item();
        i2.setName("pear");
        i2.setDescription("ew");
        i2.setCost(3);

        ShoppingCart sc = new ShoppingCart();
        sc.addItem(i1);
        sc.addItem(i2);

        Backup b = new Backup();

        String expected = "apple,2,yum,1" + System.getProperty("line.separator")
                + "pear,3,ew,1" + System.getProperty("line.separator");
        assertEquals(expected, b.serializeShoppingCart(sc));
    }

    @Test
    public void testDeserializeCartNull() {
        String input = null;
        Backup b = new Backup();
        try {
            b.deserializeShoppingCart(input);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testDeserializeCartMoreParts() {
        String input = "apple,2,yum,1,more";
        Backup b = new Backup();
        try {
            b.deserializeShoppingCart(input);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof SerializedFormatException);
        }
    }

    @Test
    public void testDeserializeLessParts() {
        String input = "apple,2,yum";
        Backup b = new Backup();
        try {
            b.deserializeShoppingCart(input);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof SerializedFormatException);
        }
    }

    @Test
    public void testDeserializeCart() {
        Item i1 = new Item();
        i1.setName("apple");
        i1.setDescription("yum");
        i1.setCost(2);

        Item i2 = new Item();
        i2.setName("pear");
        i2.setDescription("ew");
        i2.setCost(3);

        ShoppingCart sc = new ShoppingCart();
        sc.addItem(i1);
        sc.addItem(i2);

        Backup b = new Backup();

        String input = "apple,2,yum,1" + System.getProperty("line.separator")
                + "pear,3,ew,1" + System.getProperty("line.separator");

        ShoppingCart expected = new ShoppingCart();
        try {
            expected = b.deserializeShoppingCart(input);
        } catch (Exception e) {
            fail();
        }

        assertArrayEquals(sc.getCartItems().toArray(), expected.getCartItems().toArray());
    }

    @Test
    public void testSaveCartDeleteExisting() throws IOException {
        File f = File.createTempFile("test","txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("test");
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(f));
        String init = br.readLine();

        Item i1 = new Item();
        i1.setName("apple");
        ShoppingCart sc = new ShoppingCart();
        sc.addItem(i1);
        Backup b = new Backup();
        b.saveShoppingCart(sc, f);

        br = new BufferedReader(new FileReader(f));
        String last = br.readLine();

        assertNotEquals(init, last);

        if (f.exists()) {
            f.delete();
        }
    }

    @Test
    public void testSaveCart() throws IOException {
        Item i1 = new Item();
        i1.setName("apple");
        Item i2 = new Item();
        i2.setName("pear");

        ShoppingCart sc = new ShoppingCart();
        sc.addItem(i1);
        sc.addItem(i2);

        Backup b = new Backup();
        File f = File.createTempFile("test", "txt");
        b.saveShoppingCart(sc, f);

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line;
        StringBuffer allLines = new StringBuffer();
        while ((line = br.readLine()) != null) {
            allLines.append(line);
            allLines.append(System.getProperty("line.separator"));
        }

        assertEquals(b.serializeShoppingCart(sc), allLines.toString());

        if (f.exists()) {
            f.delete();
        }
    }

    @Test
    public void testLoadCartNotExists() throws IOException {
        File f = File.createTempFile("test", "txt");
        f.delete();

        Backup b = new Backup();

        try {
            b.loadShoppingCart(f);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof FileNotFoundException);
        }
    }

    @Test
    public void testLoadCart() throws IOException, SerializedFormatException {
        File f = File.createTempFile("test", "txt");

        Item i1 = new Item();
        i1.setName("apple");
        Item i2 = new Item();
        i2.setName("pear");

        ShoppingCart sc1 = new ShoppingCart();
        sc1.addItem(i1);
        sc1.addItem(i2);

        Backup b = new Backup();
        b.saveShoppingCart(sc1, f);
        ShoppingCart sc2 = b.loadShoppingCart(f);

        assertArrayEquals(sc1.getCartItems().toArray(), sc2.getCartItems().toArray());

        if (f.exists()) {
            f.delete();
        }
    }
}

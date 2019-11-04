import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {

    HashMap<Integer, String> test = new HashMap<>();

    @Test
    public void put() {
        test.put(1, "test");
        String res = test.get(1);
        assertEquals("test", res);
    }

    @Test
    public void putNullKey() {
        test.put(null, "test");
        String res = test.get(null);
        assertEquals("test", res);
    }

    @Test
    public void putTwiceWithSameKeys() {
        test.put(1, "error");
        test.put(1, "test");
        String res = test.get(1);
        assertEquals("test", res);
    }

    @Test
    public void putIntoSameIndex() {
        test.put(null, "null");
        test.put(0, "zero");
        String res = test.get(0);
        assertEquals("zero", res);
    }

    @Test
    public void get() {
        test.put(1, "one");
        test.put(2,"two");
        assertEquals("one", test.get(1));
    }
}
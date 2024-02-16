import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class IteratorTest {

    private List<String> myList;
    private Iterator<String> myIterator;

    @Before public void initializeListAndIterator()
    {
        myList = new ArrayList<String>();
        myList.add("cat");
        myList.add("dog");
        myIterator = myList.iterator();
    }

    @Test public void testHasNext_BaseScenario()
    {
        assertTrue(myIterator.hasNext());
    }

    @Test public void testHasNext_Case1()
    {
        myIterator.next(); myIterator.next();
        assertFalse(myIterator.hasNext());
    }

    @Test(expected=ConcurrentModificationException.class)
    public void testHasNext_Case5()
    {
        myList.add("elephant");
        myIterator.hasNext();
    }

    @Test public void testNext_BaseScenario()
    {
        assertEquals("cat", myIterator.next());
    }

    @Test(expected=NoSuchElementException.class)
    public void testNext_Case1()
    {
        myIterator.next(); myIterator.next();
        myIterator.next();
    }

    @Test public void testNext_Case2()
    {
        myList = new ArrayList<String>();
        myList.add(null);
        myIterator = myList.iterator();
        assertNull(myIterator.next());
    }

    @Test(expected=ConcurrentModificationException.class)
    public void testNext_Case5()
    {
        myList.add("elephant");
        myIterator.next();
    }

    @Test public void testRemove_BaseScenario()
    {
        myIterator.next();
        myIterator.remove();
        assertFalse(myList.contains("cat"));
    }

    @Test public void testRemove_Case1()
    {
        myIterator.next(); myIterator.next();
        myIterator.remove();
        assertFalse(myList.contains("dog"));
    }

    @Test public void testRemove_Case2()
    {
        myList.add(null);
        myList.add("elephant");
        myIterator = myList.iterator();
        myIterator.next(); myIterator.next();
        myIterator.next();
        myIterator.remove();
        assertFalse(myList.contains(null));
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testRemove_Case3()
    {
        myList = Collections.unmodifiableList(myList);
        myIterator = myList.iterator();
        myIterator.next();
        myIterator.remove();
    }

    @Test (expected=IllegalStateException.class)
    public void testRemove_Case4()
    {
        myIterator.remove();
    }

    @Test (expected=ConcurrentModificationException.class)
    public void testRemove_Case5()
    {
        myIterator.next();
        myList.add("elephant");
        myIterator.remove();
    }

}

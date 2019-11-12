import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class RangeBinarySearchTest {
    static String[] testStrings=new String[]{"ABBA","ABBA","ABBA","Abborre","Abbore","Blue Öyster Cult","Knopfler","Knopfler","Sting","Sting","Sting"};
    static Comparator<String> comparator=new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };

    @Test
    void firstIndexOf() {
        assertEquals(0,RangeBinarySearch.firstIndexOf(testStrings,"ABBA",comparator));
        assertEquals(3,RangeBinarySearch.firstIndexOf(testStrings,"Abbore",comparator));
        assertEquals(5,RangeBinarySearch.firstIndexOf(testStrings,"Blue Öyster Cult",comparator));
        assertEquals(6,RangeBinarySearch.firstIndexOf(testStrings,"Knopfler",comparator));
        assertEquals(8,RangeBinarySearch.firstIndexOf(testStrings,"Sting",comparator));
        assertEquals(-1,RangeBinarySearch.firstIndexOf(testStrings,"Clapton",comparator));
    }

    @Test
    void lastIndexOf() {
        assertEquals(2,RangeBinarySearch.lastIndexOf(testStrings,"ABBA",comparator));
        assertEquals(4,RangeBinarySearch.lastIndexOf(testStrings,"Abborre",comparator));
        assertEquals(5,RangeBinarySearch.lastIndexOf(testStrings,"Blue Öyster Cult",comparator));
        assertEquals(7,RangeBinarySearch.lastIndexOf(testStrings,"Knopfler",comparator));
        assertEquals(10,RangeBinarySearch.lastIndexOf(testStrings,"Sting",comparator));
        assertEquals(-1,RangeBinarySearch.firstIndexOf(testStrings,"Clapton",comparator));
    }
}
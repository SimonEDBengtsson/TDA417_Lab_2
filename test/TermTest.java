import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TermTest {
    static String[] testStrings=new String[] {"Neil Young","Rockin'","in the Free World","Rolling Stones","Angie","Rockin' in the Free World"};
    static Term[] testTerms;
    static {
        testTerms=new Term[testStrings.length];
        for (int i=0;i<testTerms.length;i++){
            testTerms[i]=new Term(testStrings[i],i);
        }
    }
    @Test
    void byLexicographicOrder() {
        Comparator<Term> comparator=Term.byLexicographicOrder();
        assertTrue(comparator.compare(testTerms[4],testTerms[3])<0);
        assertTrue(comparator.compare(testTerms[1],testTerms[0])>0);
        assertEquals(0, comparator.compare(testTerms[2], testTerms[2]));
        assertTrue(comparator.compare(testTerms[3],testTerms[0])>0);
        assertTrue(comparator.compare(testTerms[1],testTerms[5])<0);
    }

    @Test
    void byReverseWeightOrder() {
        Comparator<Term> comparator=Term.byReverseWeightOrder();
        for (int i=0;i<testTerms.length;i++){
            for (int j=i+1;j<testTerms.length;j++){
                assertTrue(comparator.compare(testTerms[i],testTerms[j])>0);
            }
        }
    }

    @Test
    void byPrefixOrder() {
        Comparator<Term> comparator=Term.byPrefixOrder(2);
        assertEquals(0, comparator.compare(testTerms[1], testTerms[3]));
        comparator=Term.byPrefixOrder(7);
        assertEquals(0,comparator.compare(testTerms[1],testTerms[5]));
        comparator=Term.byPrefixOrder(1000);
        assertTrue(comparator.compare(testTerms[3],testTerms[0])>0);
        assertTrue(comparator.compare(testTerms[4],testTerms[3])<0);
    }
}
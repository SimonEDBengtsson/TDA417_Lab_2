import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {
    private Term[] dictionary;

    // Initializes the data structure from the given array of terms.
    // Complexity: O(N log N), where N is the number of terms
    public Autocomplete(Term[] dictionary){
        this.dictionary=dictionary;
        Arrays.sort(dictionary,Term.byLexicographicOrder());
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    // Complexity: O(log N + M log M), where M is the number of matching terms
    public Term[] allMatches(String prefix){
        Term term=new Term(prefix,0);
        Comparator<Term> termComparator=Term.byPrefixOrder(prefix.length());
        int firstMatch=RangeBinarySearch.firstIndexOf(dictionary,term,termComparator);
        int lastMatch=RangeBinarySearch.lastIndexOf(dictionary,term,termComparator);
        if (firstMatch==-1 || firstMatch>lastMatch) {
            return null;
        }
        Term[] matches=new Term[lastMatch-firstMatch+1];
        for (int i=firstMatch;i<=lastMatch;i++){
            matches[i-firstMatch]=dictionary[i];
        }
        Arrays.sort(matches,Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with the given prefix.
    // Complexity: O(log N)
    public int numberOfMatches(String prefix){
        Term term=new Term(prefix,0);
        Comparator<Term> termComparator=Term.byPrefixOrder(prefix.length());
        int firstMatch=RangeBinarySearch.firstIndexOf(dictionary,term,termComparator);
        int lastMatch=RangeBinarySearch.lastIndexOf(dictionary,term,termComparator);
        if (firstMatch==-1){
            return 0;
        }
        return lastMatch-firstMatch+1;
    }
}
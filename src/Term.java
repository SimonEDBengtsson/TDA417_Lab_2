import java.util.Comparator;

public class Term {
    private String query;
    private long weight;
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight){
        if (query == null) {
            throw new NullPointerException();
        }
        if (weight<0) {
            throw new IllegalArgumentException();
        }
        this.query=query;
        this.weight=weight;
    }

    // Compares the two terms in lexicographic order by query.
    public static Comparator<Term> byLexicographicOrder(){
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                return compareIgnoreCase(o1.query,o2.query);
            }
        };
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                // larger weight==lower index Term
                return Long.compare(o2.weight,o1.weight);
            }
        };
    }

    // Compares the two terms in lexicographic order,
    // but using only the first k characters of each query.
    public static Comparator<Term> byPrefixOrder(int k){
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                if (k < 0) {
                    throw new IllegalArgumentException();
                }
                // Crop both strings so that they are no longer than k characters
                int substring1Length=Math.min(k,o1.query.length());
                int substring2Length=Math.min(k,o2.query.length());
                String substring1=o1.query.substring(0,substring1Length);// could be omitted in this use case.
                String substring2=o2.query.substring(0,substring2Length);
                // Compare the cropped strings
                return compareIgnoreCase(substring1,substring2);
            }
        };
    }

    /**
     * Helper method for comparing two Strings by lexicographic order, ignoring case.
     * @param s1 The String to be compared
     * @param s2 The String to compare it with
     * @return A negative number if s1 precedes s2, a positive number if it succeeds s2 and 0 if both are equal
     */
    private static int compareIgnoreCase(String s1, String s2) {
        return s1.toLowerCase().compareTo(s2.toLowerCase());
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by whitespace, followed by the query.
    public String toString() {
        return String.format("%12d    %s", this.weight, this.query);
    }
}
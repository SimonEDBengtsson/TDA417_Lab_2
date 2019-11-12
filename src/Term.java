import java.util.Comparator;

public class Term {
    private String query;
    private long weight;
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight){
        this.query=query;
        this.weight=weight;
    }

    // Compares the two terms in lexicographic order by query.
    public static Comparator<Term> byLexicographicOrder(){
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                return o1.query.compareTo(o2.query);
            }
        };
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
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
                int substring1Length=Math.min(k,o1.query.length());
                int substring2Length=Math.min(k,o2.query.length());
                String substring1=o1.query.substring(0,substring1Length);
                String substring2=o2.query.substring(0,substring2Length);
                return substring1.compareTo(substring2);
            }
        };
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by whitespace, followed by the query.
    public String toString() {
        return String.format("%12d    %s", this.weight, this.query);
    }
}
import java.util.Comparator;

public class RangeBinarySearch {
    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N), where N is the length of the array.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }
        int minBound=0;
        int maxBound=a.length-1;
        while (minBound<maxBound) {
            int guess=(maxBound+minBound)/2;
            int comparison=comparator.compare(key,a[guess]);
            if (comparison>0){
                minBound=guess+1;
            }
            else if (comparison<0){
                maxBound=guess-1;
            }
            else if (minBound<guess){
                maxBound=guess;
            }
            else {
                return guess;
            }
        }
        return comparator.compare(key,a[minBound])==0 ? minBound : -1;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N)
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }
        int minBound=0;
        int maxBound=a.length-1;
        while (minBound<maxBound) {
            int guess=(maxBound+minBound)/2;
            int comparison=comparator.compare(key,a[guess]);
            if (comparison>0){
                minBound=guess+1;
            }
            else if (comparison<0){
                maxBound=guess-1;
            }
            else if (maxBound>guess){
                minBound=guess+1;
            }
            else {
                return guess;
            }
        }
        return comparator.compare(key,a[maxBound])==0 ? maxBound : -1;
    }
}
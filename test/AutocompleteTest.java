import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AutocompleteTest {
    static final String dictionaryName="romaner.txt";
    static Term[] dictionary;
    static {
        BufferedReader in;
        try {
            in=new BufferedReader(
                    new FileReader(
                            new File(
                                    "/home/simon/Documents/Kurser/TDA417/Lab2/in/dictionaries/"+dictionaryName
                            )
                    )
            );
        }
        catch (FileNotFoundException x){
            dictionary=null;
            in=null;
        }
        ArrayList<Term> list=new ArrayList<>();
        String line;
        try {
            while ((line=in.readLine())!=null) {
                line=line.trim();
                String[] parameters=line.split("\t");
                list.add(new Term(parameters[1],Long.parseLong(parameters[0])));
            }
        }
        catch (IOException x){
            dictionary=null;
        }
        dictionary=list.toArray(new Term[0]);
    }
    Autocomplete autocompleter=new Autocomplete(dictionary);

    @Test
    void numberOfMatches() {
        assertEquals(15,autocompleter.numberOfMatches("pyr"));
        assertEquals(69,autocompleter.numberOfMatches("grö"));
        assertEquals(103,autocompleter.numberOfMatches("ke"));
        assertEquals(55,autocompleter.numberOfMatches("ör"));
        assertEquals(23,autocompleter.numberOfMatches("fer"));
        assertEquals(0,autocompleter.numberOfMatches("qva"));
        assertThrows(NullPointerException.class,() -> {
            autocompleter.numberOfMatches(null);
        });
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Term[] dictionary=loadDictionary("wiktionary.txt");
        Autocomplete autocomplete=new Autocomplete(dictionary);
        Scanner in=new Scanner(System.in);
        while (true) {
            System.out.print("Ord: ");
            String word=in.next();
            Term[] suggestions=autocomplete.allMatches(word);
            for (Term term:suggestions){
                System.out.println(term);
            }
        }
    }

    private static Term[] loadDictionary(String dictionaryName){
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
            return null;
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
            return null;
        }
        return list.toArray(new Term[0]);
    }
}

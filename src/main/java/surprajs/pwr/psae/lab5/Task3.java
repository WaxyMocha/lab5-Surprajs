/*
lab5 task3
michał piechowski 253137
 */
package surprajs.pwr.psae.lab5;
import java.util.HashMap;
import java.util.Map;
public class Task3 {
    public static void main(String[] args) {
//      String s = "Bibliothèque Nationale";
//      s = s.replaceAll("[^a-zA-ZÀ-žè ]", "").toLowerCase();
//      System.out.println(s);       
    long start = System.nanoTime();
    WordFrequency w1 = new WordFrequency();
    long end = System.nanoTime();
    long mili = (end - start)/1_000_000;
        System.out.println(mili);
      //HashMap<String, Integer> wordCount = new HashMap<>();
//      String s = "Pchnąć w tę łódź jeża lub ośm skrzyń fig.";
//      String[] words = s.replaceAll("[^a-zA-Z0-9ąęćłńóśźż ]", "").toLowerCase().strip().split(" ");
//      for (String word: words) {
//          wordCount.put(word, wordCount.getOrDefault(word,0)+1);
//      }
//        System.out.println(wordCount.get(""));
//        wordCount.put("a",1);
//                wordCount.put("b",2);
//
//        
//      wordCount.entrySet().forEach(word -> {System.out.println(String.format("%s: %d",word.getKey(),word.getValue()));});
//      //String test = "a    b";
//      //test = test.replaceAll("[\\s+]", " ");
//        //S/ystem.out.println(test);
//      String[] x = test.split("\\s+");
//        for (String y: x) {
//            System.out.println(y);
//        }
//        for (String word: x) {
//                        wordCount.put(word, wordCount.getOrDefault(word,0)+1);
//                        //[^a-zA-Z0-9ąęćłńóśźż\\s+]
//                    }
//                    wordCount.forEach((k,v) -> System.out.println(String.format("%s,%d",k,v)));
//                    System.out.println(wordCount.get(""));

//    String test = "PchnąĆ w tę łüódź jeża lub ośm skrzyń fig.";
//    test = test.replaceAll("[^a-zA-ZÀ-ž0-9\\s+]", " ");
//        System.out.println(test);
    }    
}

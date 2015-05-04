package manualwordcount;

import java.util.Hashtable;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

import com.google.common.collect.*;

public class manualwordcount {

    private static Hashtable<String, Integer> wordcount_hashtable = new Hashtable<String, Integer>();

    private static void incrementCount(String word) {
        Integer occurencies = wordcount_hashtable.get(word);
        if (occurencies == null) {
            wordcount_hashtable.put(word, 1);
        } else {
            wordcount_hashtable.put(word, occurencies + 1);
        }
    }

    private static void processBatches(List<String> input) {
        for (String subset : input) {
            incrementCount(subset);
        }
    }

    // divide input in batches
    private static List<List<String>> divideInBatches(List<String> input) {
        List<List<String>> batches = Lists.partition(input, 10);
        return batches;
    }

    public static void main(String[] args) {
        System.out.println("Please enter strings separated by \",\"");
        Scanner s = new Scanner(System.in);
        String linha = s.nextLine();
        List<String> input = Arrays.asList(linha.replaceAll("[^a-zA-Z0-9,']", "").split(","));
        s.close();
        List<List<String>> batches = divideInBatches(input);
        for (List<String> subset : batches) {
            processBatches(subset);
        }
        for (String key : wordcount_hashtable.keySet()) {
            System.out.println("Key:"+key + wordcount_hashtable.get(key));
        }

    }

}

package com.epam.mjc;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result
     * substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */

    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        StringBuilder regex = new StringBuilder();
        regex.append("[");
        delimiters.stream().forEach(delimiter -> regex.append(delimiter + "/"));
        regex.append("]");
        String[] str = source.split(regex.toString());
        for(String s : str){
            if(s.length() >= 1)
                result.add(s);
        }
        return result;
    }
}

package io.javasmithy.data;

import io.javasmithy.data.Name;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NameLoader {

    public static List<Name> load(char gender){
        System.out.println("in load)");
        List<Name> names = new ArrayList<Name>();
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(NameLoader.class.getClassLoader().getResourceAsStream("data/name-list.csv")));
            String line = "";
            while( (line = in.readLine()) != null ) {
                System.out.print("\r" + line);
                String[] entries = line.split(",");
                if(entries.length == 0) continue;
                Name name = new Name(entries[0], entries[1].charAt(0));
                if(gender == 'A') names.add(name); else {
                    if(name.getGender() == gender) names.add(name);
                }
            }
            //upperBound = names.size()-1;
        } catch (IOException e){
            e.printStackTrace();
        }
        return names;
    }
}

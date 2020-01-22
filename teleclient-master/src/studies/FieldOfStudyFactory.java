package studies;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FieldOfStudyFactory {

    public static ArrayList<FieldOfStudy> getFieldOfStudy(int count){
        String[] names = {"Elektronika i telekomunikacja", "Teleinformatyka", "Elektronika", "Informatyka", "Cyberbezpieczenstwo", "Electronics and Telecommunication"};
        String[] slugs = {"EiT", "Ti", "El", "It", "CS", "EaT"};
        int[] ids = {6,12,5,3,4,13};
        ArrayList fields = new ArrayList();
        for(int i = 0; i<count; i++){
            fields.add(new FieldOfStudy(names[i], slugs[i], ids[i]));
        }
        return fields;
    }
}

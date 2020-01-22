package studies;

import APIClient.Client;

import java.util.ArrayList;
import java.util.Random;

public class SubjectFactory {

    public static ArrayList<Subject> getSubjects(FieldOfStudy field, int count){
        String basename = "subject";
        ArrayList<Subject> subjectList = new ArrayList();
        Random r = new Random();
        int semester;
        for(int i=0; i < count;i++){
            StringBuilder s = new StringBuilder();
            s.append(basename);
            s.append(" ");
            s.append(field.getSlug());
            s.append(" ");
            s.append(i);
            subjectList.add(new Subject(s.toString(), r.nextInt(8)+1));
        }
//        subjectList = Client.getAPISubjects(field);
        return subjectList;
    }
}

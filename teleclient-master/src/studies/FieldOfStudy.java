package studies;
import APIClient.Client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class FieldOfStudy extends SavedModel<String, String, String> {
    private String name;
    private String slug;
    private int id;
    private ArrayList<Subject> subjects;

    public FieldOfStudy(String name, String slug, int id) {
        this.name = name;
        this.slug = slug;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    private void fetchSubjects() throws Exception {
        subjects = Client.getAPISubjects(this.id);
        // TODO: Docelowo zaciąganie przedmiotów z API
        //subjects = SubjectFactory.getSubjects(this, 10);
    }
    public ArrayList<Subject> getSubjects() throws Exception {
        if(subjects == null)
            fetchSubjects();
        System.out.println(ResourceFactory.getResources(subjects.get(0), 1));
        return subjects;
    }

    public void addSubject(Subject subject) throws Exception {
        getSubjects().add(subject);
        Client.saveSubject(subject, this);
    }
    public void removeSubject(Subject subject){
//        Client.delSubject TODO: zmienic zeby usuwalo przedmiot z api
        subjects.remove(subject);
    }

    public FieldOfStudy save(String name, String slug){
        this.name = name;
        this.slug = slug;
        System.out.println("zapisywanie kierunku " + this.toString());
        // TODO: metoda z Clienta do zapisania kierunku studiow
        return this;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(name);
        s.append(" (");
        s.append(slug);
        s.append(")");
        return s.toString();
    }


}

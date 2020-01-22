package studies;

import APIClient.Client;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Subject extends SavedModel<String, Integer, FieldOfStudy> {
    private String name;
    private int semester;
    private ArrayList<Resource> resources;
    private int id;
    public Subject(String name, int semester) {
        this.name = name;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }
    public int getSemester() {
        return semester;
    }
    public int getId() { return id; }
    public void setName(String name) { this.name=name; }
    public void setSemester(int semester) { this.semester=semester; }
    public void setId(int id) { this.id=id; }

    private void fetchResources() throws Exception {
        resources = Client.getAPIResources(this.id);
    }

    public ArrayList<Resource> getResources() throws Exception {
        if(resources == null)
            fetchResources();
        return resources;
    }
    public void addResource(Resource resource) throws Exception {
        getResources().add(resource);
//        Client.saveResource() TODO: metoda dodajaca resource do przedmiotu
    }
    public void removeResource(Resource resource) throws Exception {
        getResources().remove(resource);
//        Client.delResource(); TODO
    }

    public Subject save(String name, int semester, FieldOfStudy field) throws Exception {
        this.name = name;
        this.semester = semester;
        System.out.println("zapisywanie przedmiotu " + this.toString());
        Client client = new Client();
        client.saveSubject(this, field);
        return this;
    }
    @Override
    public String toString() {
        return this.name;
    }
}

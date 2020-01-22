package APIClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import studies.FieldOfStudy;
import studies.FieldOfStudyFactory;
import studies.Resource;
import studies.Subject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.http.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


public class Client {
    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    public static ArrayList<Subject> getAPISubjects(FieldOfStudy f) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://teleagh.herokuapp.com/api/fieldsofstudy/" + String.valueOf(f.getId()) + "/subjects/"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        Gson g = new Gson();
        Subject[] subjectList = g.fromJson(response.body(), Subject[].class);
        ArrayList<Subject> subjectArray = new ArrayList<>();
        for (int i = 0; i < subjectList.length; i++) {
            subjectArray.add(subjectList[i]);
        }
        return subjectArray;
    }

    public static void main(String[] args) throws Exception {
        FieldOfStudy f = new FieldOfStudy("cos", "cos", 3);
        getAPISubjects(f);
    }

    public static ArrayList<FieldOfStudy> fetchFieldOfStudy() {
        return FieldOfStudyFactory.getFieldOfStudy(4);
    }

    public static void saveSubject(Subject subject, FieldOfStudy field) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        String json = new StringBuilder()
                .append("{")
                .append("\"semester\":\"" + String.valueOf(subject.getSemester()) + "\",")
                .append("\"name\":\"" + String.valueOf(subject.getName()) + "\",")
                .append("\"general_description\":\"" + String.valueOf(subject.getResources()) + "\",")
                .append("\"field_of_studies_pk\":\"" + String.valueOf(field.getId()) + "\"")
                .append("}").toString();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://teleagh.herokuapp.com/api/subjects/"))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }


    public static void saveResources(Subject subject, Resource resource) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        String json = new StringBuilder()
                .append("{")
                .append("\"name\":\"" + String.valueOf(resource.getDescription()) + "\",")
                .append("\"url\":\"" + String.valueOf(resource.getLink()) + "\",")
                .append("\"subject_pk\":\"" + String.valueOf(subject.getId()) + "\"")
                .append("}").toString();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://teleagh.herokuapp.com/api/resources/"))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }
}

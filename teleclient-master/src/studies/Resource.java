package studies;

import APIClient.Client;

import java.io.IOException;

public class Resource extends SavedModel<String, String, Subject> {
    private String description;
    private String link;
    private int id;

    public Resource(String description, String link) {
        this.description = description;
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    @Override
    public SavedModel save(String description, String link, Subject subject) throws IOException, InterruptedException {
        this.description = description;
        this.link = link;
        Client client = new Client();
        Client.saveResources(subject, this);
        return this;
    }

    @Override
    public String toString() {
//        StringBuilder s = new StringBuilder();
//        s.append(description);
//        s.append(" ");
//        s.append(link);
////        s.append("");
//        return s.toString();
        return "User [id=" + id + ", name=" + description + ", url="
                + link + "]";
    }
}

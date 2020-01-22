package studies;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class ResourceFactory {
    public static ArrayList<Resource> getResources(Subject subject, int count){
        String basename = "resource";
        ArrayList<Resource> ResourceList = new ArrayList<Resource>();
        for(int i=0; i < count;i++){
            StringBuilder s = new StringBuilder();
            s.append(basename);
            s.append(" ");
            s.append(subject.getName());
            ResourceList.add(new Resource(s.toString(), getRandomUrl()));
        }
        return ResourceList;
    }
    private static String getRandomUrl() {
        String domain = UUID.randomUUID().toString().replace("-", "").replaceAll("\\d","");
        StringBuilder url = new StringBuilder();
        url.append("http://");
        url.append(domain);
        url.append(".pl");
        url.append('/');
        return url.toString();
    }
}

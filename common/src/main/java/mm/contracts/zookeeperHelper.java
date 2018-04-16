package mm.contracts;


import java.util.List;

public interface zookeeperHelper {
     List<String> getChildren(String path);
     String getData(String path);
}

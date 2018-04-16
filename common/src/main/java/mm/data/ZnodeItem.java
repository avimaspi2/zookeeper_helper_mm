package mm.data;


import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ZnodeItem {

    private String name;
    private String parent;
    private List<ZnodeItem>  children;
    private String path;
    private String data;

    public ZnodeItem() {
    }

    public ZnodeItem(String name, String parent, String path) {
        this.name = name;
        this.parent = parent;
        this.path = path;
        this.children=new ArrayList();
    }
}
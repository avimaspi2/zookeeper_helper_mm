package mm.logic;

import mm.contracts.AppLogic;
import mm.data.ZnodeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AppLogicImpl implements AppLogic {


    @Autowired
    mm.contracts.zookeeperHelper zookeeperHelper;





    @Override
    public List<ZnodeItem> GetAllZnodeItem() {

        ArrayList<ZnodeItem> res = new ArrayList<>();

        ZnodeItem main = new ZnodeItem("Nice", null,"/Nice");

        chenarateNodes(main);

        res.add(main);

        return res;



//        ZnodeItem sub3 = new ZnodeItem("sub3", "sub1", new ArrayList<>(),"df");
//        ZnodeItem sub4 = new ZnodeItem("sub4", "sub1", new ArrayList<>(),"dghdgfh");
//        ZnodeItem sub1 = new ZnodeItem("sub1", "main", new ArrayList<>(),"dsfgjy");
//        sub1.getChildren().add(sub3);
//        sub1.getChildren().add(sub4);
//
//        ZnodeItem sub2 = new ZnodeItem("sub2", "main", new ArrayList<>(),"asdf fghj f");
//
//
//        main.getChildren().add(sub1);
//        main.getChildren().add(sub2);
//
//        res.add(main);
//
//        return res;
    }

    @Override
    public String GetZnodeData(String Path) {
        return  zookeeperHelper.getData(Path);
    }


    private void chenarateNodes(ZnodeItem item){

        List<String> children = zookeeperHelper.getChildren(item.getPath());
        for (String child : children) {
            ZnodeItem znodeItem = new ZnodeItem(child, item.getName(),  item.getPath()+"/"+child);
            item.getChildren().add(znodeItem);
            chenarateNodes(znodeItem);
        }
    }





}

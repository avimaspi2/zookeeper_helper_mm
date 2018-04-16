package mm.contracts;

import mm.data.ZnodeItem;

import java.util.List;


public interface AppLogic {
    List<ZnodeItem> GetAllZnodeItem();
    String GetZnodeData(String Path);
}






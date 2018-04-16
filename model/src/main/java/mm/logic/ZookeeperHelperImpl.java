package mm.logic;

import lombok.SneakyThrows;
import mm.contracts.zookeeperHelper;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class ZookeeperHelperImpl implements zookeeperHelper, Watcher {


    private ZooKeeper zoo;

    //A synchronization aid that allows one or more threads to wait until a set of
    // operations being performed in other threads completes.
    final CountDownLatch connectedSignal = new CountDownLatch(1);


    public ZookeeperHelperImpl(String connection, int timeout) throws IOException {
        this.zoo = new ZooKeeper(connection, timeout, this);
    }

    @Override
    public void process(WatchedEvent we) {
        if (we.getState() == Watcher.Event.KeeperState.SyncConnected) {
            connectedSignal.countDown();
        }
    }



    @Override
    @SneakyThrows
    public List<String> getChildren(String path) {
        List<String> children = zoo.getChildren(path, this);
        return children;
    }

    @Override
    @SneakyThrows
    public String getData(String path) {
        byte[] data = zoo.getData(path, this, null);
        String res = new String(data, "UTF-8");
        return res;
    }


}

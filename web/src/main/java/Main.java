
import org.apache.zookeeper.KeeperException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.List;


@SpringBootApplication
@ComponentScan(basePackages = {"mm.infra"})
public class Main {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        SpringApplication.run(Main.class, args);
    }

}

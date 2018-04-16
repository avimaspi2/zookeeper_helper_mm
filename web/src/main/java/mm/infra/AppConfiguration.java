package mm.infra;


import lombok.SneakyThrows;
import mm.contracts.zookeeperHelper;
import mm.logic.ZookeeperHelperImpl;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"mm"})
@Profile("dev")
public class AppConfiguration {


    public AppConfiguration() {
        System.out.println("class AppConfiguration created");
    }


    @Bean(name = "dataSource")
    public DataSource dataSource() {
        System.out.println("data source created");
        EmbeddedDatabaseBuilder db = new EmbeddedDatabaseBuilder();


        db.setName("TEST_DB");
        db.generateUniqueName(true);
        db.setType(EmbeddedDatabaseType.H2);
        db.setScriptEncoding("UTF-8");
        db.ignoreFailedDrops(true);
        db.addScript("schema1.sql");
        db.addScripts("data1.sql");
        return db.build();
    }


    @Bean
    @SneakyThrows
    @Scope("prototype")
    public zookeeperHelper zookeeperHelper() {
        ZookeeperHelperImpl zookeeperTest = new ZookeeperHelperImpl("localhost:63344", 10000);
        return zookeeperTest;

    }


}

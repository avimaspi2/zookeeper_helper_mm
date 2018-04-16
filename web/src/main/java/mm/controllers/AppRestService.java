package mm.controllers;


import mm.contracts.AcountRepository;
import mm.contracts.AppLogic;
import mm.data.Account;
import mm.data.ZnodeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AppRestService {


    @Autowired
    private AppLogic zookeeperLogic;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private AcountRepository acountRepository;

    @Autowired
    mm.contracts.zookeeperHelper zookeeperHelper;


//    @CrossOrigin
//    @RequestMapping(value = "GetZnodeData/{path:.+}", method = RequestMethod.GET)
//    public String GetZnodeData(@PathVariable("path")  String path) {
//        return  zookeeperHelper.getData(path);
//    }


    @CrossOrigin
    @RequestMapping(value = "getZnodeData", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public String getZnodeData(@RequestBody ZnodeItem znodeItem) {
        return zookeeperHelper.getData(znodeItem.getPath());
    }


    @CrossOrigin
    @RequestMapping(value = "/postTest", method = RequestMethod.POST)
    public String postTest(@RequestBody Employee employee) {
        return "Hello" + employee.getName();
    }


    @CrossOrigin
    @RequestMapping(value = "/postPut/{id}", method = RequestMethod.PUT)
    public String postPut(@RequestBody Employee employee,@PathVariable("id") long id) {
        return "Hello" + employee.getName();
    }






    @RequestMapping(value = "/getTest", method = {RequestMethod.POST})
    public String GetTest() {
        return "hello get";
    }


    @CrossOrigin
    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public List<ZnodeItem> test() {


        List<String> children = zookeeperHelper.getChildren("/Nice");


        // String node = zookeeperTest.getNode("/Nice/Discovery/Splash/SplashBackEnd/Configuration.json");


//        Class<Account[]> aClass = Account[].class;
//
//        ResponseEntity<List<Account>> rateResponse =
//                restTemplate.exchange("http://localhost:4001/api/getCustomer",
//                        HttpMethod.POST,
//                        null,
//                        new ParameterizedTypeReference<List<Account>>() {}
//                );
//
//        List<Account> rates = rateResponse.getBody();


        //  Account account = restTemplate.postForObject("/getCustomer", null,Account.class);

        List<ZnodeItem> znodeItems = zookeeperLogic.GetAllZnodeItem();
        return znodeItems;
    }


    @CrossOrigin
    @RequestMapping(value = "/getCustomer", method = {RequestMethod.POST})
    public List<Account> getCustomer() {

        List<Account> accounts = acountRepository.getAccounts();


        return accounts;
    }


}

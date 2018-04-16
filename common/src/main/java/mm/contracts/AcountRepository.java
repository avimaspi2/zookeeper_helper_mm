package mm.contracts;

import mm.data.Account;

import java.math.BigDecimal;
import java.util.List;


public interface AcountRepository {

    List<Account> getAccounts();
    Account getAcount(Long  id);
    int getNumberOfAcount();
    Account ctrateAcount(String customerName, BigDecimal balance);
    int deleteAcount(Long id);
    Void updateAcount(Account account);
}

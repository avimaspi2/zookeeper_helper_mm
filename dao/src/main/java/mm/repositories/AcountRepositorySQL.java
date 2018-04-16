package mm.repositories;

import lombok.SneakyThrows;
import mm.contracts.AcountRepository;
import mm.data.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



@Repository
public class AcountRepositorySQL implements AcountRepository {


    private JdbcTemplate template;


    @SneakyThrows
    public AcountRepositorySQL(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);

//        Connection connection = template.getDataSource().getConnection();
//        CallableStatement exec_bla_bla_ = connection.prepareCall("EXEC bla bla ");
//        exec_bla_bla_.set
//        exec_bla_bla_.execute();

    }


    public List<Account> getAccounts() {
        String query = "select * from account";



        List<Account> res = this.template.query(query, new AcountMapper());
        return res;
    }

    public Account getAcount(Long id) {
        String query = "select * from account where id=?";
        Account res = this.template.queryForObject(query, new AcountMapper(), id);
        return res;
    }

    public int getNumberOfAcount() {
        String query = "select count(1) from account";
        Integer res = this.template.queryForObject(query, Integer.class);
        return res;
    }

    private int getMaxId() {
        String query = "select MAX(id) from account";
        Integer res = this.template.queryForObject(query, Integer.class);
        return res;
    }


    public Account ctrateAcount(String customerName, BigDecimal balance) {
        String query = "INSERT INTO account(customerName,balance,id) VALUES(?,?,?)";

        int newId = getMaxId() + 1;

        this.template.update(query, customerName, balance, newId);
        Account res = getAcount(new Long(newId));

        return res;
    }

    public int deleteAcount(Long id) {
        String query = "DELETE  from account WHERE id=?";
        this.template.update(query, id);
        return 0;
    }

    public Void updateAcount(Account account) {
        String query = "UPDATE account set balance=?";
        this.template.update(query, account.getBalance());
        return null;
    }

    private class AcountMapper implements RowMapper<Account> {
        //@Nullable
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Account(resultSet.getString("customerName"), resultSet.getLong("id"), resultSet.getBigDecimal("balance"));
        }
    }

}

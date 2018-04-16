package mm.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Account {
    private String customerName;

    @Id
    private Long id;
    private BigDecimal balance;

    public Account(String customerName, Long id, BigDecimal balance) {
        this.customerName = customerName;
        this.id = id;
        this.balance = balance;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return String.format("Acount name is:%d, balance is:%s", this.id, this.balance);
    }


    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
package beans.models;

import javax.persistence.*;

@Entity
@Table(name = "USERACCOUNT")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    @OneToOne(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private User user;

    public UserAccount(Long amount) {
        this.amount = amount;
    }

    public UserAccount() {
        this.setAmount(0L);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }
}

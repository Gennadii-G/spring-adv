package beans.models;

import sun.awt.SunHints;

import javax.persistence.*;

@Entity
@Table(name = "USERACCOUNT")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserAccount(Double amount) {
        this.amount = amount;
    }

    public UserAccount() {
        this.setAmount(0d);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}

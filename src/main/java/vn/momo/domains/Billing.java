package vn.momo.domains;

import vn.momo.common.enums.BillingState;
import vn.momo.common.enums.BillingType;

import java.text.MessageFormat;
import java.time.Instant;

public class Billing {
    private Integer id;
    //    private Provider provider;
    private String provider;
    private BillingType type;
    private BillingState state;
    private Instant dueDate;
    private Integer amount;

    public Billing(){}

    public Billing(Integer id, String provider, BillingType type, BillingState state, Instant dueDate, Integer amount) {
        this.id = id;
        this.provider = provider;
        this.type = type;
        this.state = state;
        this.dueDate = dueDate;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public BillingType getType() {
        return type;
    }

    public void setType(BillingType type) {
        this.type = type;
    }

    public BillingState getState() {
        return state;
    }

    public void setState(BillingState state) {
        this.state = state;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "Billing[Id: {0}, type: {1}, amount: {2}, due date: {3}, state: {4}, provider: {5}]",
                id, type, amount, dueDate, state, provider);
    }
}

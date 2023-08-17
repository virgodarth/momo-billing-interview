package vn.momo.repositories;

import vn.momo.common.enums.BillingState;
import vn.momo.common.enums.BillingType;
import vn.momo.domains.Billing;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class BillingRepository {
    private final List<Billing> billings;

    public BillingRepository() {
        // create mock data
//        final Provider viettel = new Provider(1, "VIETTEL");
//        final Provider evn = new Provider(2, "EVN");
//        final Provider savaco = new Provider(3, "SAVACO");

        final Billing viettelBilling = new Billing(1, "VIETTEL", BillingType.INTERNET, BillingState.NOT_PAID, Instant.ofEpochMilli(1698019200000L), 132000);
        final Billing evnBilling = new Billing(2, "EVN", BillingType.ELECTRIC, BillingState.NOT_PAID, Instant.ofEpochMilli(1700697600000L), 132000);
        final Billing savacoBilling = new Billing(3, "SAVACO", BillingType.WATER, BillingState.NOT_PAID, Instant.ofEpochMilli(1696982400000L), 132000);

        this.billings = new ArrayList<>();
        this.billings.add(viettelBilling);
        this.billings.add(evnBilling);
        this.billings.add(savacoBilling);
    }

    public List<Billing> getAllBillings() {
        return this.billings;
    }

    public Billing getOneBilling(int id) {
        return this.billings.stream().filter(b -> b.getId() == id).findFirst().orElseThrow();
    }

    public Billing saveBilling(Billing billing) {
        if (billing.getId() == null) {
            int nextId = this.billings.stream().map(Billing::getId).max(Integer::compareTo).orElse(0) + 1;
            billing.setId(nextId);
        } else {
            this.billings.removeIf(b -> b.getId() == billing.getId());
        }

        this.billings.add(billing);
        return billing;
    }

    public void deleteBilling(int id) {
        this.billings.removeIf(b -> b.getId() == id);
    }

}

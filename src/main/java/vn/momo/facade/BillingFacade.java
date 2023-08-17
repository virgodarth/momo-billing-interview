package vn.momo.facade;

import vn.momo.domains.Billing;
import vn.momo.services.BillingService;
import vn.momo.services.UserService;

import java.util.List;

public class BillingFacade {
    private final BillingService billingService;
    private final UserService userService;

    public BillingFacade(BillingService billingService, UserService userService) {
        this.billingService = billingService;
        this.userService = userService;
    }

    public List<Billing> getBillings() {
        return this.billingService.getAllBillings();
    }

    public Billing addBilling(Billing billing) {
        return this.billingService.addBilling(billing);
    }

    public Billing updateBilling(int id, Billing billing) {
        return this.billingService.updateBilling(id, billing);
    }
}

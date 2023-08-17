package vn.momo.facade;

import vn.momo.common.enums.BillingState;
import vn.momo.domains.Billing;
import vn.momo.domains.Transaction;
import vn.momo.services.BillingService;
import vn.momo.services.TransactionService;
import vn.momo.services.UserService;

public class PaymentFacade {
    private final BillingService billingService;
    private final UserService userService;
    private final TransactionService transactionService;

    public PaymentFacade(BillingService billingService, UserService userService, TransactionService transactionService) {
        this.billingService = billingService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    public Transaction payBillingById(int billingId) {
        final Billing billing = billingService.getBillingById(billingId);
        // subtract balance
        this.userService.subtractBalance(billing.getAmount());
        // update billing
        billing.setState(BillingState.PAID);
        this.billingService.updateBilling(billingId, billing);
        // create transaction
        return this.transactionService.addTransaction(new Transaction());
    }
}

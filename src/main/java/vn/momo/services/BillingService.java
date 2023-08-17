package vn.momo.services;

import vn.momo.domains.Billing;
import vn.momo.repositories.BillingRepository;

import java.util.List;

public class BillingService {
    private final BillingRepository billingRepository;

    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    public List<Billing> getAllBillings() {
        return this.billingRepository.getAllBillings();
    }

    public Billing getBillingById(int id) {
        return this.billingRepository.getOneBilling(id);
    }

    public Billing addBilling(Billing billing) {
        return this.billingRepository.saveBilling(billing);
    }

    public Billing updateBilling(int id, Billing billing) {
        billing.setId(id);
        return this.billingRepository.saveBilling(billing);
    }

    public void deleteBillingById(int id) {
        this.billingRepository.deleteBilling(id);
    }
}

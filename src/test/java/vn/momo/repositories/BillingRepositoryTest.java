package vn.momo.repositories;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import vn.momo.common.enums.BillingState;
import vn.momo.common.enums.BillingType;
import vn.momo.domains.Billing;
import vn.momo.domains.User;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BillingRepositoryTest {
    private final BillingRepository billingRepository;

    public BillingRepositoryTest() {
        this.billingRepository = new BillingRepository();
    }

    @Test
    public void givenBillingsReflection_whenGetAllBillings_thenReturnAllBillings() throws NoSuchFieldException, IllegalAccessException {
        // Given
        List<Billing> initBillings = List.of(new Billing(), new Billing(), new Billing());
        Class<?> repositoryClass = BillingRepository.class;
        Field field = repositoryClass.getDeclaredField("billings");
        field.setAccessible(true);
        field.set(billingRepository, initBillings);
        field.setAccessible(false);

        // When
        List<Billing> billings = this.billingRepository.getAllBillings();

        // Then
        Assertions.assertEquals(initBillings.size(), billings.size());
    }

    @Test
    public void givenBillingsReflection_whenGetBillingByExistedId_thenReturnExactlyBilling() throws NoSuchFieldException, IllegalAccessException {
        // Given
        Billing test = new Billing(2, "EVN", BillingType.ELECTRIC, BillingState.NOT_PAID, Instant.ofEpochMilli(1700697600000L), 132000);
        List<Billing> initBillings = List.of(
            new Billing(1, "VIETTEL", BillingType.INTERNET, BillingState.NOT_PAID, Instant.ofEpochMilli(1698019200000L), 132000),
            test,
            new Billing(3, "SAVACO", BillingType.WATER, BillingState.NOT_PAID, Instant.ofEpochMilli(1696982400000L), 132000));
        Class<?> repositoryClass = BillingRepository.class;
        Field field = repositoryClass.getDeclaredField("billings");
        field.setAccessible(true);
        field.set(billingRepository, initBillings);
        field.setAccessible(false);

        // When
        Billing billing = this.billingRepository.getOneBilling(test.getId());

        // Then
        Assertions.assertEquals(test.getId(), billing.getId());
        Assertions.assertEquals(test.getProvider(), billing.getProvider());
        Assertions.assertEquals(test.getAmount(), billing.getAmount());
    }

    @Test
    public void givenBillingsReflection_whenGetBillingByNotExistedId_thenReturnExactlyBilling() throws NoSuchFieldException, IllegalAccessException {
        // Given
        List<Billing> initBillings = List.of(
                new Billing(1, "VIETTEL", BillingType.INTERNET, BillingState.NOT_PAID, Instant.ofEpochMilli(1698019200000L), 132000),
                new Billing(2, "EVN", BillingType.ELECTRIC, BillingState.NOT_PAID, Instant.ofEpochMilli(1700697600000L), 132000),
                new Billing(3, "SAVACO", BillingType.WATER, BillingState.NOT_PAID, Instant.ofEpochMilli(1696982400000L), 132000));
        Class<?> repositoryClass = BillingRepository.class;
        Field field = repositoryClass.getDeclaredField("billings");
        field.setAccessible(true);
        field.set(billingRepository, initBillings);
        field.setAccessible(false);

        // When & Then
        assertThrows(
                NoSuchElementException.class,
                () -> billingRepository.getOneBilling(1100));
    }

    @Test
    public void givenNewBilling_whenCreateBilling_thenReturnCreatedBilling(){
        // Given
        Billing billingRequest = new Billing(null, "SAVACO", BillingType.WATER, BillingState.NOT_PAID, Instant.ofEpochMilli(1696982400000L), 132000);

        // When
        Billing createdBilling = this.billingRepository.saveBilling(billingRequest);

        // Then
        assertNotNull(createdBilling.getId());
    }

    @Test
    public void givenUpdatedBilling_whenUpdateBilling_thenReturnUpdatedBilling(){
        // Given
        Billing billingRequest = new Billing(2, "SAVACO", BillingType.WATER, BillingState.PAID, Instant.ofEpochMilli(1696982400000L), 132000);

        // When
        this.billingRepository.saveBilling(billingRequest);
        Billing updatedBilling = billingRepository.getOneBilling(billingRequest.getId());

        // Then
        assertEquals(BillingType.WATER, updatedBilling.getType());
    }

    @Test
    public void givenBillingId_whenDeleteBilling_thenReturnNoneException(){
        // Given
        int billingId = 2;

        // When
        this.billingRepository.deleteBilling(billingId);

        // Then

    }
}

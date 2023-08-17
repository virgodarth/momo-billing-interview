package vn.momo;

import vn.momo.common.enums.BillingState;
import vn.momo.common.enums.BillingType;
import vn.momo.common.enums.UserAction;
import vn.momo.domains.Billing;
import vn.momo.domains.User;
import vn.momo.facade.BillingFacade;
import vn.momo.facade.PaymentFacade;
import vn.momo.facade.UserFacade;
import vn.momo.repositories.BillingRepository;
import vn.momo.repositories.UserRepository;
import vn.momo.services.BillingService;
import vn.momo.services.UserService;

import java.text.MessageFormat;
import java.time.Instant;

public class Main {
    private static UserFacade userFacade;
    private static BillingFacade billingFacade;
    private static PaymentFacade paymentFacade;

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing arguments");
        }

        setUpInjection();
        execute(args);

    }

    private static void setUpInjection() {
        // repository
        final UserRepository userRepository = new UserRepository();
        final BillingRepository billingRepository = new BillingRepository();

        // Service
        final UserService userService = new UserService(userRepository);
        final BillingService billingService = new BillingService(billingRepository);

        // Facade
        userFacade = new UserFacade(userService);
        billingFacade = new BillingFacade(billingService, userService);
    }

    private static UserAction parseFromString(String userAction) {
        try {
            return Enum.valueOf(UserAction.class, userAction);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessageFormat.format("Invalid action: {0}", userAction));
        }
    }

    private static void execute(String[] args) {
        final UserAction userAction = parseFromString(args[0]);

        switch (userAction) {
            case CASH_IN -> {
                User user = userFacade.depositBalance(Integer.parseInt(args[1]));
                System.out.println(user);
            }
            case BILL_LIST -> billingFacade.getBillings().forEach(System.out::println);
            case BILL_CREATE -> {
                Billing billingRequest = new Billing(
                        null,
                        args[1],
                        BillingType.valueOf(args[2]),
                        BillingState.NOT_PAID,
                        Instant.ofEpochMilli(Long.parseLong(args[3])),
                        Integer.parseInt(args[4]));
                Billing billing = billingFacade.addBilling(billingRequest);
                System.out.println(billing);
            }
            default -> System.out.printf("Service is developing for action '{}'%n", userAction);
        }
    }
}
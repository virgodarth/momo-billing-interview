- add fund into his account:
	```java -jar out/artifacts/vmbilling_jar/vmbilling.jar CASH_IN {{deposit_amount}}```

	Example: ```java -jar out/artifacts/vmbilling_jar/vmbilling.jar CASH_IN 1000```
- get list of billings:
	```java -jar out/artifacts/vmbilling_jar/vmbilling.jar BILL_LIST```
- create new billing:
	```java -jar out/artifacts/vmbilling_jar/vmbilling.jar BILL_CREATE {{provider}} {{billing_type: ELECTRIC, WATER, INTERNET}} {{due_date_in_timestamp}} {{billing_amount}}```

	Example: ```java -jar out/artifacts/vmbilling_jar/vmbilling.jar BILL_CREATE VNPT INTERNET 1700611200000 2000```



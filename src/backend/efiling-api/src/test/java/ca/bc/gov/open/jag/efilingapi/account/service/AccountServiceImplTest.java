package ca.bc.gov.open.jag.efilingapi.account.service;

import ca.bc.gov.open.jag.efilingapi.TestHelpers;
import ca.bc.gov.open.jag.efilingcommons.model.AccountDetails;
import ca.bc.gov.open.jag.efilingcommons.service.EfilingAccountService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("AccountServiceImpl test suite")
public class AccountServiceImplTest {

    private AccountServiceImpl sut;

    @Mock
    private EfilingAccountService efilingAccountServiceMock;


    @BeforeAll
    public void setup() {

        MockitoAnnotations.initMocks(this);
        AccountDetails accountDetails = AccountDetails
                .builder()
                .accountId(BigDecimal.TEN)
                .create();

        Mockito.when(efilingAccountServiceMock.getAccountDetails(Mockito.eq(TestHelpers.CASE_1), Mockito.anyString()))
                .thenReturn(accountDetails);

        sut = new AccountServiceImpl(efilingAccountServiceMock);
    }

    @Test
    @DisplayName("OK: should return an account")
    public void withRequestShouldReturnAnAccount() {

        AccountDetails actual = sut.getCsoAccountDetails(TestHelpers.CASE_1);
        Assertions.assertEquals(BigDecimal.TEN, actual.getAccountId());

    }


}
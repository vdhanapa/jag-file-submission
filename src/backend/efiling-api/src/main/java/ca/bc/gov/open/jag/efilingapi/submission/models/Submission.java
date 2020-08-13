package ca.bc.gov.open.jag.efilingapi.submission.models;

import ca.bc.gov.open.jag.efilingapi.api.model.ClientApplication;
import ca.bc.gov.open.jag.efilingapi.api.model.FilingPackage;
import ca.bc.gov.open.jag.efilingapi.api.model.Navigation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents the submission details for a transaction
 */
public class Submission {

    private UUID id;

    private UUID transactionId;

    private BigDecimal accountId;

    private BigDecimal clientId;

    private String internalClientNumber;

    private long expiryDate;

    private Navigation navigation;

    private ClientApplication clientApplication;

    private FilingPackage filingPackage;

    protected Submission(Submission.Builder builder) {
        this.id = builder.id;
        this.accountId = builder.accountId;
        this.clientId = builder.clientId;
        this.internalClientNumber = builder.internalClientNumber;
        this.transactionId = builder.transactionId;
        this.filingPackage = builder.filingPackage;
        this.navigation = builder.navigation;
        this.clientApplication = builder.clientApplication;
        this.expiryDate = builder.expiryDate;
    }

    public static Submission.Builder builder() {
        return new Submission.Builder();
    }

    @JsonCreator
    public Submission(
            @JsonProperty("id") UUID id,
            @JsonProperty("accountId") BigDecimal accountId,
            @JsonProperty("clientId") BigDecimal clientId,
            @JsonProperty("internalClientNumber") String internalClientNumber,
            @JsonProperty("owner") UUID transactionId,
            @JsonProperty("package") FilingPackage filingPackage,
            @JsonProperty("navigation") Navigation navigation,
            @JsonProperty("clientApplication") ClientApplication clientApplication,
            @JsonProperty("expiryDate") long expiryDate) {
        this.id = id;
        this.accountId = accountId;
        this.clientId = clientId;
        this.internalClientNumber = internalClientNumber;
        this.transactionId = transactionId;
        this.filingPackage = filingPackage;
        this.navigation = navigation;
        this.clientApplication = clientApplication;
        this.expiryDate = expiryDate;
    }

    public UUID getId() { return id; }

    public UUID getTransactionId() { return transactionId; }

    public FilingPackage getFilingPackage() {
        return filingPackage;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public ClientApplication getClientApplication() { return clientApplication; }

    public long getExpiryDate() {
        return expiryDate;
    }

    public BigDecimal getAccountId() { return accountId; }

    public void setAccountId(BigDecimal accountId) {  this.accountId = accountId;  }

    public BigDecimal getClientId() {  return clientId; }

    public void setInternalClientNumber(String internalClientNumber) { this.internalClientNumber = internalClientNumber; }

    public String getInternalClientNumber() { return internalClientNumber; }

    public void setClientId(BigDecimal clientId) { this.clientId = clientId;  }

    public static class Builder {

        private UUID id;
        private BigDecimal accountId;
        private BigDecimal clientId;
        private String internalClientNumber;
        private UUID transactionId;
        private FilingPackage filingPackage;
        private Navigation navigation;
        private ClientApplication clientApplication;
        private long expiryDate;

        public Builder id (UUID id) {
            this.id = id;
            return this;
        }
        public Builder accountId (BigDecimal accountId) {
            this.accountId = accountId;
            return this;
        }
        public Builder clientId (BigDecimal clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder internalClientNumber (String internalClientNumber) {
            this.internalClientNumber = internalClientNumber;
            return this;
        }

        public Builder transactionId(UUID owner) {
            this.transactionId = owner;
            return this;
        }

        public Builder filingPackage(FilingPackage filingPackage) {
            this.filingPackage =  filingPackage;
            return this;
        }

        public Builder navigation(Navigation navigation) {
            this.navigation = navigation;
            return this;
        }

        public Builder clientApplication(ClientApplication clientApplication) {
            this.clientApplication = clientApplication;
            return this;
        }

        public Builder expiryDate(long expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Submission create() {
            return new Submission(this);
        }
    }

}

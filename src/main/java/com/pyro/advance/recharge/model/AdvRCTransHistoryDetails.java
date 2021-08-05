package com.pyro.advance.recharge.model;




public class AdvRCTransHistoryDetails {
    private String id;
    private String recordId;
    private String amount;
    private String transactionDate;
    private String plandetails;
    private String validity;

    public AdvRCTransHistoryDetails() {
    }

    public AdvRCTransHistoryDetails(String id, String recordId, String amount, String transactionDate, String plandetails, String validity) {
        this.id = id;
        this.recordId = recordId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.plandetails = plandetails;
        this.validity = validity;
    }
    
    

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the recordId
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * @param recordId the recordId to set
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the transactionDate
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the plandetails
     */
    public String getPlandetails() {
        return plandetails;
    }

    /**
     * @param plandetails the plandetails to set
     */
    public void setPlandetails(String plandetails) {
        this.plandetails = plandetails;
    }

    /**
     * @return the validity
     */
    public String getValidity() {
        return validity;
    }

    /**
     * @param validity the validity to set
     */
    public void setValidity(String validity) {
        this.validity = validity;
    }
    
    
}

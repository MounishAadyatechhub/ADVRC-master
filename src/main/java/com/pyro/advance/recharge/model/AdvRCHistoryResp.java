package com.pyro.advance.recharge.model;



import java.util.List;


public class AdvRCHistoryResp {
    private String status;
    private String desc;
    private List<AdvRCTransHistoryDetails> advRCTransHistoryDetails;

    public AdvRCHistoryResp() {
    }

    public AdvRCHistoryResp(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the advRCTransHistoryDetails
     */
    public List<AdvRCTransHistoryDetails> getAdvRCTransHistoryDetails() {
        return advRCTransHistoryDetails;
    }

    /**
     * @param advRCTransHistoryDetails the advRCTransHistoryDetails to set
     */
    public void setAdvRCTransHistoryDetails(List<AdvRCTransHistoryDetails> advRCTransHistoryDetails) {
        this.advRCTransHistoryDetails = advRCTransHistoryDetails;
    }
    
    

}

package com.pyro.advance.recharge.model;



public class AdvRCActivationResp {
    private String desc;
    private String recordId;
    private String status;
    
    public AdvRCActivationResp()
    {
    	
    }
    public AdvRCActivationResp(String desc, String status) {
		this.desc = desc;
		this.status = status;
	}
    
	public AdvRCActivationResp(String desc, String recordId, String status) {
		this.desc = desc;
		this.recordId = recordId;
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return " {desc:" + desc + ", recordId:" + recordId + ", status:" + status + "}";
	}

}

/*

 */

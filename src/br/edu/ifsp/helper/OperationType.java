package br.edu.ifsp.helper;

public enum OperationType {

	PERSISTENCE("-p"),
	RETRIVEMENT("-r");
	
	private String operationType;

	OperationType(String operationType) {
		this.operationType = operationType;
	}
	
	public String getValue() {
		return this.operationType;
	}

	public boolean valueOf(OperationType persistence) {
		return this.operationType.equals(persistence.getValue());
	}
}

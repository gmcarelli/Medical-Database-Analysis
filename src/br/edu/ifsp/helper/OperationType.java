package br.edu.ifsp.helper;

public enum OperationType {

	PERSISTENCE("-p"), RETRIVEMENT("-r");

	private String value;

	OperationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public boolean valueOf(OperationType persistence) {
		return this.value.equals(persistence.getValue());
	}

	public static OperationType getValue(String value) {
		for (OperationType e : OperationType.values())
			if (e.value.equals(value))
				return e;

		return null;
	}
}

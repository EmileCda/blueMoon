package fr.emile.enums;

public enum Gender {
MALE("Homme","M."), FEMALE("Femme","Mme"), OTHERS("autre","?"),NULL("","") ;
	
	private String value;
	private String title;
	
	private Gender(String value,String title) {
		this.setValue(value);
		this.setTitle(title);
	}
	private Gender() {
		
		this("","");
	}
	
	
	public static Gender fromString(String text) {
		for (Gender oneValue : Gender.values()) {
			if (oneValue.getValue().equalsIgnoreCase(text)) {
				return oneValue;
			}
		}
		return null;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}

}

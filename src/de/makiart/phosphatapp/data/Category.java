package de.makiart.phosphatapp.data;

public class Category {

	private String displayName;
	private String name;

	/**
	 * Kategorie einer Speise. 
	 * Name entspricht hier der Bezeichnung aus der XML-Datei.
	 * DisplayName ist der angezeigte Text (aus R.strings).
	 * @param name
	 * @param displayName
	 */
	public Category(String name, String displayName) {
		this.name = name;
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

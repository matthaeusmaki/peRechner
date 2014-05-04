package de.makiart.phosphatapp.logic;

public class Food {

	private int id;
	private int peValue;
	private int amount;
	
	private String name;
	private String measurement;
	
	private Category category;
	
	public static final String FOOD_ATTRIBUTE_ID = "id";
	public static final String FOOD_ATTRIBUTE_NAME = "name";
	public static final String FOOD_ATTRIBUTE_PEVALUE = "peValue";
	public static final String FOOD_ATTRIBUTE_AMOUNT = "amount";
	public static final String FOOD_ATTRIBUTE_MEASUREMENT = "measurement";
	
	/**
	 * Eine neue Speise mit einem Name, dem Typ des Essens, den PE Wert (Phosphateinheit) und der Menge in mg erstellen.
	 * @param name
	 * @param peValue
	 * @param amount
	 */
	public Food(int id, String name, Category category, int peValue, int amount, String measurement) {
		this.id = id;
		this.name = name;
		this.peValue = peValue;
		this.amount = amount;
		this.category = category;
		this.measurement = measurement;
	}
	
	public Food() {
		
	}

	
	
	@Override
	public String toString() {
		return name + " " + peValue + " (" + amount + " " + measurement + ")";
	}

	/**
	 * Gibt <code>true</code> zurück, wenn die Speise plausible Werte besitzt.
	 * Plausibel bedeutet hierbei, dass die Speise einen Namen besitzt, einen PE-Wert,
	 * eine Kategorie, eine Menge und einen Maßeinheit
	 * @return
	 */
	public boolean isValidFood() {
		return (name != null && !name.isEmpty()
				&& peValue >= 0
				&& amount >= 0
				&& category != null && category.getName() != null && !category.getName().isEmpty()
				&& measurement != null && !measurement.isEmpty());
	}
	
	/**
	 * Gibt die id der selektierten Nahrung zurück
	 * @return
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Id zum identifizieren des selektierten essens. 
	 * Soll nicht beim Anlegen des Essens gesetzt werden, sondern
	 * wenn das Essen selektiert und in der Logik abgelegt wurde
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gibt den Namen der Speise zurück
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Namen der Speise setzen
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gibt den PE Wert (Phosphateinheit) zurück	
	 * @return
	 */
	public int getPeValue() {
		return peValue;
	}
	
	/**
	 * Setzt den PE Wert (Phosphateinheit)
	 * @param peValue
	 */
	public void setPeValue(int peValue) {
		this.peValue = peValue;
	}
	
	/**
	 * Gibt Mengenangabe in mg zurück
	 * @return
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * Mengenangabe im mg setzen
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	/**
	 * Gibt Essens Kategorie dieser Speise zurück
	 * @return
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Gibt die Maßeinheit für die Mengenangabe zurück
	 * @return
	 */
	public String getMeasurement() {
		return measurement;
	}
	
	/**
	 * Setzen der Maßeinheit für die Mengenangabe
	 * @return
	 */
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	/**
	 * Setzt Essenskategory dieser Speise
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
}

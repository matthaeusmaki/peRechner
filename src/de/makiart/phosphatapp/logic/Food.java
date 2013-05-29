package de.makiart.phosphatapp.logic;

import de.makiart.phosphatapp.data.FoodData.Category;

public class Food {

	private int id;
	private String name;
	private Category category;
	private int peValue;
	private int amount;
	
	public static final String FOOD_ATTRIBUTE_ID = "id";
	public static final String FOOD_ATTRIBUTE_NAME = "name";
	public static final String FOOD_ATTRIBUTE_PEVALUE = "peValue";
	public static final String FOOD_ATTRIBUTE_AMOUNT = "amount";
	
	/**
	 * Eine neue Speise mit einem Name, den PE Wert (Phosphateinheit) und der Menge in mg erstellen.
	 * @param name
	 * @param peValue
	 * @param amount
	 */
	public Food(int id, String name, int peValue, int amount) {
		this.id = id;
		this.name = name;
		this.peValue = peValue;
		this.amount = amount;
	}
	
	public Food() {
		
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
	 * Setzt Essenskategory dieser Speise
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
}

package de.makiart.phosphatapp.logic;

public class Food {

	private int id;
	private String name;
	private int peValue;
	private int portion;
	
	/**
	 * Eine neue Speise mit einem Name, den PE Wert (Phosphateinheit) und der Menge in mg erstellen.
	 * @param name
	 * @param peValue
	 * @param portion
	 */
	public Food(String name, int peValue, int portion) {
		this.name = name;
		this.peValue = peValue;
		this.portion = portion;
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
	public int getPortion() {
		return portion;
	}
	
	/**
	 * Mengenangabe im mg setzen
	 * @param portion
	 */
	public void setPortion(int portion) {
		this.portion = portion;
	}
	
	
}

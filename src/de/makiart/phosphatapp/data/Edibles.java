package de.makiart.phosphatapp.data;


public class Edibles implements Comparable<Edibles> {

	private int id;
	private short peValue;
	private short amount;
	
	private String name;
	private String measurement;
	
	private Category category;
	
	private int times = 1;
	
	public static final String FOOD_ATTRIBUTE_ID = "id";
	public static final String FOOD_ATTRIBUTE_NAME = "name";
	public static final String FOOD_ATTRIBUTE_PEVALUE = "peValue";
	public static final String FOOD_ATTRIBUTE_AMOUNT = "amount";
	public static final String FOOD_ATTRIBUTE_AMOUNT_TIMES = "times";
	public static final String FOOD_ATTRIBUTE_MEASUREMENT = "measurement";
	
	/**
	 * Eine neue Speise mit einem Name, dem Typ des Essens, den PE Wert (Phosphateinheit) und der Menge in mg erstellen.
	 * @param name
	 * @param peValue
	 * @param amount
	 */
	public Edibles(int id, String name, Category category, short peValue, short amount, String measurement) {
		this.id = id;
		this.name = name;
		this.peValue = peValue;
		this.amount = amount;
		this.category = category;
		this.measurement = measurement;
	}
	
	public Edibles() {
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
	public short getPeValue() {
		return peValue;
	}
	
	/**
	 * Setzt den PE Wert (Phosphateinheit)
	 * @param peValue
	 */
	public void setPeValue(short peValue) {
		this.peValue = peValue;
	}
	
	/**
	 * Gibt Mengenangabe in mg zurück
	 * @return
	 */
	public short getAmount() {
		return amount;
	}
	
	/**
	 * Mengenangabe im mg setzen
	 * @param amount
	 */
	public void setAmount(short amount) {
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

	@Override
	public int compareTo(Edibles another) {
		if (another.getName() == null && this.getName() == null) {
			return 0;
		}
		if (this.getName() == null) {
			return 1;
		}
		if (another.getName() == null) {
			return -1;
		}
		return this.getName().compareTo(another.getName());
	}

	public int getTimes() {
		return times;
	}

	/**
	 * Setzt wie oft die Standard-Menge multipliziert wird.<br />
	 * Mindestens jedoch 1.
	 * @param times
	 */
	public void setTimes(int times) {
		this.times = Math.max(1, times);
	}
}

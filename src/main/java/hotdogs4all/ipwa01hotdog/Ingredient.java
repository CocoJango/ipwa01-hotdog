package hotdogs4all.ipwa01hotdog;

import java.util.Objects;

public class Ingredient
{

	private String name;
	private double price;
	private boolean required;

	public Ingredient(String name, double price)
	{
		this(name, price, false);
	}

	public Ingredient(String name, double price, boolean required)
	{
		this.name = name;
		this.price = price;
		this.required = required;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public boolean isRequired()
	{
		return required;
	}

	public void setRequired(boolean required)
	{
		this.required = required;
	}

	/**
	 * Had to override .equals so view recognizes initial ingredients as selected
	 *
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ingredient that = (Ingredient) o;
		return Double.compare(that.price, price) == 0 && required == that.required && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name, price, required);
	}
}

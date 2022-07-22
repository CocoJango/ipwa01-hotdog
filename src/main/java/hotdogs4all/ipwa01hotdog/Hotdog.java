package hotdogs4all.ipwa01hotdog;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Hotdog
{
	private List<Ingredient> ingredients = new LinkedList<>();
	private double price;
	private double discount = 0;
	private double discountEuro;
	private double discountedPrice;
	private UUID id;

	public Hotdog()
	{
		// add all ingredients that have been initialized with `isRequired = true`
		for (Ingredient ingr : Shop.getInstance().getIngredients()) {
			if (ingr.isRequired()) {
				this.ingredients.add(ingr);
			}
		}
		this.id = UUID.randomUUID();
	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public List<Ingredient> getIngredients()
	{
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients)
	{
		this.ingredients = ingredients;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public double getDiscount()
	{
		return discount;
	}

	public void setDiscount(double discount)
	{
		this.discount = discount;
	}

	public double getDiscountEuro()
	{
		return discountEuro;
	}

	public void setDiscountEuro(double discountEuro)
	{
		this.discountEuro = discountEuro;
	}

	public double getDiscountedPrice()
	{
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice)
	{
		this.discountedPrice = discountedPrice;
	}

}

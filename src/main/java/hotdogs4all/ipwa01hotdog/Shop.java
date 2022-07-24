package hotdogs4all.ipwa01hotdog;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

@Named
@ApplicationScoped
public class Shop
{
	static final String feedbackUrl = "http://localhost:8080/ipwa01-hotdog-1.0-SNAPSHOT/feedback";
	private static final Shop instance = new Shop();
	private static final DecimalFormat df = new DecimalFormat("0,00");
	private final List<Ingredient> ingredients = new LinkedList<>();

	public Shop()
	{
		// add basic configuration
		ingredients.add(new Ingredient("Brot", 1.5, true));
		ingredients.add(new Ingredient("Wurst", 2.5, true));

		// add other ingredients
		ingredients.add(new Ingredient("Röstzwiebeln", 0.5));
		ingredients.add(new Ingredient("Käse", 1));
		ingredients.add(new Ingredient("Essiggurken", 0.5));
		ingredients.add(new Ingredient("Salat", 0.5));
		ingredients.add(new Ingredient("Zwiebeln", 0.5));
		ingredients.add(new Ingredient("Ketchup", 0.5));
		ingredients.add(new Ingredient("Senf", 0.5));

		System.out.println("init Shop " + this);
	}

	public static Shop getInstance()
	{
		return instance;
	}

	public List<Ingredient> getIngredients()
	{
		return ingredients;
	}



	/**
	 * Generates the URL to the feedback page by concatenating the URL from the Shop instance and the id of the current Hotdog.
	 *
	 * @param hotdog
	 * @return the URL of the given Hotdog's feedback page
	 */
	public String feedbackUrl(Hotdog hotdog)
	{
		return feedbackUrl + "?order=" + hotdog.getId();
	}

	/**
	 * Returns the given number as a decimal number with a €-Symbol appended.
	 *
	 * @param number
	 * @return
	 */
	public String formatEuro(Double number)
	{
		return String.format("%.2f", number).concat(" €");
	}

}

package hotdogs4all.ipwa01hotdog;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class HotdogController implements Serializable
{
	private final Hotdog hotdog = new Hotdog();
	private List<Ingredient> selectedIngredients;

	public HotdogController()
	{
		// mark the required ingredients as selected
		this.selectedIngredients = hotdog.getIngredients();
		doCalculations();
	}

	public Hotdog getHotdog()
	{
		return hotdog;
	}

	public List<Ingredient> getSelectedIngredients()
	{
		return selectedIngredients;
	}

	public void setSelectedIngredients(List<Ingredient> selectedIngredients)
	{
		this.selectedIngredients = selectedIngredients;
	}

	/**
	 * Calls all necessary methods for calculating the various prices (regular, discount discounted total price) of a Hotdog.
	 */
	private void doCalculations()
	{
		calculateDiscount();
		calculatePrice();
		calculateDiscountedPrice();
	}

	/**
	 * Should be called whenever an ingredient has been de-/selected.
	 * Will synchronize the list of the selected ingredients (view) and the ingredients of the actual Hotdog (model).
	 */
	public void selectedIngredientsChanged()
	{
		hotdog.setIngredients(selectedIngredients);
		doCalculations();
	}

	/**
	 * Sums up the prices of all ingredients and sets the Hotdog's price.
	 */
	private void calculatePrice()
	{
		double total = 0;

		for (Ingredient ingredient : hotdog.getIngredients()) {
			total += ingredient.getPrice();
		}
		hotdog.setPrice(total);
	}

	/**
	 * Calculates the discount for the additional ingredients and sets the Hotdog's discount value.
	 */
	private void calculateDiscount()
	{

		// amount of selected ingredients minus the required ones
		long relevantAmount = hotdog.getIngredients().size() - hotdog.getIngredients().stream().filter(Ingredient::isRequired).count() - 1;
		double discount = relevantAmount * 0.1;

		// round to two decimals
		hotdog.setDiscount(Math.round(discount * 100.0) / 100.0);

		if (hotdog.getDiscount() > 0.5) {
			hotdog.setDiscount(0.5);
		}
		else if (hotdog.getDiscount() < 0) {
			hotdog.setDiscount(0);
		}
	}

	/**
	 * Calculates the final price of the Hotdog by adding the regular prices of all required ingredients and all discounted ingredients.
	 */
	private void calculateDiscountedPrice()
	{
		double total = 0;
		double discount = 0;
		for (Ingredient ingredient : hotdog.getIngredients()) {
			if (!ingredient.isRequired()) {
				discount += ingredient.getPrice() * hotdog.getDiscount();
			}
		}
		hotdog.setDiscountedPrice(hotdog.getPrice() - discount);

		// round to two decimals
		hotdog.setDiscountEuro(Math.round(discount * 100.0) / 100.0);
	}

	/**
	 * Invalidates the current session so a new Hotdog can be created.
	 * Redirects to the checkout page, afterwards.
	 *
	 * @return the name of the checkout page
	 */
	public String buy()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
		httpSession.invalidate();
		return "checkout";
	}

}

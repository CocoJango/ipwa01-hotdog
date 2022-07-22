package hotdogs4all.ipwa01hotdog;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Feedback
{
	private String name;
	private String email;
	private String message;
	private String order;

	public Feedback()
	{
	}

	/**
	 * Currently only redirects to the empty feedback form. If feedback should be persisted, this is probably the right place.
	 *
	 * @return page to redirect to
	 */
	public String saveFeedback()
	{
		return "feedback.xhtml?faces-redirect=true&message=true";
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}
}

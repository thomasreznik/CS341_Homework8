package reznik;

public class Video {
	private String sku;
	private String title;
	private int price;
	private int quantity;

//Constructor
	public Video(String sku, String title, int price, int quantity) {
		this.sku = sku;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "[ Sku: " + sku + " Title: " + title + " Price: " + price + " Quantity: " + quantity + " ]" + "\n";
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
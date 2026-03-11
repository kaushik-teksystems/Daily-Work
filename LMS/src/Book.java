class Book {
    String title;
    boolean available;
    int price;
    
    Book(String title, int price) {
        this.title = title;
        this.available = true;
        if(price > 0) {
        	this.price = price;
        }else {
        	throw new IllegalArgumentException("Price cannot be negative.");
        }
    }
}
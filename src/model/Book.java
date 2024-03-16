package model;
import java.io.Serializable;
import javafx.beans.property.*;
import java.io.*;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
		private String title;
		private String author; 
		private String publications;
		private String category; 
		private String ISBN; 
		private int quantity;
		private double orgPrice;
		private double sellPrice;


		 
		Book(){
			
		}
		
		public Book(String title, String auther, String publications, String category, String iSBN, int quantity,
				double orgPrice, double sellPrice) {
			super();
			this.title = title;
			this.author = auther;
			this.publications = publications;
			this.category = category;
			ISBN = iSBN;
			this.quantity = quantity;
			this.orgPrice = orgPrice;
			this.sellPrice = sellPrice;
		}

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getAuther() {
			return author;
		}
		public void setAuther(String auther) {
			this.author = auther;
		}
		public String getPublications() {
			return publications;
		}
		public void setPublications(String publications) {
			this.publications = publications;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getISBN() {
			return ISBN;
		}
		public void setISBN(String iSBN) {
			ISBN = iSBN;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getOrgPrice() {
			return orgPrice;
		}
		public void setOrgPrice(double orgPrice) {
			this.orgPrice = orgPrice;
		}
		public double getSellPrice() {
			return sellPrice;
		}
		public void setSellPrice(double sellPrice) {
			this.sellPrice = sellPrice;
		}

		@Override
		public String toString() {
		    return "Book [title=" + title + ", author=" + author + ", publications=" + publications + ", category="
		            + category + ", ISBN=" + ISBN + ", quantity=" + quantity + ", orgPrice=" + orgPrice + ", sellPrice="
		            + sellPrice + "]";
		}
		private void writeObject(ObjectOutputStream outputStream) throws IOException {
		    try {
		        outputStream.defaultWriteObject();
		        outputStream.writeUTF(this.title);
		        outputStream.writeUTF(this.author);
		        outputStream.writeUTF(this.publications);
		        outputStream.writeUTF(this.category);
		        outputStream.writeUTF(this.ISBN);
		        outputStream.writeInt(this.quantity);
		        outputStream.writeDouble(this.orgPrice);
		        outputStream.writeDouble(this.sellPrice);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

		private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
		    try {
		        inputStream.defaultReadObject();
		        this.title = inputStream.readUTF();
		        this.author = inputStream.readUTF();
		        this.publications = inputStream.readUTF();
		        this.category = inputStream.readUTF();
		        this.ISBN = inputStream.readUTF();
		        this.quantity = inputStream.readInt();
		        this.orgPrice = inputStream.readDouble();
		        this.sellPrice = inputStream.readDouble();
		    } catch (IOException | ClassNotFoundException e) {
		        e.printStackTrace();
		    }
		}

}

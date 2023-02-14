/**
 * @author Chance - cbenna
 * CIS175 - Spring 2023
 * Feb 13, 2023
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Chance
 *
 */
@Entity
@Table(name="items")
public class bookItem {
	@Id
	@GeneratedValue
	@Column(name="TITLE")
	private String title;
	@Column(name="AUTHOR")
	private String author;
	@Column(name="ID")
	private int id;
	
	public bookItem() {
		super();
	}
	
	public bookItem(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public void setAuthor(String newAuthor) {
		this.author = newAuthor;
	}
	
	public void setId(int newID) {
		this.id = newID;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String returnItemDetails() {
		return this.title + ": " + this.author;
	}
}

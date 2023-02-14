/**
 * @author Chance - cbenna
 * CIS175 - Spring 2023
 * Feb 13, 2023
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.bookItem;

/**
 * @author Chance
 *
 */
public class bookHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("bookList");
	
	public void insertItem(bookItem li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<bookItem> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<bookItem> allItems = em.createQuery("SELECT i FROM bookItem i").getResultList();
		return allItems;
	}
	
	public void deleteItem(bookItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<bookItem> typedQuery = em.createQuery("select li from bookItem li where li.title = :selectedTitle and li.author = :selectedAuthor", bookItem.class);
		
		//substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedAuthor", toDelete.getAuthor());
		
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		bookItem result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param priceToEdit
	 * @return
	 */
	public bookItem searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		bookItem found = em.find(bookItem.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param toEdit
	 */
	public void updateTitle(bookItem toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param authorName
	 * @return
	 */
	public List<bookItem> searchForItemByAuthor(String authorName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<bookItem> typedQuery = em.createQuery("select li from bookItem li where li.author = :selectedAuthor", bookItem.class);
		typedQuery.setParameter("selectedAuthor", authorName);
		
		List<bookItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	/**
	 * @param titleName
	 * @return
	 */
	public List<bookItem> searchForItemByTitle(String titleName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<bookItem> typedQuery = em.createQuery("select li from bookItem li where li.title = :selectedTitle", bookItem.class);
		typedQuery.setParameter("selectedTitle", titleName);
		
		List<bookItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}

}

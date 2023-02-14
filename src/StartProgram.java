

import java.util.List;
import java.util.Scanner;

import controller.bookHelper;
import model.bookItem;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static bookHelper lih = new bookHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter a title: ");
			String title = in.nextLine();
			System.out.print("Enter an author: ");
			String author = in.nextLine();
			
			bookItem toAdd = new bookItem(title, author);
			lih.insertItem(toAdd);

		}

		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the title to delete: ");
			String title = in.nextLine();
			System.out.print("Enter the author to delete: ");
			String author = in.nextLine();
			
			bookItem toDelete = new bookItem(title, author);
			lih.deleteItem(toDelete);

		}

		private static void editAnItem() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Author");
			System.out.println("2 : Search by Title");
			int searchBy = in.nextInt();
			in.nextLine();
			List<bookItem> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the Author name: ");
				String authorName = in.nextLine();
				foundItems = lih.searchForItemByAuthor(authorName);
				
			} else {
				System.out.print("Enter the Title: ");
				String titleName = in.nextLine();
				foundItems = lih.searchForItemByTitle(titleName);
				

			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (bookItem l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				bookItem toEdit = lih.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getTitle() + " from " + toEdit.getAuthor());
				System.out.println("1 : Update Author");
				System.out.println("2 : Update Title");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Author: ");
					String newAuthor = in.nextLine();
					toEdit.setAuthor(newAuthor);
				} else if (update == 2) {
					System.out.print("New Title: ");
					String newTitle = in.nextLine();
					toEdit.setTitle(newTitle);
				}

				lih.updateTitle(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our awesome shopping list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an item");
				System.out.println("*  2 -- Edit an item");
				System.out.println("*  3 -- Delete an item");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<bookItem> allItems = lih.showAllItems();
			for(bookItem singleItem : allItems) {
				System.out.println(singleItem.returnItemDetails());
			}

		}

	}

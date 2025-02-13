package donezo.lists;

import java.util.ArrayList;

/**
 * Represents a generic list that holds items of type T.
 * Provides common functionality for adding, removing, and retrieving items.
 * It also provides a generic implementation for finding matching items based on a search term.
 *
 * @param <T> the type of items in the list.
 */
public abstract class ItemList<T> {
    private ArrayList<T> items;

    public ItemList() {
        this.items = new ArrayList<>();
    }

    public ItemList(ArrayList<T> items) {
        this.items = items;
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public int getSizeItemList() {
        return items.size();
    }

    public T getItem(int ndx) {
        return items.get(ndx);
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void removeItem(int ndx) {
        items.remove(ndx);
    }

    public boolean isItemListEmpty() {
        return items.isEmpty();
    }

    /**
     * Finds and returns a new list containing all items whose searchable field
     * (as defined by {@link #getSearchField(T)}) contains the given search term (case-insensitive).
     *
     * @param searchTerm the term to search for.
     * @return a new ItemList containing matching items.
     */
    public ItemList<T> findMatchingItems(String searchTerm) {
        ItemList<T> matchingItems = createEmptyList();
        for (T item : items) {
            if (getSearchField(item).toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingItems.addItem(item);
            }
        }
        return matchingItems;
    }

    /**
     * Returns the text used for searching for a given item.
     * Subclasses must implement this to specify which field of T should be compared.
     *
     * @param item the item to get searchable text from.
     * @return a String representation of the searchable field.
     */
    protected abstract String getSearchField(T item);

    /**
     * Creates and returns an empty instance of the subclass.
     * This method is used by {@link #findMatchingItems(String)} to generate a new list
     * of the correct type.
     *
     * @return an empty ItemList of the appropriate type.
     */
    protected abstract ItemList<T> createEmptyList();
}

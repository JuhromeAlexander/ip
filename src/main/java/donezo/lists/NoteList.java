package donezo.lists;

import java.util.ArrayList;

import donezo.notes.Note;

public class NoteList extends ItemList<Note> {
    public NoteList(ArrayList<Note> notes) {
        super(notes);
    }

    public NoteList() {
        super();
    }

    @Override
    protected String getSearchField(Note item) {
        return item.getTitle();
    }

    @Override
    protected ItemList<Note> createEmptyList() {
        return new NoteList();
    }

    public ArrayList<Note> getNotes() {
        return getItems();
    }

    public int getSizeNoteList() {
        return getSizeItemList();
    }

    public Note getNote(int ndx) {
        return getItem(ndx);
    }

    public void addNote(Note note) {
        addItem(note);
    }

    public void removeNote(int ndx) {
        removeItem(ndx);
    }

    public boolean isEmpty() {
        return isItemListEmpty();
    }

    /**
     * Searches for notes in the current note list that contain a specific search term
     * (case-insensitive) in their description and returns a new NoteList containing
     * the matching notes.
     *
     * @param searchTerm The term to search for within the note titles.
     *                   This search is case-insensitive, meaning "Note" and "note"
     *                   would be treated as equivalent.
     * @return A NoteList containing all notes that match the specified search term.
     *         If no notes match, an empty TaskList is returned.
     */
    public NoteList findMatchingNotes(String searchTerm) {
        return (NoteList) findMatchingItems(searchTerm);
    }

}

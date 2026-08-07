package Backend;

/**
 * Holds the menu's options along with their associated values.
 */
public enum Option {
    ADD("Add contact"),
    DELETE("Delete contact"),
    EDIT("Edit contact"),
    SEARCH("Search by name"),
    IMPORT("Import data"),
    EXPORT("Export Data");

    private final String label;

    /**
     * Creates the label and option's value for the <code>Option</code> <code>enum</code>.
     * @param label The option's label.
     */
    Option(String label) {
        this.label = label;
    }

    /**
     * Gets the label for the option.
     * @return A <code>String</code> representation of the label.
     */
    public String getLabel() {
        return label;
    }
}

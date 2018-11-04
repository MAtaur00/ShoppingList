package info.pauek.shoppinglist;

public class ShoppingItem {
    private String name;

    //todo1: add boolean to know if box is checked
    private boolean checked;

    //todo1: getters and setters for boolean
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ShoppingItem(String name, boolean checked) {

        this.name = name;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

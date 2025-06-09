package framework.utils.enums;

public enum SortingOption {
    // Values are the display text of each option
    NAME_A_TO_Z("Name (A to Z)"),
    NAME_Z_TO_A("Name (Z to A)"),
    PRICE_LOW_TO_HIGH("Price (low to high)"),
    PRICE_HIGH_TO_LOW("Price (high to low)");

    private final String value;

    SortingOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

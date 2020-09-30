package animals;

public enum Species {
    CAT("Cat"),
    DOG("Dog");

    private final String description;

    Species(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

package HomeWork1;

public class ImmutableWrapper {
    private final String id;
    private final MutablePerson person;

    public ImmutableWrapper(String id, MutablePerson person) {
        this.id = id;
        this.person = person.copy();
    }

    public String getId() {
        return id;
    }

    public MutablePerson getPerson() {
        return person.copy();
    }

    @Override
    public String toString() {
        return "ImmutableWrapper{id='" + id + "', person=" + person + "}";
    }
}

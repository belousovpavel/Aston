package HomeWork1;

public final class ImmutableWrapper {
    private final String id;
    private final MutablePerson person;

    public ImmutableWrapper(String id, MutablePerson person) {

        if (id == null) {
            throw new IllegalArgumentException("id не может быть null");
        }
        if (person == null) {
            throw new IllegalArgumentException("person не может быть null");
        }

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

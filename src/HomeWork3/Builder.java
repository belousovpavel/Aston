package HomeWork3;

class User {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String phone;
    private final String email;

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    @Override
    public String toString() {
        return "User{" + firstName + " " + lastName + ", age=" + age + ", phone=" + phone + ", email=" + email + "}";
    }

    public static class Builder {
        private final String firstName;
        private final String lastName;
        private int age = 0;
        private String phone = "";
        private String email = "";

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder age(int age) { this.age = age; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder email(String email) { this.email = email; return this; }

        public User build() { return new User(this); }
    }
}

public class Builder {
    public static void main(String[] args) {
        User user = new User.Builder("John", "Doe")
                .age(30)
                .phone("+123456789")
                .email("john@example.com")
                .build();
        System.out.println(user);
    }
}
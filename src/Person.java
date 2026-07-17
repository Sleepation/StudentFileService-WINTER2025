public abstract class Person {
    private int id;
    private String name;
    private char gender;

    /**
     * This constructor creates a Person object with the id, name, and gender
     *
     * @param id     is the id of the Person (int)
     * @param name   is the name of the Person (String)
     * @param gender is the gender of the Person (char)
     */
    Person(int id, String name, char gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    //Getters (There are no setters since this data field should not change. It is determined from birth)

    /**
     * This method returns the id
     *
     * @return integer representing the id of the Person
     */
    public int getId() {
        return id;
    }

    /**
     * This method gets the name of the Person
     *
     * @return String representing the name of the Person
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the gender of the Person
     *
     * @return character representing the gender of the Person
     */
    public char getGender() {
        return gender;
    }
}

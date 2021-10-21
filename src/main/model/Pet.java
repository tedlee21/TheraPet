package model;

//represents user's pet with a name and a type
public class Pet {
    private String name; //name of pet
    private PetType type; //type of pet

    /* REQUIRES: petName must have non-zero length
     * EFFECTS : pets name is set to petName, pets type is set to
     *           petType
     */
    public Pet(String petName, PetType petType) {
        name = petName;
        type = petType;
    }

    public String getPetName() {
        return name;
    }

    public PetType getPetType() {
        return type;
    }

}

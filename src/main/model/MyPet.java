package model;

//represents user's pet with a name and a type
public class MyPet {
    private String name; //name of pet
    private String type; //type of pet

    /* REQUIRES: petName must have non-zero length
     * EFFECTS : pets name is set to petName, pets type is set to
     * petType
     */
    public MyPet(String petName, String petType) {
        name = petName;
        type = petType;
    }

    public String getPetName() {
        return name;
    }

    public String getPetType() {
        return type;
    }





    // delete or rename this class!
}

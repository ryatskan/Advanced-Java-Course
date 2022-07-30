package q1;

// Owner Object that has a name and a phone number.

public class Owner {
    // Parameters
    private String ownerName;
    private String ownerPhoneNum;

    // Constructor
    public Owner(String givenName, String givenPhoneNum) {
        ownerName = givenName;
        ownerPhoneNum = givenPhoneNum;
    }

    // Setters
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOwnerPhoneNum(String ownerPhoneNum) {
        this.ownerPhoneNum = ownerPhoneNum;
    }

    // Getters
    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerPhoneNum() {
        return ownerPhoneNum;
    }

    @Override
    public String toString() {
        return "owner name: " + this.ownerName + ";" + " owner phone number: " + this.ownerPhoneNum + ";";
    }
}

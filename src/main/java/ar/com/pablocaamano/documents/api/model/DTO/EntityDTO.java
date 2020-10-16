package ar.com.pablocaamano.documents.api.model.DTO;

public class EntityDTO {

    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "{'name': '" + name + "'," +
                "'description': '" + description + "'," +
                "'address': '" + address + "'," +
                "'phone': '" + phone + "'," +
                "'email': '" + email + "'," +
                "'website': '" + website +"'}";
    }

}

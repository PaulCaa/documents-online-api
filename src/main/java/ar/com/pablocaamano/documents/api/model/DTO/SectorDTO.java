package ar.com.pablocaamano.documents.api.model.DTO;

public class SectorDTO {

    private String name;
    private EntityDTO entity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityDTO getEntity() {
        return entity;
    }

    public void setEntity(EntityDTO entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "{'name': '" + name + "'," +
                "'entity': '" + entity.toString() + "'}";
    }
}

package ar.com.pablocaamano.documents.api.model;

import ar.com.pablocaamano.documents.api.model.DTO.EntityDTO;
import ar.com.pablocaamano.documents.api.model.DTO.FieldDTO;
import ar.com.pablocaamano.documents.api.model.DTO.SectorDTO;

import java.util.List;

public class Document {

    private int id;
    private String name;
    private SectorDTO sector;
    private EntityDTO entity;
    private List<FieldDTO> fields;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SectorDTO getSector() {
        return sector;
    }

    public void setSector(SectorDTO sector) {
        this.sector = sector;
    }

    public EntityDTO getEntity() {
        return entity;
    }

    public void setEntity(EntityDTO entity) {
        this.entity = entity;
    }

    public List<FieldDTO> getFields() {
        return fields;
    }

    public void setFields(List<FieldDTO> fields) {
        this.fields = fields;
    }

    @Override
    public String toString(){
        return "{'id': '" + id + "'," +
                "'name': '" + name + "'," +
                "'sector': '" + sector.toString() + "'," +
                "'entity': '" + entity.toString() + "'}";
    }
}

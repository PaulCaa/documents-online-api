package ar.com.pablocaamano.documents.api.service;

import ar.com.pablocaamano.documents.api.model.Document;

public interface DocumentsService {

    Document findDocument(String transaction, int id);
}

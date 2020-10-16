package ar.com.pablocaamano.documents.api.service.impl;

import ar.com.pablocaamano.documents.api.model.Document;
import ar.com.pablocaamano.documents.api.service.DocumentsService;
import org.springframework.stereotype.Service;

@Service
public class DocumentsServiceImpl implements DocumentsService {

    @Override
    public Document findDocument(String transaction, int id) {
        // TODO implement
        return new Document();
    }
}

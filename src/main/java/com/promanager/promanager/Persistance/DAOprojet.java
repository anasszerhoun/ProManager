package com.promanager.promanager.Persistance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.promanager.promanager.Metier.POJO.Projet;

public class DAOprojet {

    Connexion connexion;

    public DAOprojet(){
        connexion = Connexion.getInstance("ProManagerDB", "mongodb://localhost:27017/");
    }

    public ArrayList<Projet> getAll() {
        ArrayList<Projet> Projects = new ArrayList<>();
        FindIterable<Document> documents = connexion.selectAll("Projets");
        Projet projet;
        for (Document document : documents) {
            projet = new Projet();
            projet.setNomProjet(document.getString("Nom"));
            projet.setCategorieProjet(document.getString("Categorie"));
            projet.setDateDepartProjet(document.getDate("DateDebut"));
            projet.setDateFinProjet(document.getDate("DateFin"));
            projet.setDescriptionProjet(document.getString("Description"));
            projet.setIdProjet(document.getObjectId("_id"));
            projet.setTypeProjet(document.getString("Type"));
            projet.setStatus(document.getString("Status"));
            projet.setListeTaches((ArrayList<ObjectId>) document.getList("Taches", ObjectId.class));
            projet.setListeSeances((ArrayList<ObjectId>) document.getList("Seances", ObjectId.class));
            projet.setListeDocument((ArrayList<ObjectId>) document.getList("Documents", ObjectId.class));
            Projects.add(projet);
        }
        return Projects;
    }

    public Projet get(Integer index) {
        return this.getAll().get(index);
    }

    public Projet get(ObjectId id) {
        Document document = connexion.select(id, "Projets");
        Projet projet = new Projet();
        projet.setNomProjet(document.getString("Nom"));
        projet.setCategorieProjet(document.getString("Categorie"));
        projet.setDateDepartProjet(document.getDate("DateDebut"));
        projet.setDateFinProjet(document.getDate("DateFin"));
        projet.setDescriptionProjet(document.getString("Description"));
        projet.setIdProjet(document.getObjectId("_id"));
        projet.setTypeProjet(document.getString("Type"));
        projet.setStatus(document.getString("Status"));
        projet.setListeTaches((ArrayList<ObjectId>) document.getList("Taches", ObjectId.class));
        projet.setListeSeances((ArrayList<ObjectId>) document.getList("Seances", ObjectId.class));
        projet.setListeDocument((ArrayList<ObjectId>) document.getList("Documents", ObjectId.class));
        return projet;
    }

    public void add(String nomProjet, String categorie, String type, String description, Date debut, Date fin) {
        Projet projet = new Projet(nomProjet, categorie, type, description, debut, fin);
        HashMap<String, Object> InfoProjet = new HashMap<>();
        InfoProjet.put("Nom", projet.getNomProjet());
        InfoProjet.put("Categorie", projet.getCategorieProjet());
        InfoProjet.put("Type", projet.getTypeProjet());
        InfoProjet.put("Description", projet.getDescriptionProjet());
        InfoProjet.put("DateDebut", projet.getDateDepartProjet());
        InfoProjet.put("DateFin", projet.getDateFinProjet());
        InfoProjet.put("Taches", new ArrayList<>());
        InfoProjet.put("Seances", new ArrayList<>());
        InfoProjet.put("Documents", new ArrayList<>());
        InfoProjet.put("Status", "Ouvert");
        connexion.insert(InfoProjet, "Projets");
    }

    public void update(ObjectId id, String nomProjet, String categorie, String type, String description, Date debut,
            Date fin,
            ArrayList<ObjectId> Taches,
            ArrayList<ObjectId> Seances,
            ArrayList<ObjectId> Documents) {
        Projet projet = new Projet(nomProjet, categorie, type, description, debut, fin);
        HashMap<String, Object> InfoProjet = new HashMap<>();
        InfoProjet.put("Nom", projet.getNomProjet());
        InfoProjet.put("Categorie", projet.getCategorieProjet());
        InfoProjet.put("Type", projet.getTypeProjet());
        InfoProjet.put("Description", projet.getDescriptionProjet());
        InfoProjet.put("DateDebut", projet.getDateDepartProjet());
        InfoProjet.put("DateFin", projet.getDateFinProjet());
        InfoProjet.put("Taches", Taches);
        InfoProjet.put("Seances", Seances);
        InfoProjet.put("Documents", Documents);
        InfoProjet.put("Status", "Ouvert");
        connexion.update(id, InfoProjet, "Projets");
    }

    public void Cloner(ObjectId id) {
        connexion.Duplicate(id, "Projets");
    }

    public void delete(ObjectId id, String key) {
        connexion.remove(id, key, "Projets");
    }

    public void delete(ObjectId id) {
        connexion.remove(id, "Projets");
    }

    public void update(ObjectId id, String key, Object value) {
        connexion.update(id, key, value, "Projets");
    }

    public void update(ObjectId id, String key, List<ObjectId> value) {
        connexion.update(id, key, value, "Projets");
    }

    public void update(ObjectId id, HashMap<String, Object> Objects) {
        connexion.update(id, Objects, "Projets");
    }

}
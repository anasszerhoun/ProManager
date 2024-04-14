package com.promanager.promanager.Presentation.View.HistoriqueView.Taches;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.promanager.promanager.Presentation.Controller.HistoriqueController.Taches.TachesProjetHistoriqueController;
import org.bson.types.ObjectId;

import com.promanager.promanager.Metier.Gestion.gestionProjet;
import com.promanager.promanager.Metier.POJO.Projet;
import com.promanager.promanager.Metier.POJO.Tache;
import com.promanager.promanager.Persistance.DAOtache;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class TachesProjetHistorique extends AnchorPane {
    private ObjectId idProjet;
    private Text nomProjet;
    private Text categorie;
    private Text type;
    private Text dateDepart;
    private Text dateFin;
    private Button PrecedentButton;
    private TachesProjetHistoriqueController controller;
    private gestionProjet gProjet;
    private Projet Projet;
    private DAOtache gTaches;
    private Stage stage;
    private Label textTaches;
    private ScrollPane scrollPane;
    private VBox tacheListe;
    private ArrayList<ObjectId> idsTaches;
    private String elemTache;
    private Tache tache;

    public TachesProjetHistorique(ObjectId id, Stage stage) {
        this.idProjet = id;
        nomProjet = new Text("Nom Projet");
        categorie = new Text("Categorie");
        type = new Text("Type");
        dateDepart = new Text("Date Depart");
        dateFin = new Text("Date Fin");
        PrecedentButton = new Button("Precedent");
        gProjet = new gestionProjet();
        gTaches = new DAOtache();
        tache = new Tache();
        Projet = new Projet();
        textTaches = new Label("~ Liste Taches :");
        idsTaches = new ArrayList<>();
        elemTache = new String();
        this.stage = stage;
        this.controller = new TachesProjetHistoriqueController(this, stage, idProjet);
        design();
    }

    public ObjectId getIdProjet() {
        return idProjet;
    }

    public Text getNomProjet() {
        return nomProjet;
    }

    public Text getCategorie() {
        return categorie;
    }

    public Text getType() {
        return type;
    }

    public Text getDateDepart() {
        return dateDepart;
    }

    public Text getDateFin() {
        return dateFin;
    }

    public Button getPrecedentButton() {
        return PrecedentButton;
    }


    private void design() {
        nomProjet.setFill(javafx.scene.paint.Color.valueOf("#6a82ab"));
        nomProjet.setLayoutX(50.0);
        nomProjet.setLayoutY(90.0);
        nomProjet.setFont(Font.font("Arial", FontWeight.BOLD, 30.0));

        textTaches.setStyle(" -fx-text-fill: #6a82ab;");
        textTaches.setLayoutX(50.0);
        textTaches.setLayoutY(350.0);
        textTaches.setFont(Font.font("Arial", FontWeight.BOLD, 30.0));

        categorie.setLayoutX(50.0);
        categorie.setLayoutY(140.0);
        categorie.setFont(new Font(20.0));

        type.setLayoutX(50.0);
        type.setLayoutY(190.0);
        type.setFont(new Font(20.0));

        dateDepart.setLayoutX(50.0);
        dateDepart.setLayoutY(240.0);
        dateDepart.setFont(new Font(20.0));

        dateFin.setLayoutX(50.0);
        dateFin.setLayoutY(290.0);
        dateFin.setFont(new Font(20.0));

        scrollPane = new ScrollPane();
        scrollPane.setLayoutX(50.0);
        scrollPane.setLayoutY(420.0);
        scrollPane.setPrefWidth(1230);
        scrollPane.setPrefHeight(350);
        scrollPane.setStyle(" -fx-selection-bar: #6a82ab;fx-border-color: transparent;-fx-background-color: inherit;");
        tacheListe = new VBox(10);

        PrecedentButton.setLayoutX(1100.0);
        PrecedentButton.setLayoutY(50.0);
        PrecedentButton.setPrefWidth(150.0);
        PrecedentButton.setPrefHeight(40.0);
        PrecedentButton.setFont(new Font(18.0));
        PrecedentButton.setStyle("-fx-background-color: #6a82ab; -fx-text-fill: white;");
        PrecedentButton.setFont(Font.font("Arial", FontWeight.BOLD, 18.0));


        Projet = gProjet.get(idProjet);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        nomProjet.setText("Nom Projet : " + Projet.getNomProjet());
        categorie.setText("Categorie : " + Projet.getCategorieProjet());
        type.setText("Type : " + Projet.getTypeProjet());
        dateDepart.setText("Date Depart : " + sdf.format(Projet.getDateDepartProjet()));
        dateFin.setText("Date Fin : " + sdf.format(Projet.getDateFinProjet()));

        idsTaches = Projet.getListeTaches();

        for (ObjectId idTache : idsTaches) {
            tache = gTaches.get(idTache);
            elemTache = "Categorie : " + tache.getCategorieTache() + " - Date Depart : "
                    + sdf.format(tache.getDateDepartTache()) + " - Date Fin : " + sdf.format(tache.getDateFinTache());

            Label LabelTache = new Label(elemTache);
            HBox hbox = new HBox();

            LabelTache.setFont(Font.font(25));
            LabelTache.setPrefHeight(60);
            LabelTache.setPrefWidth(900);
            LabelTache.setStyle(
                    "-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #6a82ab;-fx-opacity:0.5;-fx-text-fill: #FFF;-fx-padding: 20px;-fx-background-radius:20px;-fx-border-radius:20px;");


            hbox.setSpacing(20);

            LabelTache.setOnMouseClicked(event -> {
                controller.openTache(idTache, idProjet);
            });



            hbox.getChildren().addAll(LabelTache);
            tacheListe.getChildren().add(hbox);
        }

        scrollPane.setContent(tacheListe);

        getChildren().addAll(nomProjet, categorie, type, dateDepart, dateFin, PrecedentButton, scrollPane, textTaches);
    }
}

package com.promanager.promanager.Presentation.View.ListesVIiew;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import com.promanager.promanager.Metier.Gestion.gestionListe;
import com.promanager.promanager.Metier.Gestion.gestionProjet;
import com.promanager.promanager.Metier.Gestion.gestionTache;
import com.promanager.promanager.Metier.POJO.Liste;
import com.promanager.promanager.Metier.POJO.Tache;
import com.promanager.promanager.Persistance.DAOconfiguration;
import com.promanager.promanager.Presentation.Controller.ListesController.ListesPageController;
import com.promanager.promanager.Presentation.Controller.ProjetController.ProjetsPageController;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class ListesPage extends AnchorPane {
    private Pane sideBar;
    private Button Projets;
    private Button Listes;
    private Button Historiques;
    private Button Statistiques;
    private Text ListeText;
    private ComboBox<String> CategorieFilter;
    private TextField rechercheInput;
    private Button rechercheButton;
    private Button buttonAjouter;
    private Stage stage;
    private ListesPageController controller;
    private ReadOnlyDoubleProperty heightWindow;
    private ReadOnlyDoubleProperty widthWindow;
    private DAOconfiguration config;
    private gestionListe gListe;
    private gestionTache gTache;
    private VBox mainVBox;

    private ScrollPane scrollPane;

    public ListesPage(Stage stage) {
        this.stage = stage;
        this.sideBar = new Pane();
        this.Projets = new Button("Projets");
        this.Listes = new Button("Listes");
        this.Historiques = new Button("Historiques");
        this.Statistiques = new Button("Statistiques");
        this.ListeText = new Text("Listes");
        this.CategorieFilter = new ComboBox<>();
        this.rechercheInput = new TextField();
        this.rechercheButton = new Button("Rechercher");
        this.buttonAjouter = new Button("Ajouter Tache");
        config = new DAOconfiguration();
        this.heightWindow = stage.heightProperty();
        scrollPane = new ScrollPane();
        gListe = new gestionListe();
        gTache = new gestionTache();
        mainVBox = new VBox();
        this.widthWindow = stage.widthProperty();
        this.controller = new ListesPageController(this, stage);
        design();
        affichage();
    }

    public Button getProjets() {
        return Projets;
    }

    public Button getListes() {
        return Listes;
    }

    public Button getHistoriques() {
        return Historiques;
    }

    public Button getStatistiques() {
        return Statistiques;
    }

    public Text getListeText() {
        return ListeText;
    }

    public ComboBox<String> getCategorieFilter() {
        return CategorieFilter;
    }

   

    public TextField getRechercheInput() {
        return rechercheInput;
    }

    public Button getRechercheButton() {
        return rechercheButton;
    }

    public Button getButtonAjouter() {
        return buttonAjouter;
    }

    public ReadOnlyDoubleProperty heightWindow() {
        return heightWindow;
    }

    public ReadOnlyDoubleProperty widthWindow() {
        return widthWindow;
    }

    private void design() {
        this.sideBar.setLayoutX(-29.0);
        this.sideBar.setLayoutY(0);
        this.sideBar.setPrefHeight(805.0);
        this.sideBar.setPrefWidth(240.0);
        this.sideBar.prefHeightProperty().bind(heightWindow().subtract(25));
        this.sideBar.setStyle(
                "-fx-background-color: #6a82ab; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: black; -fx-opacity:0.8;");

        this.Projets.setLayoutX(0.0);
        this.Projets.setLayoutY(30.0);
        this.Projets.setPrefHeight(70.0);
        this.Projets.setPrefWidth(210.0);
        this.Projets.setStyle("-fx-background-color: transparent; ");
        this.Projets.setTextFill(javafx.scene.paint.Color.WHITE);
        this.Projets.setFont(Font.font("Arial Bold", 31.0));

        this.Listes.setLayoutX(0.0);
        this.Listes.setLayoutY(100.0);
        this.Listes.setPrefHeight(70.0);
        this.Listes.setPrefWidth(210.0);
        this.Listes.setStyle("-fx-background-color: #4a628a; ");
        this.Listes.setTextFill(javafx.scene.paint.Color.WHITE);
        this.Listes.setFont(Font.font("Arial Bold", 31.0));

        this.Historiques.setLayoutX(0.0);
        this.Historiques.setLayoutY(170.0);
        this.Historiques.setPrefHeight(70.0);
        this.Historiques.setPrefWidth(210.0);
        this.Historiques.setStyle("-fx-background-color: transparent; ");
        this.Historiques.setTextFill(javafx.scene.paint.Color.WHITE);
        this.Historiques.setFont(Font.font("Arial Bold", 31.0));

        this.Statistiques.setLayoutX(0.0);
        this.Statistiques.setLayoutY(240.0);
        this.Statistiques.setPrefHeight(70.0);
        this.Statistiques.setPrefWidth(210.0);
        this.Statistiques.setStyle("-fx-background-color: transparent; ");
        this.Statistiques.setTextFill(javafx.scene.paint.Color.WHITE);
        this.Statistiques.setFont(Font.font("Arial Bold", 31.0));

        this.Statistiques.setOnMouseEntered(event -> {
            this.Statistiques.setStyle(
                    "-fx-background-color: #6a82ab; ");
        });
        this.Statistiques.setOnMouseExited(event -> {
            this.Statistiques.setStyle(
                    "-fx-background-color: transparent; ");
        });
        this.Historiques.setOnMouseEntered(event -> {
            this.Historiques.setStyle(
                    "-fx-background-color: #6a82ab; ");
        });
        this.Historiques.setOnMouseExited(event -> {
            this.Historiques.setStyle(
                    "-fx-background-color: transparent; ");
        });
        this.Listes.setOnMouseEntered(event -> {
            this.Listes.setStyle(
                    "-fx-background-color: #6a82ab; ");
        });
        this.Listes.setOnMouseExited(event -> {
            this.Listes.setStyle(
                    "-fx-background-color: transparent; ");
        });

        this.ListeText.setLayoutX(240.0);
        this.ListeText.setLayoutY(96.0);
        this.ListeText.setFill(javafx.scene.paint.Color.web("#6a82ab"));
        this.ListeText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        this.ListeText.setStrokeWidth(0.0);
        this.ListeText.setFont(Font.font("System Bold", FontWeight.BOLD, 44.0));
        this.ListeText.setWrappingWidth(188.78101640354225);

        this.CategorieFilter.setLayoutX(406.0);
        this.CategorieFilter.setLayoutY(72.0);
        this.CategorieFilter.setPrefHeight(26.0);
        this.CategorieFilter.setPrefWidth(130.0);
        this.CategorieFilter.setPromptText("Categorie");
        this.CategorieFilter.setStyle("-fx-background-color: #6a82abcc;");


        this.rechercheInput.setPrefWidth(100);
        this.rechercheInput.setPrefWidth(150.0);
        this.rechercheInput.setLayoutY(72.0);
        this.rechercheInput.setLayoutX(886);
        this.rechercheInput.setStyle("-fx-border-color: #6a82ab; -fx-border-radius: 5; -fx-background-radius: 5;");

        this.rechercheButton.setLayoutX(1050);
        this.rechercheButton.setLayoutY(72.0);
        this.rechercheButton.setStyle("-fx-background-color: #6a82ab;");
        this.rechercheButton.setTextFill(javafx.scene.paint.Color.WHITE);

        this.buttonAjouter.setLayoutX(1132.0);
        this.buttonAjouter.setLayoutY(72.0);
        this.buttonAjouter.setMnemonicParsing(false);

        this.buttonAjouter.setPrefHeight(26.0);
        this.buttonAjouter.setPrefWidth(125.0);
        this.buttonAjouter.setStyle("-fx-background-color: #6a82ab;");
        this.buttonAjouter.setTextFill(javafx.scene.paint.Color.WHITE);
        this.buttonAjouter.setFont(Font.font("System", 13.0));
        this.buttonAjouter.setDisable(false);

        config.getCategorie();
        CategorieFilter.getItems().add("tout");
        CategorieFilter.getItems().addAll(config.getCategorie());


        scrollPane.setLayoutX(280.0);
        scrollPane.setLayoutY(180.0);
        scrollPane.setPrefWidth(1100);
        scrollPane.setPrefHeight(620);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle(
                "-fx-selection-bar: #6a82ab; -fx-background-color: transparent; -fx-border-color: transparent;");

        CategorieFilter.setOnAction(event -> {
            String cat = CategorieFilter.getValue();
            affichage();
        });

        this.getChildren().addAll(sideBar, Projets, Listes, Historiques, Statistiques, ListeText, CategorieFilter,
                scrollPane,
                
                rechercheInput, rechercheButton, buttonAjouter);
    }

    private void affichage() {
        mainVBox.getChildren().clear();

        String cat = CategorieFilter.getValue();

        for (Liste liste : gListe.getAll()) {
            VBox listeVBox = new VBox(20);

            Text nomListe = new Text(" ~ Liste: " + liste.getNomListe());
            nomListe.setFont(Font.font("Arial", FontWeight.BOLD, 28));
            nomListe.setFill(Color.web("#6a82ab"));

            Text descListeText = new Text("Description: " + liste.getDescriptionListe());
            descListeText.setFont(Font.font(20));
            descListeText.setFill(Color.BLACK);

            VBox tachesVBox = new VBox();
            tachesVBox.setSpacing(5);
            tachesVBox.setStyle("-fx-padding: 0 0 0 50px;");

            boolean isCatExists = false;

            for (ObjectId idTache : liste.getListeTache()) {
                Tache tache = gTache.get_Tache(idTache);
                if (tache != null && (cat == null || cat.equals("tout")
                        || cat.equals(tache.getCategorieTache()))) {

                    Label tache_ = new Label(
                            "Categorie : " + tache.getCategorieTache() + " - Description : "
                                    + tache.getDescriptionTache());
                    tache_.setFont(Font.font(25));
                    tache_.setPrefHeight(60);
                    tache_.setPrefWidth(800);
                    tache_.setLayoutX(100);
                    tache_.setStyle(
                            "-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #6a82ab;-fx-opacity:0.5;-fx-text-fill: #FFF;-fx-padding: 20px;-fx-background-radius:20px;-fx-border-radius:20px;");

                    tachesVBox.getChildren().add(tache_);
                    tache_.setOnMouseClicked(event ->{
                        controller.afficherTache(tache.getIdTache());
                    });

                    isCatExists = true;
                }
            }

            if (cat == null || cat.equals("tout") || isCatExists) {
                listeVBox.getChildren().addAll(nomListe, tachesVBox);
                mainVBox.getChildren().add(listeVBox);
            }
        }

        scrollPane.setContent(mainVBox);
    }

}
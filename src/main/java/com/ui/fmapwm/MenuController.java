package com.ui.fmapwm;

import gamelogic.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private ChoiceBox choiceBox1Player1;

    @FXML
    private TextField player1Signature1;

    @FXML
    private ChoiceBox choiceBox2Player1;

    @FXML
    private TextField player1Signature2;

    @FXML
    private ChoiceBox choiceBox3Player1;

    @FXML
    private TextField player1Signature3;

    @FXML
    private ChoiceBox choiceBox1Player2;

    @FXML
    private TextField player2Signature1;

    @FXML
    private ChoiceBox choiceBox2Player2;

    @FXML
    private TextField player2Signature2;

    @FXML
    private ChoiceBox choiceBox3Player2;

    @FXML
    private TextField player2Signature3;
    @FXML
    private TextField player1NameTextField;

    @FXML
    private TextField player1DecksizeTextField;

    @FXML
    private TextField player1CountryTextField;

    @FXML
    private TextField player2NameTextField;

    @FXML
    private TextField player2DecksizeTextField;

    @FXML
    private TextField player2CountryTextField;

    @FXML
    private TextField player1FormationBonus;

    @FXML
    private TextField player1TrainingBonus;

    @FXML
    private TextField player2FormationBonus;

    @FXML
    private TextField player2TrainingBonus;

    @FXML
    protected void startGame(ActionEvent event) throws IOException {
        String player1Name = player1NameTextField.getText();
        int player1Decksize = Integer.parseInt(player1DecksizeTextField.getText());
        String player1Country = player1CountryTextField.getText();

        String player2Name = player2NameTextField.getText();
        int player2Decksize = Integer.parseInt(player2DecksizeTextField.getText());
        String player2Country = player2CountryTextField.getText();
        List<Player> players = new ArrayList<>();

        List<String> signatureNamesPlayer1 = new ArrayList<>();
        List<String> signatureNamesPlayer1Active = new ArrayList<>();
        if (choiceBox1Player1.getValue() == "Passiv") {
            signatureNamesPlayer1.add(player1Signature1.getText());
        } else if(choiceBox1Player1.getValue() == "Aktiv") {
            signatureNamesPlayer1Active.add(player1Signature1.getText());
        }
        if (choiceBox2Player1.getValue() == "Passiv") {
            signatureNamesPlayer1.add(player1Signature2.getText());
        } else if(choiceBox2Player1.getValue() == "Aktiv") {
            signatureNamesPlayer1Active.add(player1Signature2.getText());
        }
        if (choiceBox3Player1.getValue() == "Passiv") {
            signatureNamesPlayer1.add(player1Signature3.getText());
        } else if(choiceBox3Player1.getValue() == "Aktiv") {
            signatureNamesPlayer1Active.add(player1Signature3.getText());
        }

        List<String> signatureNamesPlayer2 = new ArrayList<>();
        List<String> signatureNamesPlayer2Active = new ArrayList<>();
        if (choiceBox1Player2.getValue() == "Passiv") {
            signatureNamesPlayer2.add(player2Signature1.getText());
        } else if(choiceBox1Player2.getValue() == "Aktiv") {
            signatureNamesPlayer2Active.add(player2Signature1.getText());
        }
        if (choiceBox2Player2.getValue() == "Passiv") {
            signatureNamesPlayer2.add(player2Signature2.getText());
        } else if(choiceBox2Player2.getValue() == "Aktiv") {
            signatureNamesPlayer2Active.add(player2Signature2.getText());
        }
        if (choiceBox3Player2.getValue() == "Passiv") {
            signatureNamesPlayer2.add(player2Signature3.getText());
        } else if(choiceBox3Player2.getValue() == "Aktiv") {
            signatureNamesPlayer2Active.add(player2Signature3.getText());
        }

        String player1FormationBonusString = "";
        if (player1FormationBonus.getText() != null) {
            player1FormationBonusString = player1FormationBonus.getText();
        }

        String player1TrainingBonusString = "";
        if (player1TrainingBonus.getText() != null) {
            player1TrainingBonusString = player1TrainingBonus.getText();
        }

        String player2FormationBonusString = "";
        if (player2FormationBonus.getText() != null) {
            player2FormationBonusString = player2FormationBonus.getText();
        }

        String player2TrainingBonusString = "";
        if (player2TrainingBonus.getText() != null) {
            player2TrainingBonusString = player2TrainingBonus.getText();
        }

        players.add(new Player(player1Decksize, player1Name, player1Country, signatureNamesPlayer1, signatureNamesPlayer1Active, player1FormationBonusString, player1TrainingBonusString));
        players.add(new Player(player2Decksize, player2Name, player2Country, signatureNamesPlayer2, signatureNamesPlayer2Active, player2FormationBonusString, player2TrainingBonusString));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FMAP.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        FMAPController fmapController = loader.getController();
        fmapController.setStage(stage);
        stage.setUserData(players);
        stage.setScene(scene);
        stage.show();
        fmapController.initFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox1Player1.getItems().addAll("Aktiv", "Passiv");
        choiceBox2Player1.getItems().addAll("Aktiv", "Passiv");
        choiceBox3Player1.getItems().addAll("Aktiv", "Passiv");
        choiceBox1Player2.getItems().addAll("Aktiv", "Passiv");
        choiceBox2Player2.getItems().addAll("Aktiv", "Passiv");
        choiceBox3Player2.getItems().addAll("Aktiv", "Passiv");
    }
}

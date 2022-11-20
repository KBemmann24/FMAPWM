package com.ui.fmapwm;

import gamelogic.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.jfoenix.controls.*;

public class FMAPController {

    private Game game;

    private Stage stage;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label aktivLabel;

    @FXML
    private VBox middleBox;

    @FXML
    private Label player1DeckLabel;

    @FXML
    private Label player2DeckLabel;
    @FXML
    private ImageView cardView;

    @FXML
    private ImageView diceView;

    @FXML
    private JFXButton substituteButton;

    @FXML
    private HBox cardBox;

    @FXML
    private HBox activeButtonBox;

    @FXML
    private TextArea bonusTextArea;

    private Card currentCard;

    private List<Card> cardPool1 = new ArrayList<>();

    private List<Card> cardPool2 = new ArrayList<>();
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void rollDice() {
        File loadingFile = new File("src/DiceImages/loading.jpg");
        Image loadingImage = new Image(loadingFile.toURI().toString());
        diceView.setImage(loadingImage);

        Random random = new Random();
        int number = random.nextInt(6) + 1;
        File file = new File("src/DiceImages/" + number + ".png");
        Image image = new Image(file.toURI().toString());
        delay(2000, () -> diceView.setImage(image));
    }

    @FXML
    protected void substitute() {
        substituteButton.setDisable(true);
        Player activePlayer = aktivLabel.getText().contains(game.getPlayer1().getName()) ? game.getPlayer1() : game.getPlayer2();
        activePlayer.setSubstituteAvailable(false);
        showSubstituteCard(Card.AUSWECHSLUNG, activePlayer);
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    public void initFields() {
        List<Player> playerList = (List<Player>) getStage().getUserData();
        Player player1 = playerList.get(0);
        Player player2 = playerList.get(1);
        getStage().setTitle(player1.getName() + "(" + player1.getCountry() + ")" + " vs. " + player2.getName() + "(" + player2.getCountry() + ")");
        player1DeckLabel.setText("Restliche Karten " + player1.getName() + ": " + player1.getDecksize());
        player2DeckLabel.setText("Restliche Karten " + player2.getName() + ": " + player2.getDecksize());
        scoreLabel.setText(player1.getName() + " 0 : 0 " + player2.getName());
        aktivLabel.setText("Aktiv: " + player1.getName());
        startGame(player1, player2);
    }

    private List<Card> createPool() {

        List<Card> cardPool = new ArrayList<>();

        for(Card card : Card.values()) {
            for(int i=0; i<card.getQuantityInPool(); i++) {
                cardPool.add(card);
            }
        }
        Collections.shuffle(cardPool);
        return cardPool;
    }

    private void startGame(Player player1, Player player2) {
        cardPool1 = createPool();
        List<Card> player1SignatureCards = new ArrayList<>();
        for(String cardName : player1.getSignatureNames()) {
            player1SignatureCards.add(Card.valueOf(cardName.toUpperCase()));
        }
        cardPool1.addAll(player1SignatureCards);
        Collections.shuffle(cardPool1);
        List<Card> deck1 = new ArrayList<>();

        for (int i = 0; i < player1.getDecksize(); i++) {
            deck1.add(cardPool1.get(0));
            cardPool1.remove(cardPool1.get(0));
        }
        player1.setDeck(deck1);

        cardPool2 = createPool();
        List<Card> player2SignatureCards = new ArrayList<>();
        for(String cardName : player2.getSignatureNames()) {
            player2SignatureCards.add(Card.valueOf(cardName.toUpperCase()));
        }
        cardPool2.addAll(player2SignatureCards);
        Collections.shuffle(cardPool2);
        List<Card> deck2 = new ArrayList<>();
        for (int i = 0; i < player2.getDecksize(); i++) {
            deck2.add(cardPool2.get(0));
            cardPool2.remove(cardPool2.get(0));
        }
        player2.setDeck(deck2);

        game = new Game(player1, player2);

        showCardAndButtons(player1);
    }

    private void showCardAndButtons(Player player) {
        if (!player.isSubstituteAvailable()) {
            substituteButton.setDisable(true);
        } else {
            substituteButton.setDisable(false);
        }

        showActiveButtons(player);
        showBoni(player);

        Card cardToShow = player.getDeck().get(0);
        player.getDeck().remove(cardToShow);
        player.setDecksize(player.getDecksize() - 1);
        File cardFile = new File(cardToShow.getImagePath());
        Image cardImage = new Image(cardFile.toURI().toString());
        cardView.setImage(cardImage);
        cardView.fitWidthProperty().bind(cardBox.widthProperty().subtract(20));
        cardView.fitHeightProperty().bind(cardBox.heightProperty().subtract(20));

        player1DeckLabel.setText("Restliche Karten " + game.getPlayer1().getName() + ": " + game.getPlayer1().getDecksize());
        player2DeckLabel.setText("Restliche Karten " + game.getPlayer2().getName() + ": " + game.getPlayer2().getDecksize());
        aktivLabel.setText("Aktiv: " + player.getName());

        List<Button> buttons = new ArrayList<>();
        for (Action action : cardToShow.getActions()) {
            String dicesText = "";
            for (int dice : action.getDices()) {
                if (action.getDices().indexOf(dice) != action.getDices().size() - 1) {
                    dicesText = dicesText + dice + ",";
                } else {
                    dicesText = dicesText + dice;
                }
            }
            JFXButton button = new JFXButton();
            button.setText(action.getName() + " (" + dicesText + ")");
            button.setStyle("-fx-background-color: #9eb09e;");
            button.setOnAction(event -> processAction(action.getEffects(), player));
            buttons.add(button);
        }
        middleBox.getChildren().clear();
        middleBox.getChildren().addAll(buttons);
        currentCard = cardToShow;
    }

    private void showBoni(Player player) {
        bonusTextArea.clear();
        bonusTextArea.setText("Formationsbonus: " + player.getFormationBonus() + "\n" + "Trainingsbonus: " + player.getTrainingBonus());
        bonusTextArea.setEditable(false);
    }

    private void showActiveButtons(Player player) {
        activeButtonBox.getChildren().removeIf(node -> node.getId() == null || node.getId().equals("") || !node.getId().equals("substituteButton"));

        List<Player> players = new ArrayList<>();
        players.add(game.getPlayer1());
        players.add(game.getPlayer2());
        for(Player playerToIterate : players) {
            for (String passiveName : playerToIterate.getSignatureNamesActive().keySet()) {
                JFXButton button = new JFXButton();
                button.setText(passiveName);
                button.setStyle("-fx-background-color: #9eb09e;");
                if (!playerToIterate.getSignatureNamesActive().get(passiveName)) {
                    button.setDisable(true);
                }
                button.setOnAction(event -> disableButton(button, passiveName, playerToIterate));
                activeButtonBox.getChildren().add(button);
            }
        }
    }

    private void disableButton(JFXButton button, String passiveName, Player player) {
        button.setDisable(true);
        player.getSignatureNamesActive().put(passiveName, false);
    }

    private void showSpecificCardAndButtons(Card nextCard, Player activePlayer) {
        if (!activePlayer.isSubstituteAvailable()) {
            substituteButton.setDisable(true);
        } else {
            substituteButton.setDisable(false);
        }

        showActiveButtons(activePlayer);
        showBoni(activePlayer);

        File cardFile = new File(nextCard.getImagePath());
        Image cardImage = new Image(cardFile.toURI().toString());
        cardView.setImage(cardImage);
        cardView.fitWidthProperty().bind(cardBox.widthProperty().subtract(20));
        cardView.fitHeightProperty().bind(cardBox.heightProperty().subtract(20));
        player1DeckLabel.setText("Restliche Karten " + game.getPlayer1().getName() + ": " + game.getPlayer1().getDecksize());
        player2DeckLabel.setText("Restliche Karten " + game.getPlayer2().getName() + ": " + game.getPlayer2().getDecksize());
        aktivLabel.setText("Aktiv: " + activePlayer.getName());

        List<Button> buttons = new ArrayList<>();
        for (Action action : nextCard.getActions()) {
            String dicesText = "";
            for (int dice : action.getDices()) {
                if (action.getDices().indexOf(dice) != action.getDices().size() - 1) {
                    dicesText = dicesText + dice + ",";
                } else {
                    dicesText = dicesText + dice;
                }
            }
            JFXButton button = new JFXButton();
            button.setText(action.getName() + " (" + dicesText + ")");
            button.setStyle("-fx-background-color: #9eb09e;");
            button.setOnAction(event -> processAction(action.getEffects(), activePlayer));
            buttons.add(button);
        }
        middleBox.getChildren().clear();
        middleBox.getChildren().addAll(buttons);
        currentCard = nextCard;
    }

    private void showSubstituteCard(Card nextCard, Player activePlayer) {
        File cardFile = new File(nextCard.getImagePath());
        Image cardImage = new Image(cardFile.toURI().toString());
        cardView.setImage(cardImage);
        cardView.fitWidthProperty().bind(cardBox.widthProperty().subtract(20));
        cardView.fitHeightProperty().bind(cardBox.heightProperty().subtract(20));
        player1DeckLabel.setText("Restliche Karten " + game.getPlayer1().getName() + ": " + game.getPlayer1().getDecksize());
        player2DeckLabel.setText("Restliche Karten " + game.getPlayer2().getName() + ": " + game.getPlayer2().getDecksize());
        aktivLabel.setText("Aktiv: " + activePlayer.getName());

        List<Button> buttons = new ArrayList<>();
        for (Action action : nextCard.getActions()) {
            String dicesText = "";
            for (int dice : action.getDices()) {
                if (action.getDices().indexOf(dice) != action.getDices().size() - 1) {
                    dicesText = dicesText + dice + ",";
                } else {
                    dicesText = dicesText + dice;
                }
            }
            JFXButton button = new JFXButton();
            button.setText(action.getName() + " (" + dicesText + ")");
            button.setStyle("-fx-background-color: #9eb09e;");
            button.setOnAction(event -> processAction(action.getEffects(), activePlayer, currentCard));
            buttons.add(button);
        }
        middleBox.getChildren().clear();
        middleBox.getChildren().addAll(buttons);
    }

    private void processAction(List<Effect> effects, Player activePlayer) {
        processAction(effects, activePlayer, null);
    }
    private void processAction(List<Effect> effects, Player activePlayer, Card currentCard) {
        for (Effect effect : effects) {
            switch (effect.getType()) {
                case Goal:
                    activePlayer.setGoals(activePlayer.getGoals() + 1);
                    scoreLabel.setText(game.getPlayer1().getName() + " " + game.getPlayer1().getGoals() + " : " + game.getPlayer2().getGoals() + " " + game.getPlayer2().getName());
                    if (effects.indexOf(effect) == effects.size() - 1) {
                        endTurn(activePlayer);
                    }
                    goalPopup(activePlayer);
                    break;
                case Injury:
                case Nothing:
                    endTurn(activePlayer);
                    break;
                case RedCard:
                    activePlayer.setDecksize(activePlayer.getDecksize() - 3);
                    if (effects.indexOf(effect) == effects.size() - 1) {
                        endTurn(activePlayer);
                    }
                    break;
                case NextCard:
                    showSpecificCardAndButtons(Card.valueOf(effect.getNextCard()), activePlayer);
                    break;
                case AddOwnCards:
                    activePlayer.setDecksize(activePlayer.getDecksize() + effect.getQuantity());
                    List<Card> cardPool = null;
                    if (activePlayer == game.getPlayer1()) {
                        cardPool = cardPool1;
                    } else if (activePlayer == game.getPlayer2()){
                        cardPool = cardPool2;
                    }
                    for (int i = 0; i < effect.getQuantity(); i++) {
                        activePlayer.getDeck().add(cardPool.get(i));
                        cardPool.remove(i);
                    }
                    if (effects.indexOf(effect) == effects.size() - 1) {
                        endTurn(activePlayer);
                    }
                    break;
                case RedCardEnemy:
                    if (activePlayer == game.getPlayer1()) {
                        game.getPlayer2().setDecksize(game.getPlayer2().getDecksize() - 3);
                    } else if (activePlayer == game.getPlayer2()) {
                        game.getPlayer1().setDecksize(game.getPlayer1().getDecksize() - 3);
                    }
                    if (effects.indexOf(effect) == effects.size() - 1) {
                        endTurn(activePlayer);
                    }
                    break;
                case AddEnemyCards:
                    if (activePlayer == game.getPlayer1()) {
                        game.getPlayer2().setDecksize(game.getPlayer2().getDecksize() + effect.getQuantity());
                    } else if (activePlayer == game.getPlayer2()){
                        game.getPlayer1().setDecksize(game.getPlayer1().getDecksize() + effect.getQuantity());
                    }

                    List<Card> cardPoolForExtraCards = null;
                    if (activePlayer == game.getPlayer1()) {
                        cardPoolForExtraCards = cardPool1;
                    } else if (activePlayer == game.getPlayer2()){
                        cardPoolForExtraCards = cardPool2;
                    }
                    for (int i = 0; i < effect.getQuantity(); i++) {
                        activePlayer.getDeck().add(cardPoolForExtraCards.get(i));
                        cardPoolForExtraCards.remove(i);
                    }
                    if (effects.indexOf(effect) == effects.size() - 1) {
                        endTurn(activePlayer);
                    }
                    break;
                case NextCardEnemy:
                    if (activePlayer == game.getPlayer1()) {
                        showSpecificCardAndButtons(Card.valueOf(effect.getNextCard()), game.getPlayer2());
                    } else {
                        showSpecificCardAndButtons(Card.valueOf(effect.getNextCard()), game.getPlayer1());
                    }
                    break;
                case SubstractOwnCards:
                    activePlayer.setDecksize(activePlayer.getDecksize() - effect.getQuantity());
                    for(int i=0; i<effect.getQuantity(); i++) {
                        activePlayer.getDeck().remove(i);
                    }
                    if (effects.indexOf(effect) == effects.size() - 1) {
                        endTurn(activePlayer);
                    }
                    break;
                case SubstractEnemyCards:
                    if (activePlayer == game.getPlayer1()) {
                        game.getPlayer2().setDecksize(game.getPlayer2().getDecksize() - effect.getQuantity());
                        for(int i=0; i<effect.getQuantity(); i++) {
                            game.getPlayer2().getDeck().remove(i);
                        }
                    } else {
                        game.getPlayer1().setDecksize(game.getPlayer1().getDecksize() - effect.getQuantity());
                        for(int i=0; i<effect.getQuantity(); i++) {
                            game.getPlayer1().getDeck().remove(i);
                        }
                    }
                    if (effects.indexOf(effect) == effects.size() - 1) {
                        endTurn(activePlayer);
                    }
                    break;
                case Substitute:
                    showSpecificCardAndButtons(currentCard, activePlayer);
                    break;
                default:
                    break;
            }
        }
    }

    private void endTurn(Player activePlayer) {
        if (game.getPlayer1().getDecksize() <= 0 && game.getPlayer2().getDecksize() <= 0) {
            endGame();
        }
        if (game.getPlayer1() == activePlayer) {
            if (game.getPlayer2().getDecksize() > 0) {
                if (game.getPlayer2().getDecksize() <= 3) {
                    if (game.getPlayer1().getDecksize() <= 3) {
                        showCardAndButtons(game.getPlayer2());
                    } else {
                        showCardAndButtons(game.getPlayer1());
                    }
                } else {
                    showCardAndButtons(game.getPlayer2());
                }
            } else {
                showCardAndButtons(game.getPlayer1());
            }
        } else {
            if (game.getPlayer1().getDecksize() > 0) {
                if (game.getPlayer1().getDecksize() <= 3) {
                    if (game.getPlayer2().getDecksize() <= 3) {
                        showCardAndButtons(game.getPlayer1());
                    } else {
                        showCardAndButtons(game.getPlayer2());
                    }
                } else {
                    showCardAndButtons(game.getPlayer1());
                }
            } else {
                showCardAndButtons(game.getPlayer2());
            }
        }
    }

    private void goalPopup(Player player) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("TOOOOOOOOOOR!");
        alert.setHeaderText(null);
        alert.setGraphic(null);

        File videoToPlay = null;

        String folderName = player.getCountry();

        File directory = new File("src/Videos/Goal/" + folderName.replaceAll("\\s", "") + "/");
        if (!directory.exists()) {
            directory = new File("src/Videos/Goal/CPU/");
        }

        if (directory.exists()) {
            File[] files = directory.listFiles();
            Random rand = new Random();

            videoToPlay = files[rand.nextInt(files.length)];

            MediaPlayer videoPlayer = new MediaPlayer( new Media(videoToPlay.toURI().toString()));
            MediaView mediaView = new MediaView(videoPlayer);
            VBox content = new VBox(10, mediaView);
            content.setAlignment(Pos.CENTER);
            alert.getDialogPane().setPrefSize(1200, 1000);
            alert.getDialogPane().setContent(content);

            alert.setOnShowing(e -> videoPlayer.play());
            alert.showAndWait();
        }
    }

    private void endGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Spielende!");
        int player1Goals = game.getPlayer1().getGoals();
        int player2Goals = game.getPlayer2().getGoals();
        if (player1Goals > player2Goals) {
            alert.setHeaderText("Sieger: " + game.getPlayer1().getName());
        } else if (player2Goals > player1Goals) {
            alert.setHeaderText("Sieger: " + game.getPlayer2().getName());
        } else {
            alert.setHeaderText("Unentschieden!");
        }
        alert.setContentText("Das Spiel endete " + game.getPlayer1().getGoals() + " zu " + game.getPlayer2().getGoals());
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                getStage().close();
            }
        });
    }

    public Stage getStage() {
        return stage;
    }
}
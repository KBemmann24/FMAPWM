package gamelogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private int decksize;
    private List<Card> deck;

    private String name;

    private String country;

    private int goals;

    private boolean substituteAvailable;

    private List<String> signatureNames;

    private Map<String, Boolean> signatureNamesActive = new HashMap<>();

    private String formationBonus;

    private String trainingBonus;

    public Player(int decksize, String name, String country, List<String> signatureNames, List<String> signatureNamesActive, String formationBonus, String trainingBonus) {
        this.decksize = decksize;
        this.name = name;
        this.country = country;
        this.signatureNames = signatureNames;
        substituteAvailable = true;
        for(String string : signatureNamesActive) {
            this.signatureNamesActive.put(string, true);
        }
        this.formationBonus = formationBonus;
        this.trainingBonus = trainingBonus;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDecksize() {
        return decksize;
    }

    public void setDecksize(int decksize) {
        this.decksize = decksize;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public boolean isSubstituteAvailable() {
        return substituteAvailable;
    }

    public void setSubstituteAvailable(boolean substituteAvailable) {
        this.substituteAvailable = substituteAvailable;
    }

    public List<String> getSignatureNames() {
        return signatureNames;
    }

    public Map<String, Boolean> getSignatureNamesActive() {
        return signatureNamesActive;
    }

    public String getTrainingBonus() {
        return trainingBonus;
    }

    public String getFormationBonus() {
        return formationBonus;
    }
}

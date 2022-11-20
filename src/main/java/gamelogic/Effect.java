package gamelogic;

public class Effect {

    private EffectTypes type;
    private int quantity;
    private String nextCard;

    public Effect(EffectTypes type, int quantity, String nextCard) {
        this.type = type;
        this.quantity = quantity;
        this.nextCard = nextCard;
    }

    public Effect(EffectTypes type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public Effect(EffectTypes type, String nextCard) {
        this.type = type;
        this.nextCard = nextCard;
    }

    public Effect(EffectTypes type) {
        this.type = type;
    }

    public EffectTypes getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNextCard() {
        return nextCard;
    }
}

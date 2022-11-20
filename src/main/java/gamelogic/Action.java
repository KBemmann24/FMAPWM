package gamelogic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Action {

    private String name;
    private List<Integer> dices;
    private List<Effect> effects;

    public Action(String name, List<Integer> dices, Effect... effects) {
        this.name = name;
        this.dices = dices;
        this.effects = Arrays.stream(effects).toList();
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public List<Integer> getDices() {
        return dices;
    }

    public void setDices(List<Integer> dices) {
        this.dices = dices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

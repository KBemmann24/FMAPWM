package gamelogic;

import org.controlsfx.control.action.AnnotatedCheckAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Card {

    ABSTOSS("Abstoß", "src/Cards/Abstoß.png", Arrays.asList(
            new Action("Mittelfeldgeplänkel", Arrays.asList(1, 2, 3), new Effect(EffectTypes.Nothing)),
            new Action("Kurzer Abstoß", Arrays.asList(4, 5), new Effect(EffectTypes.NextCard, "SCHOENE_KOMPINATION")),
            new Action("Langes Ding", Arrays.asList(6), new Effect(EffectTypes.NextCard, "STEILPASS"))), 3),
    ABGEFANGEN("Abgefangen", "src/Cards/Abgefangen.png", Arrays.asList(
            new Action("Syke", Arrays.asList(1, 2), new Effect(EffectTypes.NextCardEnemy, "FLANKE")),
            new Action("Ballbesitz", Arrays.asList(3, 4), new Effect(EffectTypes.AddOwnCards, 1)),
            new Action("Steilpass", Arrays.asList(5, 6), new Effect(EffectTypes.NextCard, "STEILPASS"))), 2),
    BEIM_SCHIRI_BESCHWEREN("Beim Schiri beschweren", "src/Cards/SchiriBeschweren.png", Arrays.asList(
            new Action("Gelb-rot", Arrays.asList(1), new Effect(EffectTypes.RedCard)),
            new Action("Ignoriert", Arrays.asList(2, 3, 4, 5), new Effect(EffectTypes.Nothing)),
            new Action("Eyyyy Schiri", Arrays.asList(6), new Effect(EffectTypes.SubstractEnemyCards, 2))
    ), 1),
    DIREKTER_FREISTOSS("Direkter Freistoß", "src/Cards/DirekterFreistoss.png", Arrays.asList(
            new Action("In die Mauer", Arrays.asList(1, 2), new Effect(EffectTypes.Nothing)),
            new Action("Flanke", Arrays.asList(3), new Effect(EffectTypes.NextCard, "FLANKE")),
            new Action("Gehalten", Arrays.asList(4, 5), new Effect(EffectTypes.NextCard, "ECKE")),
            new Action("Traumtor", Arrays.asList(6), new Effect(EffectTypes.Goal))
    ), 3),
    ECKE("Ecke", "src/Cards/Ecke.png", Arrays.asList(
            new Action("Konter", Arrays.asList(1), new Effect(EffectTypes.NextCardEnemy, "KONTER")),
            new Action("An Freund und Feind vorbei", Arrays.asList(2, 3), new Effect(EffectTypes.Nothing)),
            new Action("Kurz ausgeführt... Asi", Arrays.asList(4), new Effect(EffectTypes.NextCard, "FLANKE")),
            new Action("Ecke", Arrays.asList(5), new Effect(EffectTypes.NextCard, "ECKE")),
            new Action("Tor", Arrays.asList(6), new Effect(EffectTypes.Goal))
    ), 5),
    ELFMETER("Elfmeter", "src/Cards/Elfmeter.png", Arrays.asList(
            new Action("In die Wolken", Arrays.asList(1), new Effect(EffectTypes.Nothing)),
            new Action("Ecke", Arrays.asList(2), new Effect(EffectTypes.NextCard, "ECKE")),
            new Action("Tor", Arrays.asList(3, 4, 5, 6), new Effect(EffectTypes.Goal))
    ), 1),
    FERNSCHUSS("Fernschuss", "src/Cards/Fernschuss.png", Arrays.asList(
            new Action("In die Wolken", Arrays.asList(1, 2, 3, 4), new Effect(EffectTypes.Nothing)),
            new Action("Ecke", Arrays.asList(5), new Effect(EffectTypes.NextCard, "ECKE")),
            new Action("Sonntagsschuss", Arrays.asList(6), new Effect(EffectTypes.Goal))
    ), 5),
    FLANKE("Flanke", "src/Cards/Flanke.png", Arrays.asList(
            new Action("An Freund und Feind vorbei", Arrays.asList(1, 2, 3, 4), new Effect(EffectTypes.Nothing)),
            new Action("Ecke", Arrays.asList(5), new Effect(EffectTypes.NextCard, "ECKE")),
            new Action("Torchance", Arrays.asList(6), new Effect(EffectTypes.NextCard, "TORCHANCE_NACH_FLANKE"))
    ), 7),
    TORCHANCE_NACH_FLANKE("Torchance nach Flanke", "src/Cards/TorchanceNachFlanke.png", Arrays.asList(
            new Action("Daneben", Arrays.asList(1, 2, 3, 4, 5), new Effect(EffectTypes.Nothing)),
            new Action("Tor", Arrays.asList(6), new Effect(EffectTypes.Goal))
    )),
    GEGENPRESSING("Gegenpressing", "src/Cards/Gegenpressing.png", Arrays.asList(
            new Action("Steilpass", Arrays.asList(1), new Effect(EffectTypes.NextCardEnemy, "STEILPASS")),
            new Action("Ohne Erfolg", Arrays.asList(2, 3), new Effect(EffectTypes.Nothing)),
            new Action("Gegner verliert Ball", Arrays.asList(4), new Effect(EffectTypes.SubstractEnemyCards, 1)),
            new Action("Großchance", Arrays.asList(5, 6), new Effect(EffectTypes.NextCard, "GROSSCHANCE"))
    ), 2),
    GROSSCHANCE("Großchance", "src/Cards/Grosschance.png", Arrays.asList(
            new Action("Daneben", Arrays.asList(1, 2), new Effect(EffectTypes.Nothing)),
            new Action("Ecke", Arrays.asList(3, 4), new Effect(EffectTypes.NextCard, "ECKE")),
            new Action("Nachschuss", Arrays.asList(5), new Effect(EffectTypes.Nothing)),
            new Action("Tor", Arrays.asList(6), new Effect(EffectTypes.Goal))
    ), 3),
    GROSSCHANCE_NACHSCHUSS("Großchance Nachschuss", "src/Cards/GrosschanceNachschuss.png", Arrays.asList(
            new Action("Ecke", Arrays.asList(1, 2), new Effect(EffectTypes.NextCard, "ECKE")),
            new Action("Erneuter Nachschuss", Arrays.asList(3), new Effect(EffectTypes.NextCard, "GROSSCHANCE_NACHSCHUSS")),
            new Action("Tor", Arrays.asList(4, 5, 6), new Effect(EffectTypes.Goal))
    )),
    INS_DRIBBLING_GEHEN("Ins Dribbling Gehen", "src/Cards/Dribbling.png", Arrays.asList(
            new Action("Ah, verkackt", Arrays.asList(1, 2, 3), new Effect(EffectTypes.Nothing)),
            new Action("Einfach druff", Arrays.asList(4), new Effect(EffectTypes.NextCard, "FERNSCHUSS")),
            new Action("Gelegt!", Arrays.asList(5), new Effect(EffectTypes.NextCard, "DIREKTER_FREISTOSS")),
            new Action("Großchance", Arrays.asList(6), new Effect(EffectTypes.NextCard, "GROSSCHANCE"))
    ), 4),
    KONTER("Konter", "src/Cards/Konter.png", Arrays.asList(
            new Action("Abseits", Arrays.asList(1, 2), new Effect(EffectTypes.Nothing)),
            new Action("Taktisches Foul", Arrays.asList(3, 4), new Effect(EffectTypes.NextCard, "DIREKTER_FREISTOSS")),
            new Action("Großchance", Arrays.asList(5, 6), new Effect(EffectTypes.NextCard, "GROSSCHANCE"))
    ), 2),
    ROTE_KARTE("Rote Karte", "src/Cards/RoteKarte.png", Arrays.asList(
            new Action("Elfmeter", Arrays.asList(1, 2), new Effect(EffectTypes.SubstractOwnCards, 3),  new Effect(EffectTypes.NextCardEnemy, "ELFMETER")),
            new Action("Direkter Freistoß", Arrays.asList(3, 4, 5), new Effect(EffectTypes.SubstractOwnCards, 3), new Effect(EffectTypes.NextCardEnemy, "DIREKTER_FREISTOSS")),
            new Action("Videobeweis!", Arrays.asList(6), new Effect(EffectTypes.Nothing))
    ), 1),
    SCHOENE_KOMBINATION("Schöne Kombination", "src/Cards/SchoeneKombination.png", Arrays.asList(
            new Action("Fernschuss", Arrays.asList(1, 2), new Effect(EffectTypes.NextCard, "FERNSCHUSS")),
            new Action("Rainbow-Flick", Arrays.asList(3), new Effect(EffectTypes.NextCard, "RAINBOW_FLICK")),
            new Action("Rabona-Flanke", Arrays.asList(4), new Effect(EffectTypes.NextCard, "RABONA_FLANKE")),
            new Action("Tigerschuss", Arrays.asList(5), new Effect(EffectTypes.NextCard, "TIGERSCHUSS")),
            new Action("Megaschuss", Arrays.asList(6), new Effect(EffectTypes.NextCard, "MEGASCHUSS"))
    ), 1),
    SCHOENE_KOMPINATION("Schöne Kompination", "src/Cards/SchoeneKompination.png", Arrays.asList(
            new Action("Verläufts ins Nichts", Arrays.asList(1, 2, 3), new Effect(EffectTypes.Nothing)),
            new Action("Fernschuss", Arrays.asList(4), new Effect(EffectTypes.NextCard, "FERNSCHUSS")),
            new Action("Großchance", Arrays.asList(5), new Effect(EffectTypes.NextCard, "GROSSCHANCE")),
            new Action("Elfmeter", Arrays.asList(6), new Effect(EffectTypes.NextCard, "ELFMETER"))
    ), 4),
    SPIELEROEFFNUNG("Spieleröffnung", "src/Cards/Spieleroeffnung.png", Arrays.asList(
            new Action("Fehlpass", Arrays.asList(1), new Effect(EffectTypes.NextCardEnemy,"KONTER")),
            new Action("Flanke", Arrays.asList(2, 3, 4), new Effect(EffectTypes.NextCard, "FLANKE")),
            new Action("Schöne Kompination", Arrays.asList(5, 6), new Effect(EffectTypes.NextCard, "SCHOENE_KOMPINATION"))
    ), 4),
    SPRINTDUELL("Sprintduell", "src/Cards/Sprintduell.png", Arrays.asList(
            new Action("Abgelaufen", Arrays.asList(1, 2), new Effect(EffectTypes.NextCardEnemy, "ABSTOSS")),
            new Action("Gewonnen", Arrays.asList(3, 4, 5), new Effect(EffectTypes.NextCard, "INS_DRIBBLING_GEHEN")),
            new Action("Der schubbt doch!", Arrays.asList(6), new Effect(EffectTypes.NextCard, "ELFMETER"))
    ), 1),
    STEILPASS("Steilpass", "src/Cards/Steilpass.png", Arrays.asList(
            new Action("Abseits", Arrays.asList(1, 2, 3, 4), new Effect(EffectTypes.Nothing)),
            new Action("Großchance", Arrays.asList(5), new Effect(EffectTypes.NextCard, "GROSSCHANCE")),
            new Action("Notgrätsche, Rot", Arrays.asList(6), new Effect(EffectTypes.RedCardEnemy))
    ), 4),
    TAKTIKANWEISUNG_VOM_TRAINER("Taktikanweisung vom Trainer", "src/Cards/Taktikanweisung.png", Arrays.asList(
            new Action("Offensiv", Arrays.asList(), new Effect(EffectTypes.NextCard, "OFFENSIVE_TAKTIKANWEISUNG")),
            new Action("Defensiv", Arrays.asList(), new Effect(EffectTypes.NextCard, "DEFENSIVE_TAKTIKANWEISUNG"))
    ), 1),
    TIKI_TAKA("Tiki-Taka", "src/Cards/TikiTaka.png", Arrays.asList(
            new Action("Verläuft sich", Arrays.asList(1, 2, 3), new Effect(EffectTypes.Nothing)),
            new Action("Steilpass", Arrays.asList(4), new Effect(EffectTypes.NextCard, "STEILPASS")),
            new Action("Großchance", Arrays.asList(5, 6), new Effect(EffectTypes.NextCard,"GROSSCHANCE"))
    ), 4),
    VERLETZUNG("Verletzung", "src/Cards/Verletzung.png", Arrays.asList(
            new Action("Verletzung", Arrays.asList(1, 2, 3, 4, 5, 6), new Effect((EffectTypes.Injury)))
    ), 1),
    ZWEITE_LUFT("Zweite Luft", "src/Cards/ZweiteLuft.png", Arrays.asList(
            new Action("+1", Arrays.asList(1, 2), new Effect(EffectTypes.AddOwnCards, 1)),
            new Action("+2", Arrays.asList(3, 4), new Effect(EffectTypes.AddOwnCards, 2)),
            new Action("+3", Arrays.asList(5, 6), new Effect(EffectTypes.AddOwnCards, 3))
    ), 1),
    OFFENSIVE_TAKTIKANWEISUNG("Offensive Taktikanweisung", "src/Cards/OffensiveTaktikanweisung.png", Arrays.asList(
            new Action("Konter für den Gegner", Arrays.asList(1, 2), new Effect(EffectTypes.NextCardEnemy, "KONTER")),
            new Action("Eine Karte ins Deck", Arrays.asList(3), new Effect(EffectTypes.AddOwnCards, 1)),
            new Action("Zwei Karten ins Deck", Arrays.asList(4), new Effect(EffectTypes.AddOwnCards, 2)),
            new Action("Drei Karten ins Deck", Arrays.asList(5), new Effect(EffectTypes.AddOwnCards, 3)),
            new Action("Großchance", Arrays.asList(6), new Effect(EffectTypes.NextCard, "GROSSCHANCE"))
    ), true),
    DEFENSIVE_TAKTIKANWEISUNG("Defensive Taktikanweisung", "src/Cards/DefensiveTaktikanweisung.png", Arrays.asList(
            new Action("Großchance für deinen Gegner", Arrays.asList(1), new Effect(EffectTypes.NextCardEnemy, "GROSSCHANCE")),
            new Action("Flanke für deinen Gegner", Arrays.asList(2, 3), new Effect(EffectTypes.NextCardEnemy, "FLANKE")),
            new Action("-1 Karte für Gegner", Arrays.asList(4), new Effect(EffectTypes.SubstractEnemyCards, 1)),
            new Action("-2 Karten für Gegner", Arrays.asList(5), new Effect(EffectTypes.SubstractEnemyCards, 2)),
            new Action("-3 Karten für Gegner", Arrays.asList(6), new Effect(EffectTypes.SubstractEnemyCards, 3))
    ), true),
    RABONA_FLANKE("Rabone-Flanke", "src/Cards/RabonaFlanke.png", Arrays.asList(
            new Action("Daneben", Arrays.asList(1, 2, 3, 4), new Effect(EffectTypes.Nothing)),
            new Action("Seitfallziehertor", Arrays.asList(5), new Effect(EffectTypes.Goal)),
            new Action("Scorpionkick-Tor", Arrays.asList(6), new Effect(EffectTypes.AddOwnCards, 1), new Effect(EffectTypes.Goal))
    ), true),
    RAINBOW_FLICK("Rainbow-Flick", "src/Cards/RainbowFlick.png", Arrays.asList(
            new Action("Ins Dribbling", Arrays.asList(1, 2, 3, 4, 5), new Effect(EffectTypes.NextCard, "INS_DRIBBLING_GEHEN")),
            new Action("Tor über den Keeper", Arrays.asList(6), new Effect(EffectTypes.AddOwnCards, 1), new Effect(EffectTypes.Goal))
    ), true),
    TIGERSCHUSS("Tigerschuss", "src/Cards/Tigerschuss.png", Arrays.asList(
            new Action("Abstimmungsschwierigkeiten", Arrays.asList(1, 2, 3), new Effect(EffectTypes.Nothing)),
            new Action("Unhaltbar!", Arrays.asList(4, 5, 6), new Effect(EffectTypes.Goal))
    ), true),
    MEGASCHUSS("Megaschuss", "src/Cards/Megaschuss.png", Arrays.asList(
            new Action("Tor!", Arrays.asList(1, 2, 3, 4, 5), new Effect(EffectTypes.Goal)),
            new Action("Der Kapitän übernimmt", Arrays.asList(6), new Effect(EffectTypes.Goal), new Effect(EffectTypes.NextCard, "MEGASCHUSS_NACHSCHUSS"))
    ), true),
    AUSWECHSLUNG("Auswechslung", "src/Cards/Auswechslung.png", Arrays.asList(
            new Action("Offensiv", Arrays.asList(), new Effect(EffectTypes.AddOwnCards, 2), new Effect(EffectTypes.AddEnemyCards, 3), new Effect(EffectTypes.Substitute)),
            new Action("Defensiv", Arrays.asList(), new Effect(EffectTypes.SubstractOwnCards, 3), new Effect(EffectTypes.SubstractEnemyCards, 2), new Effect(EffectTypes.Substitute))
    )),
    FLOTTAKOS("Flottakos", "src/Cards/Flottakos.png", Arrays.asList(
            new Action("Daneben", Arrays.asList(1, 2), new Effect(EffectTypes.Nothing)),
            new Action("Fernschuss", Arrays.asList(3), new Effect(EffectTypes.NextCard, "FERNSCHUSS")),
            new Action("Großchance", Arrays.asList(4), new Effect(EffectTypes.NextCard, "GROSSCHANCE")),
            new Action("Tor! GOHFELD!", Arrays.asList(5, 6), new Effect(EffectTypes.Goal))
    )),
    GATTUSO("Gattuso", "src/Cards/Gattuso.png", Arrays.asList(
            new Action("Fernschuss", Arrays.asList(1, 2, 3), new Effect(EffectTypes.SubstractEnemyCards, 1), new Effect(EffectTypes.NextCard, "FERNSCHUSS")),
            new Action("Großchance", Arrays.asList(4, 5), new Effect(EffectTypes.SubstractEnemyCards, 1), new Effect(EffectTypes.NextCard, "GROSSCHANCE")),
            new Action("Tor", Arrays.asList(6), new Effect(EffectTypes.SubstractEnemyCards, 1), new Effect(EffectTypes.Goal))
    )),
    KOLLER("Koller", "src/Cards/Koller.png", Arrays.asList(
            new Action("Gehalten", Arrays.asList(1, 2), new Effect(EffectTypes.Nothing)),
            new Action("Ecke", Arrays.asList(3, 4), new Effect(EffectTypes.NextCard, "ECKE")),
            new Action("Kopfballtor", Arrays.asList(5, 6), new Effect(EffectTypes.Goal))
    )),
    MANE("Mane", "src/Cards/Mane.png", Arrays.asList(
            new Action("Fernschuss", Arrays.asList(1, 2), new Effect(EffectTypes.NextCard, "FERNSCHUSS")),
            new Action("Freistoß", Arrays.asList(3, 4), new Effect(EffectTypes.NextCard, "DIREKTER_FREISTOSS")),
            new Action("Tor", Arrays.asList(5, 6), new Effect(EffectTypes.Goal))
    )),
    RAUL("Raul", "src/Cards/Raul.png", Arrays.asList(
            new Action("Daneben", Arrays.asList(1, 2), new Effect(EffectTypes.Nothing)),
            new Action("Fernsschuss", Arrays.asList(3), new Effect(EffectTypes.NextCard, "FERNSCHUSS")),
            new Action("Großchance", Arrays.asList(4), new Effect(EffectTypes.NextCard, "GROSSCHANCE")),
            new Action("Tor", Arrays.asList(5, 6), new Effect(EffectTypes.Goal))
    )),
    RONALDINHO("Ronaldinho", "src/Cards/Ronaldinho.png", Arrays.asList(
            new Action("Fernschuss", Arrays.asList(1, 2), new Effect(EffectTypes.NextCard, "FERNSCHUSS")),
            new Action("Freistoß", Arrays.asList(3, 4), new Effect(EffectTypes.NextCard, "DIREKTER_FREISTOSS")),
            new Action("Tor", Arrays.asList(5, 6), new Effect(EffectTypes.Goal))
    )),
    WANCHOPE("Wanchope", "src/Cards/Wanchope.png", Arrays.asList(
            new Action("Daneben", Arrays.asList(1, 2), new Effect(EffectTypes.Nothing)),
            new Action("Ecke", Arrays.asList(3, 4), new Effect(EffectTypes.NextCard, "ECKE")),
            new Action("Tor", Arrays.asList(5, 6), new Effect(EffectTypes.Goal))
    )),
    MEGASCHUSS_NACHSCHUSS("Megaschuss Nachschuss", "src/Cards/MegaschussNachschuss.png", Arrays.asList(
            new Action("Nichts passiert", Arrays.asList(1, 2, 3, 4, 5), new Effect(EffectTypes.Nothing)),
            new Action("Tor", Arrays.asList(6), new Effect(EffectTypes.Goal))
    ));
    private String name;
    private String imagePath;
    private List<Action> actions;

    private boolean specialCard;

    private int quantityInPool;

    Card(String name, String imagePath, List<Action> actions) {
        this.name = name;
        this.imagePath = imagePath;
        this.actions = actions;
        this.specialCard = false;
        quantityInPool = 0;
    }

    Card(String name, String imagePath, List<Action> actions, int quantityInPool) {
        this.name = name;
        this.imagePath = imagePath;
        this.actions = actions;
        this.specialCard = false;
        this.quantityInPool = quantityInPool;
    }


    Card(String name, String imagePath, List<Action> actions, boolean specialCard) {
        this.name = name;
        this.imagePath = imagePath;
        this.actions = actions;
        this.specialCard = specialCard;
        quantityInPool = 0;
    }

    Card(String name, String imagePath, List<Action> actions, boolean specialCard, int quantityInPool) {
        this.name = name;
        this.imagePath = imagePath;
        this.actions = actions;
        this.specialCard = specialCard;
        this.quantityInPool = quantityInPool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public boolean isSpecialCard() {
        return specialCard;
    }

    public int getQuantityInPool() {
        return quantityInPool;
    }
}

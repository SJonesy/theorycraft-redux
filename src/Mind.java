import java.util.List;

public abstract class Mind {
    public abstract CombatAction decideAction(Character actor, List<Party> parties);

    public static Mind GetMind(String mindName) {
        switch (mindName.toUpperCase()) {
            case "GENERAL":
                return new GeneralMind();
            default:
                return null;
        }
    }
}

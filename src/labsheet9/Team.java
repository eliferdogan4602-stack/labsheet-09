
package labsheet9;

    import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> members;

    public Team() {
        this.members = new ArrayList<>();
    }

    public boolean addCharacter(GameCharacter character) {
        if (character == null) return false;
        if (findByName(character.getName()) != null) {
            System.out.println("Error: A character named '" + character.getName() + "' is already in the team.");
            return false;
        }
        members.add(character);
        System.out.println(character.getName() + " joined the team.");
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter target = findByName(name);
        if (target != null) {
            members.remove(target);
            System.out.println(name + " has been removed from the team.");
            return true;
        }
        System.out.println("Character '" + name + "' not found.");
        return false;
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter c : members) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public void attackAll(GameCharacter target) {
        if (target == null || !target.isAlive()) {
            System.out.println("Target is already defeated!");
            return;
        }
        for (GameCharacter member : members) {
            if (member.isAlive() && member instanceof Attacker) {
                int dmg = ((Attacker) member).attack(target);
                System.out.println(member.getName() + " attacks " + target.getName() + " for " + dmg + " damage.");
            }
        }
    }

    public void healTeam() {
        for (GameCharacter member : members) {
            if (member.isAlive() && member instanceof Supporter) {
                GameCharacter lowestRatioMember = getLowestHealthRatioMember();
                if (lowestRatioMember != null) {
                    int healed = ((Supporter) member).heal(lowestRatioMember);
                    System.out.println(member.getName() + " heals " + lowestRatioMember.getName() + " for " + healed + " HP.");
                }
            }
        }
    }

    private GameCharacter getLowestHealthRatioMember() {
        GameCharacter target = null;
        double lowestRatio = Double.MAX_VALUE;

        for (GameCharacter c : members) {
            if (c.isAlive()) {
                double ratio = (double) c.getHealth() / c.getMaxHealth();
                if (ratio < lowestRatio) {
                    lowestRatio = ratio;
                    target = c;
                }
            }
        }
        return target;
    }

    public int getTotalPower() {
        int total = 0;
        for (GameCharacter member : members) {
            if (member.isAlive()) {
                total += member.calculatePower();
            }
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("=== TEAM ROSTER (Total Alive Power: " + getTotalPower() + ") ===");
        for (GameCharacter member : members) {
            System.out.println(member.getCharacterInfo() + (member.isAlive() ? "" : " [DEFEATED]"));
        }
        System.out.println("==========================================");
    }
}



package labsheet9;

public class Labsheet9 {

  
    
    public static void main(String[] args) {
        System.out.println("--- 1. Testing Invalid Inputs & Character Creation ---");
        Team heroes = new Team();
        
        
        Warrior aragorn = new Warrior("Aragorn", 150, 200, 30);
        Mage gandalf = new Mage("Gandalf", 12, 120, 50);
        Healer elrond = new Healer("Elrond", 10, -50, 25);

        heroes.addCharacter(aragorn);
        heroes.addCharacter(gandalf);
        heroes.addCharacter(elrond);

       
        Warrior duplicateAragorn = new Warrior("Aragorn", 5, 100, 10);
        heroes.addCharacter(duplicateAragorn);

        System.out.println();
        heroes.displayTeam();

        
        Warrior golem = new Warrior("Golem", 15, 300, 40);
        System.out.println("\nEnemy Appeared: " + golem.getCharacterInfo());

        
        System.out.println("\n--- ROUND 1 ---");
        heroes.attackAll(golem);
        System.out.println("Golem HP after Round 1: " + golem.getHealth() + "/" + golem.getMaxHealth());

      
        int dmg1 = golem.attack(aragorn);
        System.out.println(golem.getName() + " hits " + aragorn.getName() + " for " + dmg1 + " damage.");
        int dmg2 = golem.attack(gandalf);
        System.out.println(golem.getName() + " hits " + gandalf.getName() + " for " + dmg2 + " damage.");

       
        heroes.healTeam();
        heroes.displayTeam();

       
        System.out.println("\n--- ROUND 2 ---");
        heroes.attackAll(golem);
        System.out.println("Golem HP after Round 2: " + golem.getHealth() + "/" + golem.getMaxHealth());

        int dmg3 = golem.attack(elrond);
        System.out.println(golem.getName() + " hits " + elrond.getName() + " for " + dmg3 + " damage.");

        heroes.healTeam();
        heroes.displayTeam();

        
        System.out.println("\n--- Search and Remove Test ---");
        GameCharacter found = heroes.findByName("Gandalf");
        if (found != null) {
            System.out.println("Found character: " + found.getCharacterInfo());
        }

        heroes.removeCharacter("Elrond");
        heroes.displayTeam();
    }
}
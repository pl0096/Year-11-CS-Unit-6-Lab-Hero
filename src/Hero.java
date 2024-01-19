import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Hero {
    private String name;
    private int hitPoints;
    public Hero(String name){
        this.name = name;
        this.hitPoints = 100;
    }

    public String getName(){
        return name;
    }
    public int getHitPoints(){
        return hitPoints;
    }

    public String toString(){
        return "Hero{name='"+name+"', hitPoints=" + hitPoints+ "}";
    }
    public void attack(Hero opponent){
        Random rand = new Random();
        int r = rand.nextInt();
        if(r < 0.5){
            opponent.hitPoints -= 10;
        }
        else{
            this.hitPoints -= 10;
        }
    }

    public void senzuBean(){
        hitPoints = 100;
    }
    private void fightUntilTheDeathHelper(Hero opponent){
        while(true){
            attack(opponent);
            if(opponent.hitPoints == 0 || this.hitPoints == 0){
                break;
            }
        }
    }

    public String fightUntilTheDeath(Hero opponent){
        senzuBean();
        opponent.senzuBean();
        fightUntilTheDeathHelper(opponent);
        return this.name + ": " + this.hitPoints + "    " + opponent.name + ": " + opponent.hitPoints;
    }
    private int[] nFightsToTheDeathHelper(Hero opponent, int n){
        int[] wins = new int[] {0,0};

        for(int i = 0; i < n; i++){
            senzuBean();
            opponent.senzuBean();
            while(opponent.hitPoints != 0 && this.hitPoints != 0){
                attack(opponent);
            }
            if(opponent.hitPoints == 0){
                wins[0] += 1;
            }
            else{
                wins[1] += 1;
            }

        }
        return wins;
    }
    public String nFightsToTheDeath(Hero opponent, int n){
        int[] wins = nFightsToTheDeathHelper(opponent, n);
        String msg;
        if(wins[0] == wins[1]){
            msg = "OMG! It was actually a draw!";
        }
        else if(wins[0] > wins[1]){
            msg = this.name + " wins!";
        }
        else{
            msg = opponent.name + " wins!";
        }
        return this.name + ": "+wins[0] + " wins\n" + opponent.name + ": " + wins[1] + " wins\n" + msg;
    }
    public void dramaticFightToTheDeath(Hero opponent) throws InterruptedException {
        while(hitPoints != 0 && opponent.hitPoints != 0){
            attack(opponent);
            System.out.println(name + ": " + hitPoints + "    " + opponent.name + ": " + hitPoints);
            TimeUnit.SECONDS.sleep(1);

        }
        if (hitPoints == 0) {
            System.out.println(opponent.name + "wins!");


        }
        else{
            System.out.println(name + " wins!");
        }
    }

}

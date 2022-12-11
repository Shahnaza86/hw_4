import java.util.Locale;
import java.util.Random;

    public class Main {
        public static int bossHealth = 700;
        public static int bossDamage = 50;
        public static String bossDefence;
        public static int[] heroesHealth = {270, 260, 250,580,300,750,860,450};
        public static int[] heroesDamage = {20, 15, 10,0,16,30,18,25};
        public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic","Golem","Lucky","Berserk","Thor"};
        public static int roundNumber = 0;
        static Random random = new Random();
        public static String criticalMessage;

        public static void main(String[] args) {
            printStatistics();

            while (!isGameFinished()) {
                playRound();
            }
        }

        public static void playRound() {
            roundNumber++;
            chooseBossDefence();
            bossHits();
            medicTreat();
            lucky();
            Berserk();
            Thor();
            heroesHit();
            printStatistics();
            bossDamage =50;
        }
        public static void Thor() {
            boolean bossSleep =random.nextBoolean();
            if (heroesHealth[7] >0){
                if (bossSleep){
                    bossDamage = 0;
                }
            }
        }

        public static void Berserk(){
            Random random = new Random();
            int randomBlock = random.nextInt(25);
            if (heroesHealth[6] >0){
                heroesHealth[6] += randomBlock;
                heroesDamage[6] += randomBlock;
            }
        }
        public static void lucky(){
            Random random =new Random();
            boolean a = random.nextBoolean();
            if (a) {
                heroesHealth[5] += bossDamage;
            }
        }

        public static void chooseBossDefence() {
            Random random = new Random();
            int randIndex = random.nextInt(heroesAttackType.length); // 0,1,2
            bossDefence = heroesAttackType[randIndex];
        }

        public static void bossHits() {
            for (int i = 0; i < heroesHealth.length; i++) {
                int bossDamagePower;

                if (heroesHealth[4] > 0){
                    bossDamagePower= bossDamage - bossDamage/5;
                }else {
                    bossDamagePower = bossDamage;
                }
                if (heroesHealth[i] >0 ){
                    if (heroesHealth[i] - bossDamage <0){
                        heroesHealth[4]= heroesHealth[4]-bossDamage/5;
                        heroesHealth[i] = 0;
                    }else {
                        heroesHealth[i]=heroesHealth[i]-bossDamagePower;
                        heroesHealth[4]=heroesHealth[4]-bossDamage/5;
                    }
                }
            }
        }

        public static void heroesHit() {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    int damage = heroesDamage[i];
                    if (heroesAttackType[i] == bossDefence) {
                        Random random = new Random();
                        int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                        damage = heroesDamage[i] * coeff;
                        criticalMessage = "Critical damage: " + damage;
                    }
                    if (bossHealth - damage < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - damage;
                    }
                }
            }
        }
        public static void medicTreat(){
            for (int i = 0; i < heroesDamage.length ; i++) {
              if (heroesHealth[i] < 100 && heroesHealth[i]>0 &&heroesAttackType[i] !="Medic"){
                  heroesHealth[i]=heroesHealth[i] + 25;
              }
            }
        }

        public static void printStatistics() {
            System.out.println("ROUND " + roundNumber + " ------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
            System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                    (bossDefence == null ? "No defence" : bossDefence));
            for (int i = 0; i < heroesHealth.length; i++) {
                System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
            }
            if (criticalMessage != null) {
                System.out.println(">>> " + criticalMessage);
            }
        }

        public static boolean isGameFinished() {
            if (bossHealth <= 0) {
                System.out.println("Heroes won!!!");
                return true;
            }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
            boolean allHeroesDead = true;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    allHeroesDead = false;
                    break;
                }
            }
            if (allHeroesDead) {
                System.out.println("Boss won!!!");
            }
            return allHeroesDead;
        }
    }




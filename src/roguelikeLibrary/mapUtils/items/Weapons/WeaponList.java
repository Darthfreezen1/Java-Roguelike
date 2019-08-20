package roguelikeLibrary.mapUtils.items.Weapons;

/**
 * Created by fawf on 26/05/16.
 */
public class WeaponList {
    public Weapon[] tierOne, tierTwo, tierThree, tierFour, tierFive;

    public WeaponList(){

        tierOne = new Weapon[3];
        tierOne[0] = new ArmingSword();
        tierOne[1] = new Spear();
        tierOne[2] = new Axe();

        tierTwo = new Weapon[3];
        tierTwo[0] = new Katana();
        tierTwo[1] = new Longsword();
        tierTwo[2] = new Mace();

        tierThree = new Weapon[3];
        tierThree[0] = new Broadsword();
        tierThree[1] = new Claymore();
        tierThree[2] = new Sabre();

        tierFour = new Weapon[3];
        tierFour[0] = new BattleAxe();
        tierFour[1] = new Glaive();
        tierFour[2] = new Halberd();

        tierFive = new Weapon[3];
        tierFive[0] = new Warhammer();
        tierFour[1] = new Zweihander();
        tierFour[2] = new Poleaxe();

    }

    public Weapon[] getTierOne(){return tierOne;}
    public Weapon[] getTierTwo(){return tierTwo;}
    public Weapon[] getTierThree(){return tierThree;}
    public Weapon[] getTierFour(){return tierFour;}
    public Weapon[] getTierFive(){return tierFive;}

}

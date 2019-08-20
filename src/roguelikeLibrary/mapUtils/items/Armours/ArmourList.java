package roguelikeLibrary.mapUtils.items.Armours;

/**
 * Created by fawf on 26/05/16.
 */
public class ArmourList {
    private Armour[] tierOne, tierTwo, tierThree, tierFour, tierFive;

    public ArmourList(){

        tierOne = new Armour[3];
        tierOne[0] = new Fur();
        tierOne[1] = new Cloth();
        tierOne[2] = new Leather();

        tierTwo = new Armour[3];
        tierTwo[0] = new IronChainMail();
        tierTwo[1] = new IronPlateMail();
        tierTwo[2] = new IronScaleMail();

        tierThree = new Armour[2];
        tierThree[0] = new FullPlate();
        tierThree[1] = new SamuraiArmour();

        tierFour = new Armour[3];
        tierFour[0] = new SteelChainMail();
        tierFour[1] = new SteelPlateMail();
        tierFour[2] = new SteelScaleMail();

        tierFive = new Armour[3];
        tierFive[0] = new DragonScale();
        tierFive[1] = new Demonic();
        tierFive[2] = new Angelic();

    }

    public Armour[] getTierOne(){return tierOne;}
    public Armour[] getTierTwo(){return tierTwo;}
    public Armour[] getTierThree(){return tierThree;}
    public Armour[] getTierFour(){return tierFour;}
    public Armour[] getTierFive(){return tierFive;}
}

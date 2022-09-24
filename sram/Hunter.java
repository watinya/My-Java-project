
public class Hunter {
	private String name;
	private String specialSkill;
	private int HP;
	private int MP;
	private int defense;
	private int speed;
	private int defaultPower = 10;
	private int MaxHP;
	
	public Hunter(String name, String specialSkill, int HP, int MP, int defense, int speed) {
		this.name = name;
		this.specialSkill = specialSkill;
		this.HP = HP;
		this.MP = MP;
		this.defense = defense;
		this.speed = speed;
		
		MaxHP = HP;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSS() {
		return specialSkill;
	}
	
	public int getHP() {
		return HP;
	}
	
	public int getMP() {
		return MP;
	}
	
	public int getDefense(int mountDefense) {
		return defense + mountDefense;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int attack(int weaponPower) {
		return (defaultPower + weaponPower);
	}
	
	public int attacked(int hurt) {
		HP -= hurt;
		return 0;
	}
	
	public int rest() {
		HP += 50;
		
		if(HP > MaxHP) {
			HP = 100;
		}
		return HP;
	}
}

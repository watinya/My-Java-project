
public class Monster {
	private String name;
	private String description;
	private String type;
	private int HP;
	private int attack;
	private int speed;
	
	public Monster(String name, String description, String type, int HP, int attack, int speed) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.HP = HP;
		this.attack = attack;
		this.speed = speed;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getType() {
		return type;
	}
	
	public int getHP() {
		return HP;
	}
	
	public int getATK() {
		return attack;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int attacked(int hurt) {
		HP -= hurt;
		return 0;
	}
	
	public int sleep() {
		HP = 100;
		return HP;
	}
}

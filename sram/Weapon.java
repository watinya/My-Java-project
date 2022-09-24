
public class Weapon {
	private String name;
	private int power;
	private int speedEffect;
	
	public Weapon(String name, int power, int speedEffect) {
		this.name = name;
		this.power = power;
		this.speedEffect = speedEffect;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPower() {
		return power;
	}
	
	public int getSpeedEffect() {
		return speedEffect;
	}
}

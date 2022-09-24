
public class Mount {
	private String name;
	private int speed;
	private int speedEffect;
	private int defenseEffect;
	
	public Mount(String name, int speed, int speedEffect, int defenseEffect) {
		this.name = name;
		this.speed = speed;
		this.speedEffect = speedEffect;
		this.defenseEffect = defenseEffect;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getSpeedEffect() {
		return speedEffect;
	}
	
	public int getDefenseEffect() {
		return defenseEffect;
	}
}

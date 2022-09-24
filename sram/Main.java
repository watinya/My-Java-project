import java.util.Scanner;

public class Main {
	private static int weaponPower = 0;
	private static int mountDefense = 0;

	public static void main(String[] args) {
		int round = 1;
		boolean runAway = false;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("請輸入玩家名稱；");
		String name = sc.nextLine();
		
		Hunter hunter = new Hunter(name, "遊俠", 100, 30, 5, 15); //name, specialSkill, HP, MP, defense, speed
		Monster monster = new Monster("百變怪", "軟軟軟", "陸地", 100, 75, 10); //name, description, type, HP, attack, speed
		Mount unproperMount = new Mount("青眼白龍", 30, 20, 10); //name, speed, speedEffect, defenseEffect
		Mount properMount = new Mount("赤兔馬", 40, -5, 60); //name, speed, speedEffect, defenseEffect
		Weapon unproperWeapon = new Weapon("槍", 5, -10); //name, power, speedEffect
		Weapon properWeapon = new Weapon("弓", 35, -5); //name, power, speedEffect
		
		System.out.println("進入遊戲…\n怪物  [" + monster.getName() + "] 出現…\n");
		while(hunter.getHP() > 0 && monster.getHP() > 0 && !runAway) {
			System.out.println("---------第" + round++ + "回合---------");
			System.out.println("目前 " + name + "血量：" + hunter.getHP());
			System.out.println("目前怪物血量：" + monster.getHP());
			System.out.print("\n請選擇此回合之行動\n1.攻擊\n2.回復\n3.逃跑\n輸入行動編號：");
			
			int actionNum = sc.nextInt();
			if(actionNum == 1) {
				System.out.print("\n選擇要裝備的武器：\n1.徒手\n2.槍\n3.弓\n輸入武器編號：");
				int weaponNum = sc.nextInt();
				switch(weaponNum) {
					case 1:
						weaponPower = 0;
						break;
					case 2:
						weaponPower = unproperWeapon.getPower();
						break;
					case 3:
						weaponPower = properWeapon.getPower();
						break;
				}
				
				System.out.print("\n選擇要騎乘的坐騎；\n1.不騎乘\n2.青眼白龍\n3.赤兔馬\n輸入坐騎編號：");
				int mountNum = sc.nextInt();
				switch(mountNum) {
					case 1:
						mountDefense = 0;
						break;
					case 2:
						mountDefense = unproperMount.getDefenseEffect();
						break;
					case 3:
						mountDefense = properMount.getDefenseEffect();
						break;
				}
				
				int hurt = hunter.attack(weaponPower);
				monster.attacked(hurt);
				System.out.println("\n獵人攻擊！	造成傷害：-" + hurt);
				
				if(monster.getHP() > 0) {
					hurt = monster.getATK() - hunter.getDefense(mountDefense);
					hunter.attacked(hurt);
					System.out.println("怪物反擊！	造成傷害：-" + hurt + "\n");
				}
			} else if(actionNum == 2) {
				hunter.rest();
				monster.sleep();
				System.out.println("\n獵人吃仙丹 睡覺，血量恢復");
				System.out.println("怪物睡覺，血量恢復");
			} else {
				runAway = true;
			}
		}
		
		if(monster.getHP() <= 0) {
			System.out.println("\n怪物 ["+ monster.getName() + "] 血量歸零！");
		} else if(hunter.getHP() <= 0){
			System.out.println("\n獵人["+ hunter.getName() + "] 血量歸零！");
		} else {
			System.out.println("\n怪物太強，三十六計走為上策");
		}
		
		System.out.println("---------遊戲結束---------");
	}
}

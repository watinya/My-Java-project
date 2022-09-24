import java.util.Scanner;

public class Main {
	private static int weaponPower = 0;
	private static int mountDefense = 0;

	public static void main(String[] args) {
		int round = 1;
		boolean runAway = false;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�п�J���a�W�١F");
		String name = sc.nextLine();
		
		Hunter hunter = new Hunter(name, "�C�L", 100, 30, 5, 15); //name, specialSkill, HP, MP, defense, speed
		Monster monster = new Monster("���ܩ�", "�n�n�n", "���a", 100, 75, 10); //name, description, type, HP, attack, speed
		Mount unproperMount = new Mount("�C�����s", 30, 20, 10); //name, speed, speedEffect, defenseEffect
		Mount properMount = new Mount("���߰�", 40, -5, 60); //name, speed, speedEffect, defenseEffect
		Weapon unproperWeapon = new Weapon("�j", 5, -10); //name, power, speedEffect
		Weapon properWeapon = new Weapon("�}", 35, -5); //name, power, speedEffect
		
		System.out.println("�i�J�C���K\n�Ǫ�  [" + monster.getName() + "] �X�{�K\n");
		while(hunter.getHP() > 0 && monster.getHP() > 0 && !runAway) {
			System.out.println("---------��" + round++ + "�^�X---------");
			System.out.println("�ثe " + name + "��q�G" + hunter.getHP());
			System.out.println("�ثe�Ǫ���q�G" + monster.getHP());
			System.out.print("\n�п�ܦ��^�X�����\n1.����\n2.�^�_\n3.�k�]\n��J��ʽs���G");
			
			int actionNum = sc.nextInt();
			if(actionNum == 1) {
				System.out.print("\n��ܭn�˳ƪ��Z���G\n1.�{��\n2.�j\n3.�}\n��J�Z���s���G");
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
				
				System.out.print("\n��ܭn�M�������M�F\n1.���M��\n2.�C�����s\n3.���߰�\n��J���M�s���G");
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
				System.out.println("\n�y�H�����I	�y���ˮ`�G-" + hurt);
				
				if(monster.getHP() > 0) {
					hurt = monster.getATK() - hunter.getDefense(mountDefense);
					hunter.attacked(hurt);
					System.out.println("�Ǫ������I	�y���ˮ`�G-" + hurt + "\n");
				}
			} else if(actionNum == 2) {
				hunter.rest();
				monster.sleep();
				System.out.println("\n�y�H�Y�P�� ��ı�A��q��_");
				System.out.println("�Ǫ���ı�A��q��_");
			} else {
				runAway = true;
			}
		}
		
		if(monster.getHP() <= 0) {
			System.out.println("\n�Ǫ� ["+ monster.getName() + "] ��q�k�s�I");
		} else if(hunter.getHP() <= 0){
			System.out.println("\n�y�H["+ hunter.getName() + "] ��q�k�s�I");
		} else {
			System.out.println("\n�Ǫ��ӱj�A�T�Q���p�����W��");
		}
		
		System.out.println("---------�C������---------");
	}
}

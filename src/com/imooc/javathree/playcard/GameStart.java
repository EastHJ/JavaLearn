package com.imooc.javathree.playcard;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 此类启动玩牌程序
 * 
 * @author hjd
 *
 */
public class GameStart {
	// 玩家人数
	private final static int PLAYERNUM = 2;
	// 每位玩家手牌数量
	private final static int HANDCARDNUM = 2;
	// 创建玩家
	public static Player[] newPlayer() {
		// 创建玩家数组
		Player[] player = new Player[PLAYERNUM];
		// 使用控制台输入玩家信息
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < PLAYERNUM; i++) {
			System.out.println("请输入第" + (i + 1) + "位玩家ID和姓名");
			Player p = new Player();
			while (true) {
				try {
					System.out.println("请输入ID");
					int id = Integer.parseInt(scanner.nextLine());
					p.setId(id);
					System.out.println("请输入玩家姓名");
					p.setName(scanner.nextLine());
					player[i] = p;
					break;
				} catch (Exception e) {
					System.out.println("请输入整数类型的ID");
				} 
			}
		}
		scanner.close();
		//遍历玩家
		for(Player p:player)
		{
			System.out.println("欢迎玩家"+p.getName());
		}
		return player;
	}
	// 给玩家发牌
	public static void sendCard(Player[] player, Map<Integer, Card> card) {
		System.out.println("----------开始发牌----------");
		for (int i = 0, j = 0; i < HANDCARDNUM && j < card.size(); i++)
		{   // 遍历玩家发牌
			for (Player p : player) 
			{
				System.out.println(p.getName() + "拿牌");
				// 每个玩家每次发一张牌
				p.setHandCard(card.get(j));
				// 没发出一张牌存放牌的Map中的key值加一
				j++;
			}
		}
		System.out.println("----------发牌结束----------");
	}
	// 玩家玩牌进行比大小
	public static void playing(Player[] player) {
		System.out.println("----------开始游戏----------");
		// 创建比较器实例
		ComparatorCard ccd = new ComparatorCard();
		// 创建存放每个玩家最大牌的数组
		Card[] maxCards = new Card[player.length];
		for (int i=0;i<player.length;i++) 
		{
			// 获取玩家手牌
			List<Card> c = player[i].getHandCard();
			// 将玩家手牌排序，按降序排列从大到小0号为最大
			Collections.sort(c, ccd);
			//将玩家最大的牌放入maxCard数组中
			maxCards[i]=c.get(0);
			//输出玩家最大的牌张
			System.out.println("玩家："+player[i].getName() + "最大的牌为—— " + c.get(0));
			
		}
		// 将玩家的最大牌张进行比较得出胜者
		//创建个临时变量保存最大牌
		Card max =maxCards[0];
		//记录最大牌的选手标号
		int win = 0;
		//遍历maxCards找出最大牌的玩家编号
        for(int i =0; i<player.length;i++)
        {
			if (max.compareTo(maxCards[i]) < 0) {
				win = i;
				max =maxCards[i];
			} 
        }
        System.out.println("----------玩家："+player[win].getName() + "获胜----------");
		// 输出玩家的手牌
        System.out.println("玩家各自的手牌为：");
        for(Player p:player)
        {
        	System.out.println(p.getName() + "：" + p.getHandCard());
        }
	}

	public static void main(String[] args) {
		// 创建一个副扑克牌
		CreatePoker poker = new CreatePoker();
		poker.create();
		// 洗牌
		Map<Integer, Card> card = poker.washingCard();
		// 创建玩家
		Player[] player = newPlayer();
		// 给玩家发牌
		sendCard(player, card);
		// 玩家玩牌
		playing(player);
	}

}

// 设置设置比较器将玩家手中的牌进行排序
class ComparatorCard implements Comparator<Card> {
	/**
	 * 若返回值>0:o1>o2 若返回值<0:o1<o2 若返回值=0： o==o2
	 */
	// 重写compareTo方法惊醒牌的比较
	public int compare(Card c1, Card c2) {
		// 牌的花色按从小到大排序
		String[] type = { "草花", "方片", "红心", "黑桃" };
		// 设立比较变量记录两张牌的点数
		int thisNum = c1.getPoint();
		int anotherNum = c2.getPoint();
		// 如果点数一致就比较花色
		if (thisNum == anotherNum) {
			// 设立变量记录花色所在数组的下标
			for (int i = 0; i < type.length; i++) { // 找到符合的花色将数组下标赋值给比较变量
				if (c1.getMark().equals(type[i])) {
					thisNum = i;
				}
				if (c2.getMark().equals(type[i])) {
					anotherNum = i;
				}
			}
		}
		// 返回比较结果
		return anotherNum-thisNum;
	}
}
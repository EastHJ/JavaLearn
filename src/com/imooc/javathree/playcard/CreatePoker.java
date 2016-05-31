package com.imooc.javathree.playcard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本类用于产生一副扑克牌，并进洗牌和发牌
 * 
 * @author hjd
 *
 */
public class CreatePoker {
	// 一副扑克有四种花色
	private final String[] cardTypes = { "黑桃", "红桃", "方片", "草花" };
	// 每种花色牌张数
	private final int EACHCARDNUM = 13;
	// 牌的总张数除去大小王
	private final int ALLCARDNUM = 52;
	// 用List集合存放产生的整副扑克牌
	private List<Card> cardList;
	// 用Map存放洗好的扑克牌
	private Map<Integer, Card> sufferAfterCard;
	// 构造方法初始化属性
	public CreatePoker() {
		this.cardList = new ArrayList<Card>();
		this.sufferAfterCard = new HashMap<Integer, Card>();
	}
	// 此方法用与产生一副新的扑克牌
	public List<Card> create() {
		System.out.println("--------创建一副新扑克牌--------");
		// 遍历扑克牌花色产生新牌
		for (String type : cardTypes) 
		{
			for (int i = 0; i < EACHCARDNUM; i++) 
			{
				Card card = new Card();
				card.setMark(type);
				card.setPoint(i);
				cardList.add(card);
			}
		}
		System.out.println("--------新扑克创建成功--------");
		// 查看新产生的牌
		System.out.println("新扑克牌为" + cardList);
		return cardList;
	}
	// 此方法用于洗牌
	public Map<Integer, Card> washingCard() {
		System.out.println("----------开始洗牌----------");
		// 打乱集合中原有的牌序
		Collections.shuffle(cardList);
		// 测试洗牌效果
//		System.out.println(cardList);
		for (int i = 0; i < ALLCARDNUM; i++) {
			sufferAfterCard.put(i, cardList.get(i));
		}
		// 测试存放洗好的牌
//		System.out.println(sufferAfterCard);
		System.out.println("----------洗牌结束----------");
		return sufferAfterCard;
	}

	// 此处用于测试本类的方法
//	public static void main(String[] args) {
//		PlayingCards card = new PlayingCards();
//		List<Card> newcard = card.newCard();
//		card.washingCard();
//	}
}

package com.imocc.javathree.playcard;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类的实例表示一个牌手
 * @author hjd
 *
 */
public class Player {
	//牌手ID
	private int id ;
	//姓名
	private String name ;
	//手牌
	private List<Card> handCard ;
	//构造方法将牌手初始化
	public Player(){
		id = 0;
		name = "player";
		handCard = new ArrayList<Card>();
	}
	public Player(int id, String name, List<Card> handCard) {
		this.id = id;
		this.name = name;
		this.handCard = handCard;
	}
	//设置getter，Setter方法获获取牌手的属性
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getHandCard() {
		return handCard;
	}
	public void setHandCard( Card card) {
		 this.handCard.add(card);
	}
}

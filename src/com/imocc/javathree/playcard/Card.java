package com.imocc.javathree.playcard;

/**
 * 单张牌的特征
 * 
 * @author hjd
 *
 */
public class Card {
	// 牌的花色
	private String mark;
	// 牌的点数
	private int point;

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getPoint() {
		return point;
	}

	// 将牌的点数分开利于比大小
	public void setPoint(int point) {
		//扑克牌的点数从2——14
		if (0 == point) {
			this.point = 13;
		} else if (1 == point) {
			this.point = 14;
		} else {
			this.point = point;
		}

	}

	@Override
	public String toString() {
		//根据牌的点数将牌转换成相应的牌面
		String cardtype = null;
		if (1 < point && point < 11) {
			cardtype = mark + ":" + point;
		} else if (11 == point) {
			cardtype = mark + ":J";
		} else if (12 == point) {
			cardtype = mark + ":Q";
		} else if (13 == point) {
			cardtype = mark + ":K";
		} else if (14 == point) {
			cardtype = mark + ":A";
		}
		return cardtype;
	}
	//重写compareTo方法进行牌的比较
	public  int compareTo(Card card) {
		// 牌的花色按从小到大排序
		String[] type = { "草花", "方片", "红心", "黑桃" };
		// 设立比较变量记录两张牌的点数
		int thisNum = this.getPoint();
		int anotherNum = card.getPoint();
		// 如果点数一致就比较花色
		if (thisNum == anotherNum)
		{
			// 设立变量记录花色所在数组的下标
			for (int i = 0; i < type.length; i++)
			{ // 找到符合的花色将数组下标赋值给比较变量
				if (this.getMark().equals(type[i])) 
				{
					thisNum = i;
				}
				if (card.getMark().equals(type[i]))
				{
					anotherNum = i;
				}
			}
		}
		//返回计较结果
		return thisNum - anotherNum;
	}

}

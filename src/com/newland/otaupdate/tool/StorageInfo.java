package com.newland.otaupdate.tool;

public class StorageInfo 
{
	public enum CardType
	{
		USB_STORAGE,External_SD;
	}
	private CardType cardType;
	private String path;
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}

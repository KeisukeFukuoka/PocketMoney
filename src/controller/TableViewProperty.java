package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * TableViewPropertyクラス 
 * TableViewに表示させるためカラム名プロパティを保持
 */
public class TableViewProperty {
	
	private StringProperty category;
	private StringProperty imcome;
	private StringProperty money;
	private StringProperty memo;
	private StringProperty imcomed_at;
	private StringProperty paid_at;
	
	public StringProperty categoryProperty() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = new SimpleStringProperty(category);
	}
	
	public StringProperty imcomeProperty() {
		return imcome;
	}
	
	public void setImcome(String imcome) {
		this.imcome = new SimpleStringProperty(imcome);
	}
	
	public StringProperty moneyProperty() {
		return money;
	}
	
	public void setMoney(String money) {
		this.money = new SimpleStringProperty(money);
	}
	
	public StringProperty memoProperty() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = new SimpleStringProperty(memo);
	}
	
	public StringProperty imcomed_atProperty() {
		return imcomed_at;
	}
	
	public void setImcomed_at(String imcomed_at) {
		this.imcomed_at = new SimpleStringProperty(imcomed_at);
	}
	
	public StringProperty paid_atProperty() {
		return paid_at;
	}
	
	public void setPaid_at(String paid_at) {
		this.paid_at = new SimpleStringProperty(paid_at);
	}
}

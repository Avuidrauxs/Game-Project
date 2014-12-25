package com.umedia.wccgame.DAO;

public class EnglishWords {
	
	private String code,word,advance_code;
	private int column_id;
	
	
	
	
	
	public EnglishWords() {
		super();
	}
	public EnglishWords(String code, String word, String advance_code,
			int column_id) {
		super();
		this.code = code;
		this.word = word;
		this.advance_code = advance_code;
		this.column_id = column_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getAdvance_code() {
		return advance_code;
	}
	public void setAdvance_code(String advance_code) {
		this.advance_code = advance_code;
	}
	public int getColumn_id() {
		return column_id;
	}
	public void setColumn_id(int column_id) {
		this.column_id = column_id;
	}
	@Override
	public String toString() {
		return "EnglishWords [Colum_id=" + column_id + ", word=" + word
				+ ", advance_code=" + advance_code + ", code =" + code
				+ "]";
	}

	
	
	
	

}

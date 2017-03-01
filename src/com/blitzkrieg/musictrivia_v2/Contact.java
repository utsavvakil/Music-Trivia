package com.blitzkrieg.musictrivia_v2;

public class Contact {
	
	//private variables
	int _id;
	String _name;
	int _score;
	
	// Empty constructor
	public Contact(){
		
	}
	// constructor
	public Contact(int id, String name, int _score){
		this._id = id;
		this._name = name;
		this._score = _score;
	}
	
	// constructor
	public Contact(String name, int _score){
		this._name = name;
		this._score = _score;
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	
	// getting phone number
	public int getScore(){
		return this._score;
	}
	
	// setting phone number
	public void setScore(int score){
		this._score = score;
	}

}

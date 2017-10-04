package me.crastchet.pattern_observer;

import java.util.ArrayList;

public abstract class Observable {

	ArrayList<Observer> observers;
	
	public Observable() {
		observers = new ArrayList<Observer>();
		
	}
	
	public void addObserver(Observer obs) {
		observers.add( obs );
	}

	public void removeObserver(Observer obs) {
		observers.remove(obs);
	}

	public void notifyObservers() {
		for(Observer o : this.observers)
			o.update(this);
	}
	
	public abstract void init();
	public abstract void stop();

}

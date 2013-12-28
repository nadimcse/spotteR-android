package com.butterfly.spotter.android.singleton;

/** 
*
* @author Nadim
* @since  Dec 17, 2013
*
*/

public enum SingletonAccess {
     INSTANCE;
     private Singleton singleton;
     
     public void setSingleton(Singleton singleton) {
    	 this.singleton = singleton;
     }
     
     public Singleton getSingleton() {
    	 return singleton;
     }
}

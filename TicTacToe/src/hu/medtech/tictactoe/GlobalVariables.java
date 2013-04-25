package hu.medtech.tictactoe;

import android.app.Application;

public class GlobalVariables extends Application {

	ConnectionService mConnectionService = null;
	
	@Override
	public void onCreate() {
	
	}
	
	public ConnectionService getConnectionService() {
		return mConnectionService;
	}
	
	public void setConnectionService(ConnectionService mConnectionService) {
		this.mConnectionService = mConnectionService;
	}
	
}

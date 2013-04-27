package hu.medtech.tictactoe;

import android.app.Application;

public class GlobalVariables extends Application {

	private ConnectionService mConnectionService = null;
	private int symbol;
	
	@Override
	public void onCreate() {

	}

	public ConnectionService getConnectionService() {
		return mConnectionService;
	}

	public void setConnectionService(ConnectionService mConnectionService) {
		this.mConnectionService = mConnectionService;
	}
	
	public void setSymbol(int symbol) {
		this.symbol = symbol;
	}
	
	public int getSymbol() {
		return symbol;
	}

}

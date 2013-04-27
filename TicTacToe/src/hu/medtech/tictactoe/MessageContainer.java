package hu.medtech.tictactoe;

import java.io.Serializable;

public class MessageContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static int MESSAGE_NEW_GAME = 1;
	public final static int MESSAGE_SYMBOL_X = 2;
	public final static int MESSAGE_SYMBOL_O = 3;
	public final static int MESSAGE_ACK = 4;
	public final static int MESSAGE_WIN = 5;
	public final static int MESSAGE_GAME_OVER = 6;

	private int message;
	private int coords[];

	public MessageContainer() {
		this.setMessage(1);
		this.setCoords(new int[2]);
	}

	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int coords[]) {
		this.coords = coords;
	}

}

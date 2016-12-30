package nl.itopia.modwillie.data.data;

/**
 * An enum for the Action of a ChannelData object. The value of '0' and '1' come from the Arduino sketch. 
 * @author Robin de Jong
 */
public enum ChannelAction {
	RING, REGISTER, INVALID;
	
	public static final ChannelAction get(int action) {
		switch(action) {
			case 0:
				return RING;
			case 1:
				return REGISTER;
			default:
				return INVALID;
		}
	}
}

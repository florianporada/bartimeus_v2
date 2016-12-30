package nl.itopia.modwillie.data.data;

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

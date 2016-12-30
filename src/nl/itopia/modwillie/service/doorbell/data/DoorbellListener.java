package nl.itopia.modwillie.service.doorbell.data;

import nl.itopia.modwillie.data.data.ChannelData;

public interface DoorbellListener {
	void ring(ChannelData data);
}

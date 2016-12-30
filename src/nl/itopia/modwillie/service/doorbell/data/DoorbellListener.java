package nl.itopia.modwillie.service.doorbell.data;

import nl.itopia.modwillie.data.data.ChannelData;

/**
 * A interface that is used for processing the data gathered from the Arduino
 * @author Robin de Jong
 */
public interface DoorbellListener {
	void ring(ChannelData data);
}

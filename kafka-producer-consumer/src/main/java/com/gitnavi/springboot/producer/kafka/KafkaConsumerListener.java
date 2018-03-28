package com.gitnavi.springboot.producer.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Log4j2
@Component
public class KafkaConsumerListener {

	private static final int COUNT = 20;

	private CountDownLatch latch = new CountDownLatch(COUNT);

	public CountDownLatch getLatch() {
		return latch;
	}

	/**
	 * topics 需要提前创建好
	 *
	 * @param message
	 * @param partitions
	 * @param offsets
	 */
	@KafkaListener(id = "foo", topics = "my-topic-test")
	public void receive(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
		log.info("received message='{}' with partition-offset='{}'", message, partitions + "-" + offsets);
		latch.countDown();
	}
}

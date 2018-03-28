package com.gitnavi.springboot.consumer.kafka;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
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
	 * 一般用法
	 * topics 需要提前创建好
	 *
	 * @param message
	 * @param partitions
	 * @param offsets
	 */
	@KafkaListener(id = "foo", topics = "${custom.variable.kafka.topic}")
	public void receive(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
		log.info("received message='{}' with partition-offset='{}'", message, partitions + "-" + offsets);
		latch.countDown();
	}
	
	//======================================================其他用法======================================================

	@KafkaListener(id = "foo1", topicPattern = "myTopic1")
	public void listen(@Payload String foo,
	                   @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
	                   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
	                   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
	                   @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts
	) {
		//TODO
	}

	@KafkaListener(id = "foo2", topicPartitions =
			{@TopicPartition(topic = "topic1", partitions = {"0", "1"}),
					@TopicPartition(topic = "topic2", partitions = "0",
							partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
			})
	public void listen(ConsumerRecord<?, ?> record) {
		//TODO
	}

	@KafkaListener(id = "foo3", topics = "myTopic")
	public void listen(String data, Acknowledgment ack) {
		//TODO
		ack.acknowledge();
	}

	@KafkaListener(id = "foo4", topics = "myTopic")
	static class MultiListenerBean {

		@KafkaHandler
		public void listen(String foo) {
			//TODO
		}

		@KafkaHandler
		public void listen(Integer bar) {
			//TODO
		}

	}

	@KafkaListener(id = "foo5", topics = "annotated")
	public void listen4(@Payload String foo, Acknowledgment ack) {

	}

	@KafkaListener(id = "foo6", topics = {"c1", "c2", "c3"})
	public void listen_C(ConsumerRecord<String, String> record) {

	}


}

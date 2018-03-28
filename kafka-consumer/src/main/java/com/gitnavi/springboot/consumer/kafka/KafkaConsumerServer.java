package com.gitnavi.springboot.consumer.kafka;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

@Log4j2
public class KafkaConsumerServer implements MessageListener<String, String> {

	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		String topic = record.topic();
		String key = record.key();
		String value = record.value();
		long offset = record.offset();
		int partition = record.partition();
		log.info("----------------KafkaConsumerServer----------------topic=[{}]", topic);
		log.info("----------------KafkaConsumerServer----------------value=[{}]", value);
		log.info("----------------KafkaConsumerServer----------------key=[{}]", key);
		log.info("----------------KafkaConsumerServer----------------offset=[{}]", offset);
		log.info("----------------KafkaConsumerServer----------------partition=[{}]", partition);
	}
}


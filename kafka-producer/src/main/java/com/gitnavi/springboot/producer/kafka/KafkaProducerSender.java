package com.gitnavi.springboot.producer.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

@Log4j2
@Component
public class KafkaProducerSender {

	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String topic, String message) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("unable to send message='{}'", message, ex);
			}
		});

	}
}

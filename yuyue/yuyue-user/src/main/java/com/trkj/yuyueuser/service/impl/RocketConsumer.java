package com.trkj.yuyueuser.service.impl;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

//consumeMode = ConsumeMode.ORDERLY,messageModel = MessageModel.BROADCASTING，这样会报 messageModel BROADCASTING does not support ORDERLY message!

//consumeMode 消费模式,默认值 ConsumeMode.CONCURRENTLY 并行处理;ConsumeMode.ORDERLY 按顺序处理(广播模式下消息不能顺序消费)
//messageModel 消息模型,默认值 MessageModel.CLUSTERING 集群;MessageModel.BROADCASTING 广播
//集群模式下消费者平摊消息;广播模式消费下消费者都消费到MQ_TOPIC的所有消息。
@Service("RocketConsumer")
@RocketMQMessageListener(consumerGroup = "testmq", topic = RocketContants.TEST_TOPIC,consumeMode = ConsumeMode.CONCURRENTLY,messageModel = MessageModel.BROADCASTING)
public class RocketConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    int i=0;
    @Override
    public void onMessage(MessageExt message) {

        String msg= null;
        try {
            msg = new String(message.getBody(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.err.println("RocketConsumer 接收到消息：tag:" + message.getTags()+"   count:"+(i++)+"  QueueId:"+message.getQueueId()+" 消息body："+msg+" ="+message);
    }
    @SneakyThrows
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
       /* System.out.println("设置消费起始位");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()-1000*1*3600));*/
        // consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 设置生产者实例名称
        consumer.setInstanceName("RocketConsumer");
        // 订阅TEST_TOPIC1下Tag的所有消息 "*"表示订阅主题中的所有
        consumer.subscribe("TEST_TOPIC1", MessageSelector.byTag("*"));
        consumer.subscribe("TEST_TOPIC2", MessageSelector.byTag("*"));
    }
}
package com.trkj.yuyuemessage.service.impl;

import com.trkj.service.MessageService;
import com.trkj.util.BeanTools;
import com.trkj.yuyuemessage.entity.MessageEntity;
import com.trkj.dto.message.MessageDto;
import com.trkj.yuyuemessage.dao.MessageDao;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统消息表(Message)表服务实现类
 *
 * @author makejava
 * @since 2023-01-28 19:21:07
 */
@Service("messageService")
@DubboService
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    int i = 0;

    /**
     * 新增数据
     *
     * @param messageDto 实例对象
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean insert(MessageDto messageDto) {
        System.out.println("in MessageServiceImpl.....................");
        rocketMQTemplate.getProducer().setDefaultTopicQueueNums(4);

        log.info("MessageDto:{}",messageDto);
        String message = messageDto.getMeContent();
        //为具有给定负载的消息创建一个新生成器。
        Message info= MessageBuilder.withPayload("emp:"+message+i).build();

        //info.setBody();
        //info.getHeaders().put("level","a1");
        Map<String, Object> headers = new HashMap<>();
        headers.put("level", "a1") ;

        Map<String, Object> headers2 = new HashMap<>();
        headers2.put("level", "a2") ;
        Map<String, Object> headers3 = new HashMap<>();
        headers3.put("level", "e1") ;
        Map<String, Object> headers4 = new HashMap<>();
        headers4.put("level", "e2") ;
        //
        // info.putUserProperty("level","a");
        //rocketMQTemplate.convertAndSend(info);
        /*
          syncSend，发送同步消息；
          asyncSend，发送异步消息；
          syncSendOrderly，发送同步顺序消息；
          asyncSendOrderly，发送异步顺序消息；
          sendOneWayOrderly，发送单向顺序消息；
          convertAndSend，发送普通消息；

          destination：消息主题
          hashKey：用来计算决定消息发送到哪个消息队列 一般是订单ID，产品ID等
       */
        if(message.startsWith("sqla1")) {
            System.out.println("sqla1");
            //序列化参数 并发送指定目的地 第一个参数 目的地（主题），第二个参数 有效载荷
            rocketMQTemplate.convertAndSend("TEST_TOPIC", info, headers);
        }else if(message.startsWith("sqla2")){
            System.out.println("sqla2");
            rocketMQTemplate.convertAndSend("TEST_TOPIC", info, headers2);
        }else if(message.startsWith("emp1")){
            System.out.println("emp");
            rocketMQTemplate.convertAndSend("TEST_TOPIC:emp", info,headers3);
        }else if(message.startsWith("emp2")){
            System.out.println("emp");
            rocketMQTemplate.convertAndSend("TEST_TOPIC:emp", info,headers4);
        }
        else{
            System.out.println("nosql");
            rocketMQTemplate.convertAndSend("TEST_TOPIC", "TEST_TOPIC:"+info);
            rocketMQTemplate.convertAndSend("TEST_TOPIC1", "TEST_TOPIC1:"+info);
            rocketMQTemplate.convertAndSend("TEST_TOPIC2", "TEST_TOPIC2:"+info);
        }
        // rocketMQTemplate.convertAndSend("TEST_TOPIC:dept", MessageBuilder.withPayload("dept:"+message+i).build());
        // }
        i++;

        messageDto.setMeState(1);
        messageDto.setSendTime(new Date());
        int count=this.messageDao.insert(messageDto);
        return count>0;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param meId 主键
     * @return 实例对象
     */
    @Override
    public MessageDto queryById(Integer meId) {
		MessageEntity entity=this.messageDao.queryById(meId);
		MessageDto dto=new MessageDto() ;
		BeanTools.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param messageDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<MessageDto> queryByPage(MessageDto messageDto, int currentPage, int pageSize) {
		Page<MessageEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<MessageEntity> List=this.messageDao.queryAll(messageDto);
		PageInfo<MessageEntity> pageInfo=pageEntity.toPageInfo();
		Page<MessageDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,MessageDto.class);
        PageInfo<MessageDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 修改数据
     *
     * @param messageDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(MessageDto messageDto) {
        int count=this.messageDao.update(messageDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param meId 主键
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean deleteById(Integer meId) {
        return this.messageDao.deleteById(meId) > 0;
    }
}

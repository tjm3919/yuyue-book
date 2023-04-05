package com.trkj.yuyuemessage.dao;

import com.trkj.yuyuemessage.entity.MessageEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.message.MessageDto;
import java.util.List;

/**
 * 系统消息表(Message)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-28 19:21:06
 */
 @Mapper
public interface MessageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param meId 主键
     * @return 实例对象
     */
    MessageEntity queryById(Integer meId);

    /**
     * 查询指定行数据
     *
     * @param messageDto 查询条件
     * @return 对象列表
     */
    List<MessageEntity> queryAll(MessageDto messageDto);

    /**
     * 统计总行数
     *
     * @param messageDto 查询条件
     * @return 总行数
     */
    int count(MessageDto messageDto);

    /**
     * 新增数据
     *
     * @param messageDto 实例对象
     * @return 影响行数
     */
    int insert(MessageDto messageDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MessageDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MessageDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MessageDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MessageDto> entities);

    /**
     * 修改数据
     *
     * @param messageDto 实例对象
     * @return 影响行数
     */
    int update(MessageDto messageDto);

    /**
     * 通过主键删除数据
     *
     * @param meId 主键
     * @return 影响行数
     */
    int deleteById(Integer meId);

}


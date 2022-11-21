package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("select * from MESSAGES")
    List<ChatMessage> getAllMessages();

    @Insert("insert into MESSAGES (messagetext, username) " +
    "values (#{messageText}, #{username})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insertMessage(ChatMessage message);
}

package com.nowcoder.dao;

import com.nowcoder.model.Comment;
import com.nowcoder.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static com.nowcoder.dao.CommentDAO.TABLE_NAME;

/**
 * Created by Administrator on 2017/7/25.
 */
@Mapper
public interface CommentDAO {
    String TABLE_NAME = "comment";
    String INSERT_FIELDS = "user_id,content, created_date, entity_id, entity_type,status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;
    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId},#{content},#{createdDate},#{entityId},#{entityType},#{status})"})
    int addComment(Comment comment);

    @Select({"select",SELECT_FIELDS, "from", TABLE_NAME, "where entity_id=#{entityId} and entity_type=#{entityType} order by id desc" })
    List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

    @Update({"update ", TABLE_NAME, "set status=#{status} where entity_id=#{entityId} and entity_type=#{entityType"})
    void updateStatus(@Param("entityId") int entityId, @Param("entityType") int entityType,  @Param("status") int status);

    @Select({"select count(id) from ", TABLE_NAME,"where entity_id=#{entityId} and entity_type=#{entityType} order by id desc"})
    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);
}

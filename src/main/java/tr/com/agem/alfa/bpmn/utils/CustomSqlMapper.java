package tr.com.agem.alfa.bpmn.utils;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public interface CustomSqlMapper 
{
	@Select("select RES.ID_ as taskId, I.TYPE_ as identityLinkType from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ WHERE not I.TYPE_ in ('candidate','owner','assignee') and ( I.USER_ID_ =  #{username} ) order by RES.ID_ asc")
	List<Map<String, Object>> selectUserRelatedTasks(String username);

	@Select("select RES.ID_ as taskId, I.TYPE_ as identityLinkType from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ WHERE not I.TYPE_ in ('candidate','owner','assignee') and ( I.GROUP_ID_ =  #{group} ) order by RES.ID_ asc")
	List<Map<String, Object>> selectGroupRelatedTasks(String group);

}

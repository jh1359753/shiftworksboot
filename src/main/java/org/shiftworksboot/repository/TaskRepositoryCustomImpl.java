package org.shiftworksboot.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.java.Log;
import org.shiftworksboot.constant.TaskDept;
import org.shiftworksboot.dto.TaskDto;
import org.shiftworksboot.entity.QSchedule;
import org.shiftworksboot.entity.QTask;
import org.shiftworksboot.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Log
public class TaskRepositoryCustomImpl implements TaskRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public TaskRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Task> getListWithPaging(TaskDto taskDto, Pageable pageable) {

        log.info("오프셋:" + pageable.getOffset());

        QTask qTask = QTask.task;
        List<Task> taskList = queryFactory
                .selectFrom(qTask)
                .where(searchTitle(taskDto.getTask_title()),
                        searchContent(taskDto.getTask_content()),
                        selectDept(taskDto.getDept_id()))
                .orderBy(QTask.task.task_id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<Task>(taskList, pageable, taskList.size());
    }

    @Override
    public Integer getTotalCount(TaskDto taskDto) {
        QTask qTask = QTask.task;
        List<Task> taskList = queryFactory
                .selectFrom(qTask)
                .where(searchTitle(taskDto.getTask_title()),
                        searchContent(taskDto.getTask_content()),
                        selectDept(taskDto.getDept_id()))
                .fetch();
        return taskList.size();
    }


    // where절 조건 입력을 위한 BooleanExpression

    // 제목으로 검색
    private BooleanExpression searchTitle(String task_title) {
        log.info(task_title);
        return StringUtils.hasText(task_title) ? QTask.task.task_title.like("%"+task_title+"%") : null;
    }
    // 내용으로 검색
    private BooleanExpression searchContent(String task_content) {
        log.info(task_content);
        return StringUtils.hasText(task_content) ? QTask.task.task_content.like("%"+task_content+"%") : null;
    }

    // 부서별 출력
    private BooleanExpression selectDept(TaskDept taskDept) {
        return ObjectUtils.isEmpty(taskDept) ? null : QTask.task.dept_id.eq(taskDept);
    }

}

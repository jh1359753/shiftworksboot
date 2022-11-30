package org.shiftworksboot.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.constant.TaskDept;
import org.shiftworksboot.entity.QTask;
import org.shiftworksboot.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Log
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("업무 등록 테스트")
    public void insertTaskTest() {
        Task task = new Task();
        task.setTask_content("test task");

        Task savedTask = taskRepository.save(task);
        log.info(savedTask.toString());

    }

    @Test
    @DisplayName("검색 쿼리 동적 생성 테스트")
    public void dslTest() {
        for(int i=0; i<5; i++) {
            Task task = new Task();
            task.setTask_title("test" + i);
            task.setTask_content("test task");
            task.setDept_id(TaskDept.DEPT1);

            taskRepository.save(task);
        }

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTask qTask = QTask.task;
        JPAQuery<Task> query = queryFactory.selectFrom(qTask)
                .where(qTask.dept_id.eq(TaskDept.DEPT1))
                .where(qTask.task_title.like("1"));

        List<Task> list = query.fetch();

        for (Task t : list) {
            log.info(t.toString());
        }

    }

}
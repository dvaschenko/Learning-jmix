package com.company.demo.security;

import com.company.demo.entity.Task;
import com.company.demo.entity.TaskStatus;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "RequestTasksOnly", code = RequestTasksOnlyRole.CODE)
public interface RequestTasksOnlyRole {
    String CODE = "request-tasks-only";

    @PredicateRowLevelPolicy(entityClass = Task.class, actions = RowLevelPolicyAction.CREATE)
    default RowLevelBiPredicate<Task, ApplicationContext> taskPredicate() {
        return (task, applicationContext) -> {
            return task.getStatus() == TaskStatus.REQUEST;
        };
    }


    @PredicateRowLevelPolicy(entityClass = Task.class, actions = RowLevelPolicyAction.READ)
    default RowLevelBiPredicate<Task, ApplicationContext> taskPredicate1() {
        return (task, applicationContext) -> {
            return true;
        };
    }
}
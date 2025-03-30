package com.company.demo.security;

import com.company.demo.entity.Project;
import com.company.demo.entity.Task;
import com.company.demo.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "performer", code = PerformerRole.CODE, scope = "UI")
public interface PerformerRole {
    String CODE = "performer";

    @MenuPolicy(menuIds = "Task_.list")
    @ViewPolicy(viewIds = {"Task_.list", "Task_.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Project.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.READ)
    void project();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "status", action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = Task.class, attributes = {"id", "author", "peformer", "controller", "name", "efforts", "project", "concat"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Task.class, actions = {EntityPolicyAction.UPDATE, EntityPolicyAction.READ})
    void task();
}
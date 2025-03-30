package com.company.demo.security;

import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "availableStatuses", code = AvailableStatusesRole.CODE, scope = "UI")
public interface AvailableStatusesRole {
    String CODE = "available-statuses";

}
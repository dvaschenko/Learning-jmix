package com.company.demo.app;

import com.company.demo.entity.Project;
import com.company.demo.entity.ProjectStats;
import io.jmix.core.DataManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatsService {

    private final DataManager dataManager;

    public StatsService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<ProjectStats> getStats() {
        List<Project> projects = dataManager.load(Project.class).all().list();
        return projects.stream()
                .map(project -> {
                    ProjectStats stats = dataManager.create(ProjectStats.class);

                    stats.setId(project.getId());
                    stats.setProjectName(project.getName());
                    stats.setTasksCount(project.getTasks().size());

                    return stats;
                })
                .toList();

    }
}
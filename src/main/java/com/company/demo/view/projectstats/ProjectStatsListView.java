package com.company.demo.view.projectstats;

import com.company.demo.app.StatsService;
import com.company.demo.entity.ProjectStats;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "project-stats", layout = MainView.class)
@ViewController(id = "ProjectStats.list")
@ViewDescriptor(path = "project-stats-list-view.xml")
@LookupComponent("projectStatsDataGrid")
@DialogMode(width = "50em")
public class ProjectStatsListView extends StandardListView<ProjectStats> {

    @Autowired
    private StatsService statsService;

    @Install(to = "projectStatsDl", target = Target.DATA_LOADER)
    protected List<ProjectStats> projectStatsDlLoadDelegate(LoadContext<ProjectStats> loadContext) {
       return statsService.getStats();
    }
}

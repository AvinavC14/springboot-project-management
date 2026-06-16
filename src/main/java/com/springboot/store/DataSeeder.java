package com.springboot.store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.store.entity.Project;
import com.springboot.store.entity.SoftwareEngineer;
import com.springboot.store.repositories.ProjectRepository;
import com.springboot.store.repositories.SoftwareEngineerRepository;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProjectRepository projectRepo;
    private final SoftwareEngineerRepository engineerRepo;

    public DataSeeder(ProjectRepository projectRepo,
                      SoftwareEngineerRepository engineerRepo) {
        this.projectRepo = projectRepo;
        this.engineerRepo = engineerRepo;
    }

    @Override
    public void run(String... args) {
        if (engineerRepo.count() > 0) {
            System.out.println("Data already seeded, skipping...");
            return;
        }

        System.out.println("Seeding 10,000 engineers...");

        List<Project> projects = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            projects.add(projectRepo.save(new Project("Project Alpha " + i)));
        }

        String[] stacks = {"Java", "React", "Python", "Angular", "Node.js"};
        List<SoftwareEngineer> batch = new ArrayList<>();

        for (int i = 1; i <= 10000; i++) {
            Project p = projects.get(i % projects.size());
            String stack = stacks[i % stacks.length];
            batch.add(new SoftwareEngineer("Engineer " + i, stack, p));

            if (i % 500 == 0) {
                engineerRepo.saveAll(batch);
                batch.clear();
                System.out.println("Seeded " + i + " engineers...");
            }
        }

        System.out.println("Seeding complete!");
    }
}
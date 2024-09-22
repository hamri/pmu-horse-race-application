package com.pmu.race.db.jpa.repository;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

@Import(RaceManagerRepositoryConfiguration.class)
@SpringBootConfiguration
public class RepositoryTestConfiguration {

    @Value("classpath:/")
    private Resource workingDirectory;

    @Bean
    public DataSource dataSource() throws IOException {
        final File workingDirectoryFile = workingDirectory.getFile();
        return EmbeddedPostgres.builder().setOverrideWorkingDirectory(workingDirectoryFile).start().getPostgresDatabase();
    }
}

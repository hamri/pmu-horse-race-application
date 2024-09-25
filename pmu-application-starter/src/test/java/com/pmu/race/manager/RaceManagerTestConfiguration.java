package com.pmu.race.manager;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

@Configuration
public class RaceManagerTestConfiguration {

    @Value("classpath:/")
    private Resource workingDirectory;

    @Bean
    public DataSource dataSource() throws IOException {
        final File workingDirectoryFile = workingDirectory.getFile();
        return EmbeddedPostgres.builder().setOverrideWorkingDirectory(workingDirectoryFile).start()
                .getPostgresDatabase();
    }
}

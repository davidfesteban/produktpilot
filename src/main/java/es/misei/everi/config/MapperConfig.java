package es.misei.everi.config;

import es.misei.everi.domain.mapper.BillingMapper;
import es.misei.everi.domain.mapper.material.MaterialMapper;
import es.misei.everi.domain.mapper.material.SimpleMaterialMapper;
import es.misei.everi.domain.mapper.organization.OrganizationMapper;
import es.misei.everi.domain.mapper.organization.SimpleOrganizationMapper;
import es.misei.everi.domain.mapper.project.ProjectMapper;
import es.misei.everi.domain.mapper.project.SimpleProjectMapper;
import es.misei.everi.domain.mapper.user.SimpleUserMapper;
import es.misei.everi.domain.mapper.user.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public BillingMapper billingMapper() {
        return BillingMapper.INSTANCE;
    }

    @Bean
    public MaterialMapper materialMapper() {
        return MaterialMapper.INSTANCE;
    }

    @Bean
    public OrganizationMapper organizationMapper() {
        return OrganizationMapper.INSTANCE;
    }

    @Bean
    public ProjectMapper projectMapper() {
        return ProjectMapper.INSTANCE;
    }

    @Bean
    public UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }

    @Bean
    public SimpleMaterialMapper simpleMaterialMapper() {
        return SimpleMaterialMapper.INSTANCE;
    }

    @Bean
    public SimpleOrganizationMapper simpleOrganizationMapper() {
        return SimpleOrganizationMapper.INSTANCE;
    }

    @Bean
    public SimpleProjectMapper simpleProjectMapper() {
        return SimpleProjectMapper.INSTANCE;
    }

    @Bean
    public SimpleUserMapper simpleUserMapper() {
        return SimpleUserMapper.INSTANCE;
    }
}

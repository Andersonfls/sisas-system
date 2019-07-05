package com.minea.sisas.config;

import com.minea.sisas.domain.*;
import com.minea.sisas.repository.UserRepository;
import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(User.class.getName(), jcacheConfiguration);
            cm.createCache(Midia.class.getName(), jcacheConfiguration);
            cm.createCache(Midia.class.getName() + ".tipoObras", jcacheConfiguration);
            cm.createCache(TipoObra.class.getName(), jcacheConfiguration);
            cm.createCache(TipoObra.class.getName() + ".obras", jcacheConfiguration);
            cm.createCache(Editora.class.getName(), jcacheConfiguration);
            cm.createCache(Editora.class.getName() + ".obras", jcacheConfiguration);
            cm.createCache(Obra.class.getName(), jcacheConfiguration);
            cm.createCache(Obra.class.getName() + ".autorObras", jcacheConfiguration);
            cm.createCache(Autor.class.getName(), jcacheConfiguration);
            cm.createCache(Autor.class.getName() + ".autorObras", jcacheConfiguration);
            cm.createCache(AutorObra.class.getName(), jcacheConfiguration);
            cm.createCache(TipoEspaco.class.getName(), jcacheConfiguration);
            cm.createCache(TipoEspaco.class.getName() + ".espacos", jcacheConfiguration);
            cm.createCache(Espaco.class.getName(), jcacheConfiguration);
            cm.createCache(Emprestimo.class.getName(), jcacheConfiguration);
            cm.createCache(User.class.getName() + ".emprestimos", jcacheConfiguration);
            cm.createCache(Exemplar.class.getName(), jcacheConfiguration);
            cm.createCache(Exemplar.class.getName() + ".emprestimos", jcacheConfiguration);
            cm.createCache(Obra.class.getName() + ".exemplares", jcacheConfiguration);
            cm.createCache(Espaco.class.getName() + ".exemplares", jcacheConfiguration);
            cm.createCache(Reserva.class.getName(), jcacheConfiguration);
            cm.createCache(User.class.getName() + ".reservas", jcacheConfiguration);
            cm.createCache(Obra.class.getName() + ".reservas", jcacheConfiguration);

            // jhipster-needle-ehcache-add-entry
        };
    }
}

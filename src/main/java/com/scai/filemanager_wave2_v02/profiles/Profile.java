package com.scai.filemanager_wave2_v02.profiles;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Profile {
    @Configuration
    @Profile("integ")
    public class ConfigurazioneIntegrazione {
        // Configura i bean specifici per il profilo "integ"
    }

    @Configuration
    @Profile("test")
    public class ConfigurazioneTest {
        // Configura i bean specifici per il profilo "test"
    }

    @Configuration
    @Profile("prod")
    public class ConfigurazioneProduzione {
        // Configura i bean specifici per il profilo "prod"

    }
}

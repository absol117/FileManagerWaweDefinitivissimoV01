package com.scai.filemanager_wave2_v02.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "file-ops")
public class FileConfig {
    private boolean abilitate;
    private String cartellaSorgente;
    private String cartellaDestinazione;

}

FROM itzg/mc-proxy

# Définir les variables d'environnement
ENV TYPE=VELOCITY
ENV VERSION=1.20.4
ENV DEBUG=false
ENV ENABLE_RCON=true
ENV PLUGINS_FILE=/config/plugins/plugins.txt

# Copier les fichiers de configuration
COPY config/velocity.toml /config/velocity.toml
COPY config/forwarding.secret /config/forwarding.secret
COPY ./config/plugins/plugins.txt /config/plugins/plugins.txt 
COPY ./config/connectors.yml /plugins/rustyconnector-velocity/connectors.yml
run chown -R 1000:1000 /config
RUN chmod -R 777 /config
# Définir le point de montage pour les volumes
VOLUME /server

# Exposer le port
EXPOSE 25577
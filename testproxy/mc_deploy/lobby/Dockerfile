# mc/Dockerfile
FROM itzg/minecraft-server
# Définir les variables d'environnement
ENV FORCE_WORLD_COPY=false
ENV EULA=TRUE
ENV VERSION=1.20.4
ENV TYPE=PAPER
ENV ONLINE_MODE=FALSE
ENV PLUGINS_FILE=/config/plugins/plugins.txt
ENV WORLD=/data/world/
# Copier les fichiers nécessaires dans l'image
COPY ./config/plugins/plugins.txt /config/plugins/plugins.txt
COPY ./config/plugins/plugin.jar /plugins/plugin.jar  
COPY ./mapLobby/Natural-Lobby /data/world
COPY ./config/paper-global.yml /config/paper-global.yml
# Définir le point de montage pour les volumes
VOLUME /data


WORKDIR /data/world

RUN chown -R 1000:1000 /data/world
RUN chmod -R 777 /data/world

# Commande par défaut pour démarrer le serveur Minecraft
CMD ["start"]
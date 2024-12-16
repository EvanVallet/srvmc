# srv1/Dockerfile
FROM itzg/minecraft-server

# Définir les variables d'environnement
ENV EULA=TRUE
ENV VERSION=1.20.4
ENV TYPE=PAPER
ENV ONLINE_MODE=FALSE
ENV CREATE_CONSOLE_IN_PIPE=true
ENV PLUGINS_FILE=/config/plugins/plugins.txt


COPY ./config/plugins/plugins.txt /config/plugins/plugins.txt  
COPY ./config/paper-global.yml /config/paper-global.yml
# Définir le point de montage pour les volumes


VOLUME /data


# Commande par défaut pour démarrer le serveur Minecraft
CMD ["start"]
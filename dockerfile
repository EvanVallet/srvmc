FROM itzg/minecraft-server

ENV EULA=true
ENV TYPE=PAPER
COPY ./plugin/build/libs/plugin.jar /plugins/

EXPOSE 25565

VOLUME /data

CMD ["start"]
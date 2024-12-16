# FILE: api.py
from flask import Flask, request
import subprocess

app = Flask(__name__)
mapid = []
upserv = []
@app.route('/create_server', methods=['POST'])
def create_server():
    # Récupérer l'UUID envoyé par le plugin
    uuid = request.args.get('uuid')
    for i in mapid:
        if i == uuid:
            #start_server
            return f"Serveur deja crée lancement du server"
    # Afficher l'UUID reçu
    print('UUID reçu :', uuid)

    # Créer le nom du volume en utilisant l'UUID
    volume_name = f'minecraft_data_{uuid}'

    cmd = [
        "docker", "run","-d","--name",f"{uuid}","-p", "25564:25565",
        "-e", "EULA=TRUE", "-e", "ONLINE_MODE=FALSE",
        "-v", f"{volume_name}:/data",
        "itzg/minecraft-server"
    ]

    subprocess.run(cmd, check=True)
    mapid.append(uuid)
    upserv.append(uuid)
    return f"Serveur créé pour l'UUID : {uuid}"


@app.route('/stop_server', methods=['POST'])
def stop_server():
    # Récupérer l'UUID envoyé par le plugin
    uuid = request.args.get('uuid')
    if uuid in upserv:
        # Créer le nom du volume en utilisant l'UUID
        volume_name = f'minecraft_data_{uuid}'

        cmd = [
            "docker", "stop", f"{uuid}"
        ]
        subprocess.run(cmd, check=True)
        upserv.remove(uuid)
        mapid.remove(uuid)
        return f"Serveur {uuid} arrêté"
    return "UUID non trouvé"


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

@app.route('/start_server', methods=['POST'])
def start_server():
    # Récupérer l'UUID envoyé par le plugin
    uuid = request.args.get('uuid')
    for i in upserv:
        if i == uuid:
            start_server
            return f"Serveur deja lancée"
    # Afficher l'UUID reçu
    print('UUID reçu :', uuid)

    # Créer le nom du volume en utilisant l'UUID
    volume_name = f'minecraft_data_{uuid}'

    cmd = [
        "docker", "start", f"{uuid}"
    ]

    subprocess.run(cmd, check=True)
    upserv.append(uuid)
    return f"Serveur créé pour l'UUID : {uuid}"
from flask import Flask, request
import subprocess

app = Flask(__name__)

@app.route('/create_server', methods=['POST'])
def create_server():
    data = request.json
    port = data.get('port')
    if not port:
        return {"error": "Port not specified"}, 400

    server_name = f"server_{port}"
    cmd = [
        "docker", "run", "-d",
        "--name", server_name,
        "-p", f"{port}:25565",
        "-e", "EULA=TRUE",
        "-e", "ONLINE_MODE=FALSE",
        "itzg/minecraft-server"
    ]

    try:
        subprocess.run(cmd, check=True)
        return {"message": f"Server {server_name} created on port {port}"}, 200
    except subprocess.CalledProcessError as e:
        return {"error": str(e)}, 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

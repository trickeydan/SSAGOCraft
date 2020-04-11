import os
import logging
from re import compile, sub

from flask import Flask, request
from mcrcon import MCRcon

USERNAME_REGEX = compile("^[A-Za-z0-9_]{3,16}$")

LOGGER = logging.Logger(__name__)

app = Flask(__name__)

MC_SECRET = os.environ["SSAGOCRAFT_RCON_SECRET"]
SECRET = os.environ["SSAGOCRAFT_API_SECRET"]

@app.before_first_request
def connect():
    LOGGER.error("Connecting to server.")
    app.mcr = MCRcon("127.0.0.1", MC_SECRET)
    app.mcr.connect()


def setup_team(team_string: str, username: str):
    team_name = sub('[^0-9a-zA-Z]+', '', team_string)
    result = app.mcr.command(f"team add {team_name}")
    LOGGER.error(f"Creating team {team_name} -> {result}")

    result2 = app.mcr.command(f"team modify {team_name} prefix \"[{team_string}] \"")
    LOGGER.error(f"Setting {team_name} prefix to {team_string} -> {result2}")

    result3 = app.mcr.command(f"team join {team_name} {username}")
    LOGGER.error(f"Adding {username} to {team_name} -> {result3}")


def whitelist_player(username: str) -> str:
    result = app.mcr.command(f"whitelist add {username}")
    LOGGER.error(f"Whitelisting {username} -> {result}")
    return result

@app.route('/api', methods=['GET'])
def index():
    return "SSAGOCraft API", 200

@app.route('/api/whitelist', methods=['POST'])
def whitelist():
    data = request.get_json()

    if data is None or "username" not in data or "secret" not in data or "club" not in data:
        return "Bad Request.", 400
    else:
        if data["secret"] == SECRET:
           username = data["username"]
           if USERNAME_REGEX.match(username) is not None:
               res = whitelist_player(username)
               setup_team(data["club"], username)
               #if "admin" in data and data["admin"]:
               #    op_player(username)
               return res, 200
           else:
               return "Bad Username", 400
        else:
            return "Unauthorised.", 403

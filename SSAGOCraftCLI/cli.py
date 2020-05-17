#! /opt/minecraft/resources/SSAGOCraft/SSAGOCraftCLI/venv/bin/python
import os
from pathlib import Path

import click
from mcrcon import MCRcon


SERVER_DIR = Path("/opt/minecraft/servers/")
assert SERVER_DIR.exists() and SERVER_DIR.is_dir()

SERVERS = [path.parts[-1] for path in SERVER_DIR.iterdir()]

@click.command()
@click.option('--server', default='vanilla', help='Name of Server', type=click.Choice(SERVERS))
@click.argument('command')
def execute(server, command):
    server_path = SERVER_DIR.joinpath(server)
    properties_path = server_path.joinpath("server.properties")
    
    properties = {}
    with properties_path.open() as fh:
        for line in fh.read().split("\n"):
            parts = line.split("=")
            if len(parts) == 2:
                properties[parts[0]] = parts[1]
    with MCRcon("127.0.0.1", properties["rcon.password"], port=int(properties["rcon.port"])) as mcr:
        print(mcr.command(command))
if __name__ == '__main__':
    execute()

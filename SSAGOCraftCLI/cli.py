#! /opt/minecraft/SSAGOCraft/SSAGOCraftCLI/venv/bin/python3
import os
from sys import argv

import click
from mcrcon import MCRcon

MC_SECRET = os.environ["SSAGOCRAFT_RCON_SECRET"]
SERVERS = {"vanilla": ("127.0.0.1", 25575)}

@click.command()
@click.option('--server', default='vanilla', help='Name of Server', type=click.Choice(SERVERS.keys()))
@click.argument('command')
def execute(server, command):
    info = SERVERS[server]
    with MCRcon(info[0], MC_SECRET, port=info[1]) as mcr:
        print(mcr.command(command))
if __name__ == '__main__':
    execute()



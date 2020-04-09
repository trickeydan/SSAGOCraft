#! /opt/minecraft/SSAGOCraft/SSAGOCraftCLI/venv/bin/python3
import os
from sys import argv

from mcrcon import MCRcon

MC_SECRET = os.environ["SSAGOCRAFT_RCON_SECRET"]

if len(argv) == 1:
    command = input("Enter command: ")
else:
    command = " ".join(argv[1:])
with MCRcon("127.0.0.1", MC_SECRET) as mcr:
    print(mcr.command(command))
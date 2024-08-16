import scripts.console # Register Consoles
from scripts.bump.Config import Config
from pyucc import console, colors
import os

Config.handle_input()
Config.find()
Config.update_version()
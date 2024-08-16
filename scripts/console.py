from pyucc import console, colors, symbols
from typing import List

@console.register(identifier="start")
def start(*values, **_):
  # [starting]
  buff: List[str] = []
  buff.append(f"{colors.white}[{colors.vibrant_blue}starting{colors.white}]{symbols.reset}")
  
  # [starting] {*values}
  buff.extend(values)
  console.cprint(*buff)

@console.register(identifier="done")
def done(*values, **_):
  # [done]
  buff: List[str] = []
  buff.append(f"{colors.white}[{colors.vibrant_green}done{colors.white}]{symbols.reset}")
  
  # [done] {*values}
  buff.extend(values)
  console.cprint(*buff)

@console.register(identifier="fail")
def fail(*values, **_):
  # [fail]
  buff: List[str] = []
  buff.append(f"{colors.white}[{colors.vibrant_red}fail{colors.white}]{symbols.reset}")
  
  # [fail] {*values}
  buff.extend(values)
  console.cprint(*buff)
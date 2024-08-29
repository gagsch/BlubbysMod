import pathlib
from pyucc import console, colors, symbols
from typing import Union, Literal
import sys
from scripts.jproperties import Properties

class Config:
  _type: Literal["major", "minor", "patch"] = "patch"
  _job: Literal["inference", "exact"] = "inference"
  _path: Union[pathlib.Path, None] = None
  
  def __init__(self):
    pass

  @classmethod
  def handle_input(cls):
    """
    Handles command input i.e. major, minor, patch
    """
    try:
      _in = sys.argv[1].lower()
    except IndexError:
      cls._job = "inference"
      cls._type = "patch"
      return

    if _in not in ["major", "minor", "patch"]:
      try:
        major, minor, patch = _in.strip("v").split(".")
        int(major); int(minor); int(patch)
      except:
        console.fail(f" Input => {colors.vibrant_red}{_in}{symbols.reset}\n\tChange to either => {colors.vibrant_yellow}[major, minor, patch]{symbols.reset}\n\tformat-able string such as {colors.vibrant_yellow}v.5.1.2")
        quit()      
      cls._job = "exact"
      return
    
    cls._job = "inference"
    cls._type = _in


  @classmethod
  def find(cls, parent_limit: int = 3) -> pathlib.Path:
    """
    Attempts to locate the location of gradle.properties, returns a pathlib.Path object
    """
    console.start(f"Attempting to locate {colors.vibrant_violet}gradle.properties{symbols.reset}")
    _root = pathlib.Path(".").resolve()
    cls._path = None
    for _ in range(parent_limit):
      cls._path = [x for x in _root.glob("./*")]
      if not cls._path or len(cls._path) > 1:
        _root = _root.parent
        cls._path = None
        continue
      break
    if not cls._path[0]:
      return None
    
    cls._path = cls._path[0] / "gradle.properties"

    console.done(f"Found {colors.vibrant_violet}gradle.properties{symbols.reset} in {colors.vibrant_orange}{cls._path.parent.absolute()}")
    return cls._path
  
  @classmethod
  def get(cls):
    p = Properties()
    p.load(open(cls._path.absolute(), "rb"), "utf-8", metadoc=True)
    return p
    
    

  @classmethod
  def update_version(cls):
    props: Properties = cls.get()

    if len(sys.argv) < 2:
      _in = props.properties["mod_version"]
    else:
      _in = sys.argv[1]    

    if cls._job == "exact":
      props.properties["mod_version"] = _in.strip("v")

    if cls._job == "inference":    
      major, minor, patch = [int(x) for x in props.properties["mod_version"].split('.')]
      match cls._type:
        case "major":
          major += 1
          minor = 0
          patch = 0
        case "minor":
          minor += 1
          patch = 0
        case "patch":
          patch += 1

      props.properties["mod_version"]= ".".join([str(major), str(minor), str(patch)])
    
    with open(cls._path.absolute(), "r+b") as f:
      f.seek(0)
      f.truncate(0)
      props.store(f, encoding="utf-8", strip_meta=False)
    
    console.done(f"Changed Version: {colors.vibrant_blue}{_in} {symbols.reset}=> {colors.vibrant_green}v{props.properties['mod_version']}")



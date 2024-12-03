# Blubby's Mod
![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)
![Version](https://img.shields.io/badge/version-0.5.0-green.svg)

Blubby's Mod is a Minecraft 1.19.2 content mod which introduces many Items, Mobs, Blocks, and more things I think would be a nice addition to the game. Also has JEI compatibility for custom crafting type. This mod requires the [Architectury API](https://curseforge.com/minecraft/mc-mods/architectury-api/files/version=1.19.2) so I can make Forge and Fabric versions of the mod.

# The Frog Programming Language

The Frog programming language was made by me and is parsed in Java. It was made for the purpose of interacting with my mods content, right now it can only change pixels colors.

## How to Use
Currently, there are 13 keywords
- Booleans `true`, `false`, `null`
- Conditionals `if`, `end`, `is`, `!!`, `<`, `>`
- Built-in `print`, `var`, `=`
- Minecraft `set_pixels [x y z, x y z, ...] [r g b]`

Keywords `true` and `false` are simple, basically `true` and `false` in every other language. The keyword `if` must have a boolean value directly after and must be ended with the `end` keyword. To define a variable, use `var`, then to set the variable `=` should be used. To print a variable, use `print`. Use the `is` keyword to see if a variable is the same as another and `!!` after a boolean value to invert it. `<` and `>` are comparisons for numbers. `set_pixels` is used for setting multiple pixels to a different color.

## Example Code
```
print "Please enter your name: "
var name = input

print "Please enter your age: "
var age = input

if name is "" !!
    print "Welcome to the Frog Programming Language, " + name + "!"
end

if age > 21
    print "You are over 21 years old."
end
```

**INPUT:**
```
Terry
37
```
**OUTPUT:**
```
Welcome to the Frog Programming Language, Terry!
You are over 21 years old.
```

# Building
If you want to build the mod, clone the repo and run ```gradlew build``` in the terminal.

The JAR file should be located in ```/[MOD PLATFORM]/build/libs```. The file in ```/common/build/libs/``` or ```/build/libs/``` will not work.

# Contacts
You can contact me at owner@gagsch.xyz
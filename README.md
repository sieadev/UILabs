# UILabs
**UILabs** is an API for Spigot plugins, enabling efficient and safe custom **inventory GUIs** in Minecraft.

## Why UILabs?
- **Efficient**: UILabs is designed to be resource efficient, with minimal impact on server performance.
- **Safe**: UILabs handles all interactions with the GUI ─ you don't have to worry about players messing with your GUIs.
- **Easy to Use**: Creating GUIs with UILabs is simple and intuitive, with a clean and easy-to-understand API.
- **Lightweight**: No need to include a bunch of dependencies in your plugin, UILabs is a standalone API.
- **Open Source**: UILabs is completely open source, so you can contribute to the project and help make it better.
- **Customizable**: Customize your GUIs with to your liking, using UILabs' built-in [components](#elements) and utilities.

## Table of Contents
- [Getting Started](#getting-started)
  - [Installation](#installation)
    - [Maven](#maven)
    - [Gradle](#gradle)
  - [Creating a GUI](#creating-a-gui)
- [Elements](#elements)


## Getting Started
### Installation
To get use UILabs in your plugin, add it as a dependency in your project.
#### Maven
```xml
<repository>
    <id>pixel-services-snapshots</id>
    <name>Pixel Services</name>
    <url>https://maven.pixel-services.com/snapshots</url>
</repository>
<dependency>
    <groupId>dev.siea.uilabs</groupId>
    <artifactId>UILabs</artifactId>
    <version>0.3.8-SNAPSHOT</version>
</dependency>
```
#### Gradle
```gradle
repositories {
    maven {
        name = "pixelServicesSnapshots"
        url = uri("https://maven.pixel-services.com/snapshots")
    }
}

dependencies {
    implementation "dev.siea.uilabs:UILabs:0.3.8-SNAPSHOT"
}
```

### Creating a GUI
To create a GUI with UILabs, follow these steps:
1. **Initialize UILabs**: Create an instance of `UILabs` in your plugin's main class. (Or wherever you want to use UILabs)
    ```java
    public class MyPlugin extends JavaPlugin {
        private UILabs uilabs;
    
        @Override
        public void onEnable() {
            uilabs = new UILabs(this);
        }
    }
    ```
2. **Create a GUI**: Use the `UILabs#create` method to create a ``DefaultInventoryGui`` instance. You can specify the title as well as the size(`height`, `width`) of the GUI. 
    ```java
    DefaultInventoryGui gui = uilabs.create("MyGUI", 6, 9);
    ```
    **Yes, it's that simple!** You now have a GUI instance that you can use to add [Elements](#elements) and show to players.

3. **Add Components**: UILabs provides a variety of [Elements](#elements), that you can add to your GUI. Here's an example of adding a `Button` Element to the GUI:
    ```java
    Button button = new Button(Material.EMERALD_BLOCK, ChatColor.GREEN + "Click me!") {
        @Override
        public void onButtonPressed(InventoryClickEvent e) {
            e.getWhoClicked().sendMessage(ChatColor.GREEN + "Button clicked! :D");
        }
    };
    gui.addElement(button);
    ```
   
4. **Show the GUI**: To show the GUI to a player, use the `UILabs#view` method:
    ```java
    gui.view(player);
    ```
    That's it! You've successfully created a fully functional GUI using UILabs.

## Elements
UILabs provides a variety of [Elements](#elements) that you can use to create your GUIs. Here's a list of some of the Elements that UILabs provides:
- `ItemElement`: The most basic element, that represents an item in the GUI. (Almost) All other elements extend this class.
- `Button`: A simple button that can be clicked by players to perform an action.
- `CheckBox`: A checkbox that players can toggle on and off.
- `Carousel`: A carousel that loops through a list of ItemElements at a specified interval.

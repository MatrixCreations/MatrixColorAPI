# 🌈 MatrixColorAPI

**MatrixColorAPI** is a powerful and flexible library for processing and applying custom colors, gradients, and text decorations in Minecraft servers. It supports hex colors, legacy Minecraft color codes, and more advanced formatting like gradients and solid colors.

This library simplifies color management while allowing developers to build beautifully formatted text with ease!

![Thumbnail](https://media.discordapp.net/attachments/1262415791731511347/1291385890517815387/image_9.png?ex=671c4095&is=671aef15&hm=b7f9fa12709bd425946f8efcd72a537693d8ec4303d3ca2a8dc40b84f206a2bc&=&format=webp&quality=lossless&width=550&height=314)

## ✨ Features

- 🎨 **Hex and Legacy Color Code Support**
- 🌈 **Gradients**: Apply smooth color transitions across text.
- 🟡 **Solid Colors**: Easily apply static colors to any text.
- 🖋️ **Text Decorations**: Support for bold, italic, underlined, strikethrough, and obfuscated text styles.
- 🚀 **Lightweight**: Minimal impact on performance.
- 🔄 **Easy to Use**: Clean and simple API to integrate into your project.
- 🌍 **Supports All Server Software**: Compatible with Spigot, Paper, Bukkit, Minestom, Fabric, and more.
- 🕹️ **Works with Every Minecraft Version Starting from 1.16.5**.
- 🛠️ **Open to Contributions**: Licensed under Apache 2.0 License for open contributions, modifications, and commercial use!

## 🚀 Getting Started

> [!IMPORTANT]
> While MatrixColorAPI is designed to work with all Minecraft server software, it has currently been extensively tested only on Bukkit platforms. We encourage users on other platforms to try it out and report any issues they encounter. Your feedback is crucial in making MatrixColorAPI universally compatible!
> 
> Please note that the Minestom Gradient Blocks format "<GRADIENT></GRADIENT>" is currently not supported. If you want to make this format work with MatrixColorAPI, feel free to code a solution and open a pull request. We welcome contributions to expand compatibility!

### Installation

![API Version](https://img.shields.io/jitpack/v/MatrixCreations/MatrixColorAPI.svg?color=512BD4&label=API%20Version&style=for-the-badge)

MatrixColorAPI is hosted on [Jitpack](https://jitpack.io), making it easy to integrate with your Maven or Gradle projects. Follow the steps below to add it to your project:

### Using Gradle
1. **Add the Jitpack repository** to your `build.gradle` file (inside `repositories` block):
   ```gradle
   repositories {
       maven { url 'https://jitpack.io' }
   }
   ```

2. **Add the dependency** in your `dependencies` block:
   ```gradle
   dependencies {
       implementation 'com.github.MatrixCreations:MatrixColorAPI:v1.0.7'
   }
   ```

### Using Maven
1. **Add the Jitpack repository** to your `pom.xml`:
   ```xml
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
   ```

2. **Add the dependency** in your `dependencies` block:
   ```xml
   <dependency>
       <groupId>com.github.MatrixCreations</groupId>
       <artifactId>MatrixColorAPI</artifactId>
       <version>v1.0.7</version>
   </dependency>
   ```

### Additional Information

- For the latest version, please scroll up to know the latest api version.
- Ensure you're using Java 16 or higher to avoid compatibility issues.

### Example Usages

- **Solid Color Example:**

![Solid Color Example](https://media.discordapp.net/attachments/1262415791731511347/1291383818502279218/image.png?ex=671c3ea7&is=671aed27&hm=3f0665dca694de2d917071776c46b98f0aaa34cdecdb0cee5c802e01b1e7e55f&=&format=webp&quality=lossless)

```java
String text = "<SOLID:#FFD700>This is yellow text";
String processed = MatrixColorAPI.process(text);
```

- **Gradient Example:**

![Gradient Color Example](https://media.discordapp.net/attachments/1262415791731511347/1291384051160055869/image.png?ex=671c3ede&is=671aed5e&hm=cc27f1669e839059c88bb4a465c2a9073d5afd8efbb8174c331607d513a2544e&=&format=webp&quality=lossless)

```java
String gradientText = "<GRADIENT:#FF0000>This is a red to yellow gradient</GRADIENT:#FFFF00>";
String processedGradient = MatrixColorAPI.process(gradientText);
```

- **Legacy Code Support:**

![Legacy Color Example](https://media.discordapp.net/attachments/1262415791731511347/1291384114905092096/image.png?ex=671c3eed&is=671aed6d&hm=b176621daa2f15709bc86170bdafcfdf5f23ab73697176fd57327ad567ae470d&=&format=webp&quality=lossless)

```java
String legacyText = "&aThis is a green text with &lBOLD";
String processedLegacy = MatrixColorAPI.process(legacyText);
```

- **Hex Code Support:**

![Hex Color Example](https://media.discordapp.net/attachments/1262415791731511347/1291384163072610335/image.png?ex=671c3ef9&is=671aed79&hm=1fd9ae3a15a8f94727b308f795f2b1bbd51561fb4a736f40dfc7cdd622fd8ed8&=&format=webp&quality=lossless)

```java
String hexText = "&#FFD700This is a yellow text with &lBOLD";
String processedLegacy = MatrixColorAPI.process(hexText);
```

## 🛠️ Available Methods

- `process(String text)`: Process a single string for color codes, gradients, and formatting.
- `process(List<String> texts)`: Process a list of strings for color codes and formatting.

## 📦 Contributing

We welcome contributions! To get started:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Submit a pull request with a clear explanation of your changes.

Make sure to follow the coding standards and include tests where appropriate.

## 📝 License

This project is licensed under the **Apache 2.0 License**. See the [LICENSE](./LICENSE.txt) file for details.

## 📢 Acknowledgments

Thanks to everyone contributing to this project! Your support and feedback help make **MatrixColorAPI** even better.

## 👥 Contact

For any questions or feedback, feel free to open an issue or reach out to us on our [discord server.](https://discord.gg/B4QsfUrdUR)

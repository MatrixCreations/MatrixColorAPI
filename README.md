# 🌈 MatrixColorAPI

**MatrixColorAPI** is a powerful and flexible library for processing and applying custom colors, gradients, and text decorations in Minecraft servers. It supports hex colors, legacy Minecraft color codes, and more advanced formatting like gradients and solid colors.

This library simplifies color management while allowing developers to build beautifully formatted text with ease!

![Thumbnail](https://i.imgur.com/God4ZAt.png)

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
       implementation 'com.github.MatrixCreations:MatrixColorAPI:v1.0.6'
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
       <version>v1.0.6</version>
   </dependency>
   ```

### Additional Information

- For the latest version, please scroll up to know the latest api version.
- Ensure you're using Java 16 or higher to avoid compatibility issues.

### Example Usages

- **Solid Color Example:**

![Solid Color Example](https://media.discordapp.net/attachments/1262415791731511347/1291383818502279218/image.png?ex=66ffe667&is=66fe94e7&hm=8cbe3b951c6b0cd7eee1b9edb7718f81b75c46f8e72388746b1fc1ed0da39c1e&=&format=webp&quality=lossless)

```java
String text = "<SOLID:#FFD700>This is yellow text";
String processed = MatrixColorAPI.process(text);
```

- **Gradient Example:**

![Gradient Color Example](https://media.discordapp.net/attachments/1262415791731511347/1291384051160055869/image.png?ex=66ffe69e&is=66fe951e&hm=ba7d6c4748241b8e989249e2cf7818fe41a2768a61a58753fad034bc04ab56db&=&format=webp&quality=lossless)

```java
String gradientText = "<GRADIENT:#FF0000>This is a red to yellow gradient</GRADIENT:#FFFF00>";
String processedGradient = MatrixColorAPI.process(gradientText);
```

- **Legacy Code Support:**

![Legacy Color Example](https://media.discordapp.net/attachments/1262415791731511347/1291384114905092096/image.png?ex=66ffe6ad&is=66fe952d&hm=b6f9d8dcb41a6f09cd1d2f7609fefda99cc01c14a5b2a351ee9bcb2655e46521&=&format=webp&quality=lossless)

```java
String legacyText = "&aThis is a green text with &lBOLD";
String processedLegacy = MatrixColorAPI.process(legacyText);
```

- **Hex Code Support:**

![Hex Color Example](https://media.discordapp.net/attachments/1262415791731511347/1291384163072610335/image.png?ex=66ffe6b9&is=66fe9539&hm=9938cbc8816a4f5465cde4af5853ca43e2b002855e3a3b00601c94b498a5a6da&=&format=webp&quality=lossless)

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

This project is licensed under the **Apache 2.0 License**. See the [LICENSE](./LICENSE) file for details.

## 📢 Acknowledgments

Thanks to everyone contributing to this project! Your support and feedback help make **MatrixColorAPI** even better.

## 👥 Contact

For any questions or feedback, feel free to open an issue or reach out to us on our [discord server.](https://discord.gg/B4QsfUrdUR)

# 🌈 MatrixColorAPI

**MatrixColorAPI** is a powerful and flexible library for processing and applying custom colors, gradients, and text decorations in Minecraft or other Java-based applications. It supports hex colors, legacy Minecraft color codes, and more advanced formatting like gradients and solid colors.

This library simplifies color management while allowing developers to build beautifully formatted text with ease!

## ✨ Features

- 🎨 **Hex and Legacy Color Code Support**
- 🌈 **Gradients**: Apply smooth color transitions across text.
- 🟡 **Solid Colors**: Easily apply static colors to any text.
- 🖋️ **Text Decorations**: Support for bold, italic, underlined, strikethrough, and obfuscated text styles.
- 🚀 **Lightweight**: Minimal impact on performance.
- 🔄 **Easy to Use**: Clean and simple API to integrate into your project.
- 🌍 **Supports All Server Software**: Compatible with Spigot, Paper, Bukkit, Minestom, Fabric, and more.
- 🕹️ **Works with Every Minecraft Version Starting from 1.16.5**.
- 🛠️ **Open to Contributions**: Licensed under MIT for open contributions, modifications, and commercial use!

## 🚀 Getting Started

### Installation

1. **Add the JAR to your project**: (Instructions to add it as a dependency once you release it, or users can manually download it)
2. **Build the project**: Make sure your project is built with a compatible version of Java.

### Example Usages

- **Solid Color Example:**

```java
String text = "<SOLID:#FFD700>This is yellow text";
String processed = ColorUtils.process(text);
```

- **Gradient Example:**

```java
String gradientText = "<GRADIENT:#FF0000>This is a red to yellow gradient</GRADIENT:#FFFF00>";
String processedGradient = ColorUtils.process(gradientText);
```

- **Legacy Code Support:**

```java
String legacyText = "&aThis is a green text with &lBOLD";
String processedLegacy = ColorUtils.process(legacyText);
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

For any questions or feedback, feel free to open an issue or reach out.

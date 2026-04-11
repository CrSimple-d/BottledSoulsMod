# Bottled Souls

### Основная механика

**С помощью Soul Bottle вы можете:**

* Захватывать душу почти любого моба
* Хранить её внутри бутылки
* Выпускать захваченную душу в любом выбранном месте

---

### Конфигурация

Мод включает гибкую систему настройки через JSON:

```json
{
  "isSoulBottleUnbreakable": false,
  "soulBottleDurability": 5,
  "mobBlackList": {
    "enable": true,
    "collection": [
      "minecraft:warden",
      "minecraft:wither"
    ]
  },
  "mobWhiteList": {
    "enable": false,
    "collection": [
      "minecraft:creeper"
    ]
  }
}
```

---

### Тег мобов

**Для разработчиков модпаков и плагинов:**

* Добавлена поддержка тега `black_list`
* Работает аналогично системе чёрного списка в конфиге
* Позволяет гибко настраивать интеграцию и datapack’и

---

### Права и команды

**Мод поддерживает совместимость с LuckPerms:**

Команда: `/bottledsouls reload` — **перезагружает файл конфигурации**

Требования:
OP уровень 4 или право LuckPerms: `bottled_souls.config.reload`

---

**P.S. - Если тебе понравился мод, пожалуйста, поставь ❤️ :3**

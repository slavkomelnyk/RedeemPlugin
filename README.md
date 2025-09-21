# RedeemPlugin

A simple redeem code plugin for Minecraft servers.

Players can use special codes to receive money via the Vault economy system.

### 🎮 Commands
```
/redeem <code>
/generatecode (OP only)
```



---

## 🔧 Installation

1. Make sure your server has **Vault** and an economy plugin (e.g. EssentialsX).
2. Download the plugin JAR.
3. Place the JAR file in your server's `plugins/` folder
4. Start or reload your server.

---

## ⚙️ Configuration

`plugins/RedeemPlugin/config.yml`

```yaml
# How much money to give per code
reward-amount: 1000.
```
You can change this amount to give more or less money per code.

---

## 💾 Codes Storage

`plugins/RedeemPlugin/codes.yml`

```
# All unused codes
codes:
  EXAMPLECODE: true
  SUMMER2025: true
```

Each code can be redeemed once. After redemption, it is removed from this file automatically.


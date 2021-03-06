package aquarius0715.infinitystack.main

import aquarius0715.infinitystack.events.PlayerItemPickUpEvent
import aquarius0715.infinitystack.events.PlayerJoinEvent
import aquarius0715.infinitystack.commands.Commands
import aquarius0715.infinitystack.config.LoadConfig
import aquarius0715.infinitystack.items.ConvertItems
import aquarius0715.infinitystack.mysql.MySQLInsert
import aquarius0715.infinitystack.mysql.MySQLManager
import aquarius0715.infinitystack.mysql.MySQLSelect
import aquarius0715.infinitystack.mysql.MySQLUpDate
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class InfinityStack : JavaPlugin() {

    val prefix = "[InfinityStack]"

    val convertItems = ConvertItems()

    val mySQLManager = MySQLManager(this, "InfinityStack")

    val mySQLSelect = MySQLSelect(this)

    val mySQLInsert = MySQLInsert(this)

    val mySQLUpDate = MySQLUpDate(this)

    val stackStats: MutableMap<UUID, Boolean> = mutableMapOf()

    val itemMap: MutableMap<String?, String?> = mutableMapOf()

    val loadConfig = LoadConfig(this)

    override fun onEnable() {

        saveDefaultConfig()

        loadConfig.loadConfig()

        Objects.requireNonNull(getCommand("is")!!.setExecutor(Commands(this)))

        server.pluginManager.registerEvents(PlayerItemPickUpEvent(this), this)

        server.pluginManager.registerEvents(PlayerJoinEvent(this), this)

    }

}
package gg.tropic.souppvp.listener

import gg.scala.commons.annotations.Listeners
import gg.tropic.souppvp.config.config
import gg.tropic.souppvp.profile.PlayerState
import gg.tropic.souppvp.profile.event.PlayerStateChangeEvent
import gg.tropic.souppvp.profile.profile
import gg.tropic.souppvp.profile.refresh
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author GrowlyX
 * @since 6/13/2023
 */
@Listeners
object ListenerService : Listener
{
    @EventHandler
    fun PlayerJoinEvent.on()
    {
        config.loginMessage
            .forEach(player::sendMessage)

        player.profile.state = PlayerState.Spawn
    }

    @EventHandler
    fun PlayerDeathEvent.on()
    {
        entity.profile.state = PlayerState.Spawn
    }

    @EventHandler
    fun PlayerStateChangeEvent.toSpawn()
    {
        if (to != PlayerState.Spawn)
        {
            return
        }

        profile.player().refresh(GameMode.ADVENTURE)
        profile.player().teleport(config.spawn)
    }
}

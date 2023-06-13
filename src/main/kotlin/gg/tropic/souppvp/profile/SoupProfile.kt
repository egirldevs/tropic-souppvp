package gg.tropic.souppvp.profile

import gg.scala.store.storage.storable.IDataStoreObject
import gg.tropic.souppvp.profile.bounty.Bounty
import gg.tropic.souppvp.profile.event.PlayerStateChangeEvent
import org.bukkit.Bukkit
import java.util.*

/**
 * @author GrowlyX
 * @since 6/12/2023
 */
data class SoupProfile(
    override val identifier: UUID,
    var kills: Int = 0,
    var deaths: Int = 0,
    var soupsConsumed: Int = 0,
    var coins: Double = 0.0,
    /**
     * Set of perk class names.
     */
    val ownedPerks: Set<String> = mutableSetOf(),
    val ownedKits: Set<String> = mutableSetOf()
) : IDataStoreObject
{
    var bounty: Bounty? = null

    @Transient
    internal var backingState = PlayerState.Loading

    var state: PlayerState
        get() = backingState
        set(value)
        {
            val oldValue = backingState
            this.backingState = value

            PlayerStateChangeEvent(
                this@SoupProfile,
                oldValue, value
            ).callEvent()
        }

    fun player() = Bukkit.getPlayer(identifier)!!
}

package me.minidigger.customentities.api.components;

import com.artemis.Component;
import me.minidigger.customentities.api.Documentation;
import org.bukkit.inventory.Inventory;

/**
 * Allows the entity to hold items
 */
@Documentation(val = "Allows the entity to hold items")
public class InventoryComponent extends Component {

    public Inventory inventory;

}

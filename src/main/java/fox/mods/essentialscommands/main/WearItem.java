package fox.mods.essentialscommands.main;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;

public class WearItem {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        ItemStack helmet = ItemStack.EMPTY;
        helmet = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY);
        {
            Entity _entity = entity;
            if (_entity instanceof Player _player) {
                _player.getInventory().armor.set(3, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
                _player.getInventory().setChanged();
            } else if (_entity instanceof LivingEntity _living) {
                _living.setItemSlot(EquipmentSlot.HEAD, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
            }
        }
        if (entity instanceof LivingEntity _entity) {
            ItemStack _setstack = helmet.copy();
            _setstack.setCount(1);
            _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
            if (_entity instanceof Player _player)
                _player.getInventory().setChanged();
        }
    }
}


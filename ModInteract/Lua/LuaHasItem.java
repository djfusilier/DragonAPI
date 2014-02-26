package Reika.DragonAPI.ModInteract.Lua;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import Reika.DragonAPI.Libraries.ReikaInventoryHelper;

public class LuaHasItem extends LuaMethod {

	public LuaHasItem() {
		super("hasItem", IInventory.class);
	}

	@Override
	public Object[] invoke(TileEntity te, Object[] args) throws Exception {
		IInventory ii = (IInventory) te;
		boolean flag = false;
		switch(args.length) {
		case 1: {
			int itemID = ((Double)args[0]).intValue();
			flag = ReikaInventoryHelper.checkForItem(itemID, ii);
		}
		break;
		case 2: {
			int itemID = ((Double)args[0]).intValue();
			int dmg = ((Double)args[1]).intValue();
			flag = ReikaInventoryHelper.checkForItemStack(itemID, dmg, ii);
		}
		break;
		case 3: {
			int itemID = ((Double)args[0]).intValue();
			int dmg = ((Double)args[1]).intValue();
			int size = ((Double)args[2]).intValue();
			ItemStack is = new ItemStack(itemID, size, dmg);
			flag = ReikaInventoryHelper.checkForItemStack(is, ii, true);
		}
		break;
		default:
			throw new IllegalArgumentException("Invalid ItemStack!");
		}
		return new Object[]{flag};
	}

}

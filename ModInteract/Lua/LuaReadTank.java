/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.ModInteract.Lua;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class LuaReadTank extends LuaMethod {

	public LuaReadTank() {
		super("readTank", IFluidHandler.class);
	}

	@Override
	public Object[] invoke(TileEntity te, Object[] args) throws Exception {
		int ordinal = ((Double)args[0]).intValue();
		IFluidHandler ifl = (IFluidHandler)te;
		Object[] o = new Object[3];
		FluidTankInfo info = ifl.getTankInfo(ForgeDirection.UP)[ordinal];
		if (info.fluid == null)
			return new Object[]{null, 0, info.capacity};
		o[0] = info.fluid.getFluid().getLocalizedName();
		o[1] = info.fluid.amount;
		o[2] = info.capacity;
		return o;
	}

	@Override
	public String getDocumentation() {
		return "Returns the contents of an IFluidTank.\nArgs: Tank Index\nReturns: [Fluid, Amount, Capacity]";
	}

}
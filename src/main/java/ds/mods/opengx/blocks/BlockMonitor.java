package ds.mods.opengx.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ds.mods.opengx.OpenGX;
import ds.mods.opengx.tileentity.TileEntityMonitor;

public class BlockMonitor extends Block {
	IIcon off, on, sidei;

	public BlockMonitor(Material p_i45394_1_) {
		super(p_i45394_1_);
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityMonitor();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float clickX, float clickY, float clickZ) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		if (((TileEntityMonitor)tileEntity).owner == null)
			return false;
		player.openGui(OpenGX.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public IIcon getIcon(IBlockAccess ba, int x,
			int y, int z, int side) {
		ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[side];
		if (dir == ForgeDirection.NORTH)
		{
			TileEntity tile = ba.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileEntityMonitor)
			{
				return ((TileEntityMonitor)tile).owner == null ? off : on;
			}
			else
			{
				return off;
			}
		}
		return sidei;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[side];
		if (dir == ForgeDirection.WEST)
		{
			return on;
		}
		return sidei;
	}

	@Override
	public void registerBlockIcons(IIconRegister ir) {
		off = ir.registerIcon("opengx:monitoroff");
		on = ir.registerIcon("opengx:monitoron");
		sidei = ir.registerIcon("opengx:commonside");
	}

}
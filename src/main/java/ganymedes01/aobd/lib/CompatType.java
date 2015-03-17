package ganymedes01.aobd.lib;

import ganymedes01.aobd.recipes.RecipesModule;
import ganymedes01.aobd.recipes.modules.AppliedEnergistics;
import ganymedes01.aobd.recipes.modules.BuildCraftAdditions;
import ganymedes01.aobd.recipes.modules.EnderIO;
import ganymedes01.aobd.recipes.modules.Factorization;
import ganymedes01.aobd.recipes.modules.GanysNether;
import ganymedes01.aobd.recipes.modules.Hydraulicraft;
import ganymedes01.aobd.recipes.modules.IndustrialCraft;
import ganymedes01.aobd.recipes.modules.Mekanism7;
import ganymedes01.aobd.recipes.modules.Mekanism8;
import ganymedes01.aobd.recipes.modules.ModularSystems;
import ganymedes01.aobd.recipes.modules.NetherOres;
import ganymedes01.aobd.recipes.modules.Railcraft;
import ganymedes01.aobd.recipes.modules.RandomAdditions;
import ganymedes01.aobd.recipes.modules.SimpleOreGrinder;
import ganymedes01.aobd.recipes.modules.Steamcraft;
import ganymedes01.aobd.recipes.modules.Thaumcraft;
import ganymedes01.aobd.recipes.modules.ThermalExpansion;
import ganymedes01.aobd.recipes.modules.TinkersConstruct;
import ganymedes01.aobd.recipes.modules.UltraTech;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;

public enum CompatType {

	IC2("IC2", IndustrialCraft.class, "dustTiny", "crushedPurified", "crushed", "dust"),
	RAILCRAFT("Railcraft", Railcraft.class, "crushed"),
	ENDERIO("EnderIO", EnderIO.class, "dust"),
	MEKANISM7("Mekanism", Mekanism7.class, "clump", "crystal", "shard", "dustDirty", "dust") {
		@Override
		public boolean isEnabled() {
			if (!super.isEnabled())
				return false;
			for (ModContainer mod : Loader.instance().getActiveModList())
				if ("Mekanism".equals(mod.getModId()))
					return mod.getVersion().startsWith("7");
			return false;
		}
	},
	MEKANISM8("Mekanism", Mekanism8.class, "clump", "crystal", "shard", "dustDirty", "dust") {
		@Override
		public boolean isEnabled() {
			if (!super.isEnabled())
				return false;
			for (ModContainer mod : Loader.instance().getActiveModList())
				if ("Mekanism".equals(mod.getModId()))
					return mod.getVersion().startsWith("8");
			return false;
		}
	},
	THAUMCRAFT("Thaumcraft", Thaumcraft.class, "cluster"),
	THERMAL_EXPANSION("ThermalExpansion", ThermalExpansion.class, "dust"),
	FACTORISATION("factorization", Factorization.class, "crystalline", "cleanGravel", "reduced", "dirtyGravel"),
	RANDOM_ADDITIONS("randomadditions", RandomAdditions.class, "dust"),
	GANYS_NETHER("ganysnether", GanysNether.class, "nugget"),
	ULTRA_TECH("UltraTech", UltraTech.class, "chunk", "dust"),
	MODULAR_SYSTEMS("modularsystems", ModularSystems.class, "dust"),
	NETHER_ORES("NetherOres", NetherOres.class, "ore"),
	APPLIED_ENERGISTICS("appliedenergistics2", AppliedEnergistics.class, "dust"),
	HYDRAULICRAFT("HydCraft", Hydraulicraft.class, "dust", "chunk"),
	TINKERS_CONSTRUCT("TConstruct", TinkersConstruct.class, "block"),
	SIMPLE_ORE_GRINDER("simpleoregrinder", SimpleOreGrinder.class, "dust"),
	STEAMCRAFT("Steamcraft", Steamcraft.class, "dust"),
	BC_ADDITIONS("bcadditions", BuildCraftAdditions.class, "dust");

	final String modid;
	final String[] prefixes;
	final Class<? extends RecipesModule> module;
	boolean enabled = true;

	CompatType(String modid, Class<? extends RecipesModule> module, String... prefixes) {
		this.modid = modid;
		this.prefixes = prefixes;
		this.module = module;
	}

	public String modID() {
		return modid;
	}

	public String[] prefixes() {
		return prefixes;
	}

	public RecipesModule getModule() throws InstantiationException, IllegalAccessException {
		return module.newInstance();
	}

	public void setStatus(boolean status) {
		enabled = status;
	}

	public boolean isEnabled() {
		return enabled && Loader.isModLoaded(modID());
	}
}
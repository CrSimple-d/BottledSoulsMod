package net.crsimple.bottledsouls.common.item;

import net.crsimple.bottledsouls.registry.ModDataComponents;
import net.crsimple.bottledsouls.util.EntityNbtHelper;
import net.crsimple.bottledsouls.ModMain;
import net.crsimple.bottledsouls.registry.ModTags;
import net.crsimple.bottledsouls.util.PlayerUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class SoulBottleItem extends Item {

    public SoulBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (shouldNotSaveEntity(stack, entity)) {
            user.sendMessage(Text.translatable("item.bottled_souls.soul_bottle:fail").formatted(Formatting.RED), true);
            return ActionResult.PASS;
        }

        NbtCompound nbt = EntityNbtHelper.saveEntity(new NbtCompound(), entity);
        stack.set(ModDataComponents.ENTITY_DATA, NbtComponent.of(nbt));
        stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(List.of(1f),List.of(),List.of(),List.of()));

        if (!user.getEntityWorld().isClient()) {
            entity.discard();
            user.setStackInHand(hand, stack);
            PlayerUtils.damageUnlessCreative(user, stack, hand);
            this.particles((ServerWorld) entity.getEntityWorld(), entity);
        }
        return ActionResult.PASS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        if (ctx.getPlayer() == null || !hasEntity(ctx.getStack())) return ActionResult.PASS;

        if (!ctx.getWorld().isClient()) {
            LivingEntity living = EntityNbtHelper.readEntity(ctx.getWorld(), getEntityNbt(ctx.getStack()), LivingEntity.class);
            BlockPos pos = ctx.getBlockPos();
            if (!ctx.getWorld().getBlockState(ctx.getBlockPos()).getCollisionShape(ctx.getWorld(), ctx.getBlockPos()).isEmpty()) {
                pos = pos.offset(ctx.getSide());
            }
            living.setPosition(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d);
            living.setYaw(ctx.getPlayerYaw() + 180f);
            living.setHeadYaw(ctx.getPlayerYaw() + 180f);
            living.setBodyYaw(ctx.getPlayerYaw() + 180f);
            ctx.getWorld().spawnEntity(living);

            this.particles((ServerWorld) ctx.getWorld(), living);
        }

        ItemStack stack = ctx.getStack();
        stack.remove(ModDataComponents.ENTITY_DATA);
        stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelDataComponent.DEFAULT);
        ctx.getPlayer().setStackInHand(ctx.getHand(), stack);

        return ActionResult.PASS;
    }

    private boolean shouldNotSaveEntity(ItemStack stack, LivingEntity entity) {
        String id = Registries.ENTITY_TYPE.getId(entity.getType()).toString();
        return hasEntity(stack) || (entity.getType().isIn(ModTags.EntityTags.BLACK_LIST) ||
                ModMain.CONFIG.mobBlackList.contains(id) ||
                (ModMain.CONFIG.mobWhiteList.isEnable() && !ModMain.CONFIG.mobWhiteList.contains(id)));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        MutableText text = Text.translatable("item.bottled_souls.soul_bottle.tooltip:prefix").formatted(Formatting.YELLOW);

        if (hasEntity(stack)) {
            text.append(Text.literal(EntityNbtHelper.parseIdentifier(getEntityNbt(stack)).toString())
                    .formatted(Formatting.RED));
        } else {
            text.append(Text.literal("empty")
                    .formatted(Formatting.GRAY));
        }

        textConsumer.accept(text);
    }

    private void particles(ServerWorld world, LivingEntity entity) {
        for (int i = 0; i < 20; ++i) {
            double d = world.random.nextGaussian() * 0.02;
            double e = world.random.nextGaussian() * 0.02;
            double f = world.random.nextGaussian() * 0.02;
            world.spawnParticles(ParticleTypes.POOF, entity.getParticleX(1.0), entity.getRandomBodyY(), entity.getParticleZ(1.0), 2, d, e, f, 0.1d);
        }
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return hasEntity(stack);
    }

    private boolean hasEntity(ItemStack stack) {
        return EntityNbtHelper.hasEntity(getEntityNbt(stack));
    }

    private @Nullable NbtCompound getEntityNbt(ItemStack stack) {
        NbtComponent nbt = stack.get(ModDataComponents.ENTITY_DATA);
        return nbt==null?null:nbt.copyNbt();
    }
}
